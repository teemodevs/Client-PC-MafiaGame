package client.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import client.frame.action.CloseWindowWithLogoutAction;
import game.User;
import protocol.Protocol;
import protocol.system.subprotocol.GameRoomListProtocol;
import protocol.system.subprotocol.GameRoomMakeProtocol;
import protocol.system.subprotocol.JoinGameRoomProtocol;

/**
 * 로그인에 성공하고 대기실 화면. 서버에 만들어진 GameRoom의 리스트를 확인할 수 있음
 */
public class LobbyFrame extends JFrame {
	private JPanel 						mainPanel; 			// 메인 패널
	private JList<Integer> 				gameRoomList;		// 접속 가능한 GameRoom 리스트
	private DefaultListModel<Integer> 	listModel; 			// GameRoom 리스트에 들어갈 ListModel
	private JButton 					makeGameRoomButton;	// 방만들기 버튼

	private static LobbyFrame lobbyFrame = new LobbyFrame();

	public static LobbyFrame getInstance() {
		return lobbyFrame;
	}
	
	private LobbyFrame() {
		this.mainPanel = new JPanel();
		listModel = new DefaultListModel<>();
		this.gameRoomList = new JList<>(listModel);
		this.makeGameRoomButton = new JButton("방만들기");
	}

	/**
	 * 로그인 성공시 수행, LobbyFrame을 실제로 띄움
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

		this.updateGameRoomListRequest();
		gameRoomList.setBackground(Color.WHITE);
		gameRoomList.setBounds(100, 100, 100, 100);
		gameRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameRoomList.addMouseListener(new GameRoomJoinAction());
		mainPanel.add(gameRoomList);

		makeGameRoomButton.setBounds(100, 80, 100, 20);
		makeGameRoomButton.addActionListener(new GameRoomMakeAction());
		mainPanel.add(makeGameRoomButton);
		
		this.addWindowListener(new CloseWindowWithLogoutAction());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 서버에 접속 가능한 GameRoom 리스트를 요청
	 */
	public void updateGameRoomListRequest() {
		Protocol protocol = new GameRoomListProtocol();
		User.getInstance().sendProtocol(protocol);
	}

	/**
	 * 서버로부터 받은, 접속 가능한 GameRoom 리스트를 바탕으로 List 업데이트
	 * @param gameRoomNumberList List<Integer> 접속 가능한 GameRoom의 방 번호 배열
	 */
	public void updateGameRoomList(List<Integer> gameRoomNumberList) {
		System.out.println("LobbyFrame.setGameRoom()");
		listModel.removeAllElements();
		listModel.addAll(gameRoomNumberList);
	}

	/**
	 * 방 만들기 버튼 액션 : 서버에 방을 만들어달라고 요청
	 */
	class GameRoomMakeAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Protocol protocol = new GameRoomMakeProtocol();
			User.getInstance().sendProtocol(protocol);
		}
	}
	
	/**
	 * 방 들어가기 액션 (번호더블클릭) : 서버에 방에 접속한다고 요청
	 */
	class GameRoomJoinAction extends MouseAdapter {
		public void mouseClicked(MouseEvent mouseEvent) {
			JList list = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 2) {
	        	// 클릭한 위치에 해당하는 요소의 인덱스
	            int index = list.locationToIndex(mouseEvent.getPoint());
	            // 빈칸 클릭이 아닌 경우
	            if (index != -1) {
	            	int gameRoomNumber = (int) list.getSelectedValue();
	            	Protocol protocol = new JoinGameRoomProtocol().setGameRoomNumber(gameRoomNumber);
	            	User.getInstance().sendProtocol(protocol);
	            }
	        } 
		}
	} 
}