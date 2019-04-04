package client.frame.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import client.frame.action.CloseWindowWithLogoutAction;
import exception.game.gui.MessageAppendToPanelFailure;
import game.GameContext;
import game.User;
import protocol.Protocol;
import protocol.game.subprotocol.ExecuteVoteAgreeProtocol;
import resource.ResourceLoader;
import resource.SoundPlayer;

/**
 * LobbyFrame에서 방을 선택하고 방에 들어왔을 때 게임창
 */
public class GameFrame extends JFrame {
	private static final int MAX_USERS = 8;
	private static GameFrame gameFrame = new GameFrame();

	/**
	 * 메인 패널
	 */
	private JPanel 			mainPanel; 		// 메인 패널
	private JLabel 			centerLabel; 	// GameFrame 중앙 라벨
	private List<UserFrame> userFrameList; 	// 유저 패널 리스트
	private JButton 		startButton; 	// 시작 버튼
	private JButton 		killButton;		// 죽이기 버튼
	private JButton 		saveButton;		// 살리기 버튼

	/**
	 * 채팅창
	 */
	private JTextField 		inputChatTextField; // 채팅입력창
	private JScrollPane 	scrollPane; 		// 채팅창 스크롤 바
	private JButton 		sendMessageButton; 	// 채팅창 전송버튼
	private JTextPane 		textPane; 			// 채팅창

	/**
	 * 사운드
	 */
	private SoundPlayer		bgmSoundPlayer;		// 게임방 기본 음악 플레이어
	
	private GameFrame() {
		mainPanel 		= new JPanel();
		centerLabel 	= new JLabel("환영합니다.", JLabel.CENTER);
		userFrameList 	= new ArrayList<>(8);
		killButton = new JButton("Kill");
		saveButton = new JButton("Save");

		inputChatTextField 	= new JTextField();
		scrollPane 			= new JScrollPane();
		sendMessageButton 	= new JButton("전송");
		textPane 			= new JTextPane();
		bgmSoundPlayer 		= new SoundPlayer("sound/gameroomwait.wav");
	}

	/**
	 * 게임프레임 반환
	 * @return gameFrame GameFrame 게임프레임
	 */
	public static GameFrame getInstance() {
		return gameFrame;
	}
	
	/**
	 * 채팅창에 메시지를 출력
	 * @param message String 출력할 메시지
	 */
	public void appendMessageToTextPane(String message, Color color) {
		SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
		simpleAttributeSet.addAttribute(StyleConstants.Foreground, color);
		
		StyledDocument document = (StyledDocument) this.textPane.getDocument();
		try {
			document.insertString(document.getLength(), message + "\n", simpleAttributeSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
			throw new MessageAppendToPanelFailure("Message Append to Panel Failed");
		}
	}

	/**
	 * 로그인 성공 시 실행, GameFrame 을 띄움
	 */
	public void boot() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1000, 620);

		/* 메인 패널 설정 */
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);

		/* 센터 라벨 설정 */
		centerLabel.setBounds(200, 200, 300, 200);
		centerLabel.setBackground(Color.red);
		centerLabel.setForeground(Color.white);
		mainPanel.add(centerLabel);
		
		/* 유저 프레임 설정 */
		for (int index = 0; index < MAX_USERS; index++) {
			UserFrame userFrame = new UserFrame(index);
			this.userFrameList.add(userFrame);
			this.mainPanel.add(userFrame.getCharacterButton());
			this.mainPanel.add(userFrame.getIdLabel());
		}

		/* 죽이기 버튼 설정 */
		killButton.setBounds(280, 320, 70, 30);
		killButton.setBackground(Color.BLACK);
		killButton.setForeground(Color.RED);
		killButton.setVisible(false);
		killButton.addActionListener(new ExecuteVoteAgreeAction());
		mainPanel.add(killButton);

		/* 살리기 버튼 설정 */
		saveButton.setBounds(360, 320, 70, 30);
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(Color.BLUE);
		saveButton.setVisible(false);
		saveButton.addActionListener(new ButtonDisableAction());
		mainPanel.add(saveButton);

		/* 채팅 입력창 설정 */
		inputChatTextField.setBounds(696, 532, 210, 32);
		mainPanel.add(inputChatTextField);
		
		/* 채팅창 스크롤 설정 */
		scrollPane.setBounds(696, 30, 280, 490);
		scrollPane.setViewportView(textPane);
		mainPanel.add(scrollPane);
		
		/* 채팅 전송 버튼 설정 */
		sendMessageButton.setBounds(916, 532, 60, 32);
		sendMessageButton.setForeground(Color.WHITE);
		sendMessageButton.setBackground(Color.BLACK);
		sendMessageButton.addActionListener(new SubmitMessageAction());
		mainPanel.add(sendMessageButton);
		
		/* 채팅창 설정 */
		textPane.setBackground(Color.WHITE);
		textPane.setEditable(false);

		this.addWindowListener(new CloseWindowWithLogoutAction());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.soundOn();
		setVisible(true);
	}

	/**
	 * GameFrame에서 현재 유저가 할당되지 않은, 비활성하된 UserFrame을 반환
	 * @return userFrmae UserFrame 비활성화된 UserFrame
	 */
	private UserFrame getEmptyUserFrame() {
		for (UserFrame userFrame : this.userFrameList) {
			if (!userFrame.isLogined())
				return userFrame;
		}
		return null;
	}

	/**
	 * 유저가 새로 로그인할 때 UserFrame에 할당 후 활성화
	 * @param loginUserIdList String 서버 기준에서 접속한 유저의 id List
	 */
	public void attachUserFrame(List<String> loginUserIdList) {
		// 클라이언트 기준 접속한 유저의 id List
		List<String> clientUserIdList = new ArrayList<>();

		for (UserFrame userFrame : this.userFrameList) {
			if (userFrame.isLogined())
				clientUserIdList.add(userFrame.getIdLabel().getText());
		}

		// 서버 기준의 접속 id 리스트에서, 클라 기준 접속 id 리스트를 제외하여 새로 접속한 유저의 id를 구함
		loginUserIdList.removeAll(clientUserIdList);

		// 새로 접속한 유저를 UserFrame에 반영
		for (String userId : loginUserIdList) {
			UserFrame newUserFrame = getEmptyUserFrame();
			newUserFrame.getIdLabel().setText(userId);
			newUserFrame.setLogined(true);
			newUserFrame.getIdLabel().setVisible(true);
			newUserFrame.getCharacterButton().setVisible(true);
		}

	}

	/**
	 * 특정 유저가 로그아웃하면 해당 유저 자리에 있는 UserFrame을 다른 유저가 입장 시 할당될 수 있게 비활성화
	 * @param logoutUserId String 로그아웃 한 유저의 id
	 */
	public void detachUserFrame(String logoutUserId) {
		for (UserFrame userFrame : this.userFrameList) {
			if (userFrame.isLogined() && userFrame.getIdLabel().getText().equals(logoutUserId)) {
				userFrame.setLogined(false);
				userFrame.getIdLabel().setText(null);
				userFrame.getIdLabel().setVisible(false);
				userFrame.getCharacterButton().setVisible(false);
			}
		}
	}

	/**
	 * 방장이 바뀐 경우 버튼 추가
	 */
	public void addStartButton() {
		/* 시작 버튼 설정 */
		this.startButton = new JButton("start");
		this.startButton.setBounds(310, 350, 80, 30);
		this.startButton.setBackground(Color.WHITE);
		this.startButton.setForeground(Color.WHITE);
		this.startButton.addActionListener(new StartGameAction());
		
		if (GameContext.getInstance().isPlaying())
			this.setStartButtonVisible(false);
		else
			this.setStartButtonVisible(true);
		
		this.mainPanel.add(startButton);
		repaint();
	}
	
	/**
	 * 시작버튼 활성/비활성화
	 * 활성화 시 : 방장으로 할당되어 있는경우 && 게임이 시작하지 않은 경우
	 * @param visible boolean 활성화 여부
	 */
	public void setStartButtonVisible(boolean visible) {
		this.startButton.setVisible(visible);
	}

	/**
	 * 죽이기 버튼 활성/비활성화
	 * 활성화 시 : 최후의 변론이 끝난 후 처형투표 시 && 유저가 살아있는 경우
	 * @param visible boolean 활성화 여부
	 */
	public void setKillButtonVisible(boolean visible) {
		this.killButton.setVisible(visible);
	}

	/**
	 * 살리기 버튼 활성/비활성화
	 * 활성화 시 : 최후의 변론이 끝난 후 처형투표 시 && 유저가 살아있는 경우
	 * @param visible boolean 활성화 여부
	 */
	public void setSaveButtonVisible(boolean visible) {
		this.saveButton.setVisible(visible);
	}

	/**
	 * GameFrame BGM 켜기
	 */
	public void soundOn() {
		//this.bgmSoundPlayer.play().repeat();
	}
	
	/**
	 * GameFrame BGM 끄기
	 */
	public void soundOff() {
		this.bgmSoundPlayer.stop();
	}
	
	/**
	 * 특정 유저의 캐릭터 버튼 이미지 변경
	 * @param userId String 이미지를 변경할 유저의 Id
	 * @param filePath String 변경할 이미지의 경로
	 */
	public void setCharacterButtonImage(String userId, String filePath) {
		for (UserFrame userFrame : this.userFrameList) {
			// 활성화 되어 있고 해당 유저가 맞으면 이미지 변경
			if ((userFrame.isLogined()) && (userFrame.getIdLabel().getText() == userId))
				userFrame.getCharacterButton().setIcon(ResourceLoader.getImageIconResource(filePath));
		}
	}
	
	/**
	 * 특정 유저의 캐릭터 버튼 비활성화
	 * @param userId String 버튼을 활성/비활성 시킬 유저의 Id
	 * @param active boolean 버튼 활성/비활성 여부
	 */
	public void setCharacterButtonActivation(String userId, boolean active) {
		for (UserFrame userFrame : this.userFrameList) {
			// 활성화 되어 있고 해당 유저가 맞으면 버튼 활성/비활성화
			if ((userFrame.isLogined()) && (userFrame.getIdLabel().getText() == userId)) 
				userFrame.getCharacterButton().setEnabled(active);
		}
	}

	/**
	 * 처형투표 찬성 액션 : 가장 많은 투표수를 얻은 유저에 대해 처형을 찬성함
	 */
	class ExecuteVoteAgreeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Protocol protocol = new ExecuteVoteAgreeProtocol();
			User.getInstance().sendProtocol(protocol);
			new ButtonDisableAction().actionPerformed(e);
		}
	}

	class ButtonDisableAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new SoundPlayer("sound/buttonclick.wav").play();
			killButton.setEnabled(false);
			saveButton.setEnabled(false);
		}
	}

	/**
	 * 메시지 전송 액션 : 채팅입력창(inputChatTextField)에 써있는 문자를 서버로 전송
	 */
	class SubmitMessageAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			User.getInstance().sendMessage(inputChatTextField.getText());
		}
	}
	
	/**
	 * 서버에 게임 시작 요청
	 */
	class StartGameAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new SoundPlayer("sound/buttonclick.wav").play();
			User.getInstance().startGame();	
		}
	}
}