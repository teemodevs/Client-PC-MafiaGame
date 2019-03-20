package client.frame.game;

import exception.game.gui.MessageAppendToPanelFailure;
import game.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private static GameFrame gameFrame = new GameFrame();

    /** 메인 패널 **/
    private JPanel mainPanel;
    private JLabel centerLabel;          // GameFrame 중앙 라벨
    private List<UserFrame> userFrameList;  // 유저 패널 리스트

    /** 채팅창 **/
    private JTextField inputChatTextField; // 채팅입력창
    private JScrollPane scrollPane;    // 채팅창 스크롤 바
    private JButton sendMessageButton; // 채팅창 전송버튼
    private JTextPane textPane;        // 채팅창


    private GameFrame() {
        mainPanel = new JPanel();
        textPane = new JTextPane();
        scrollPane = new JScrollPane();
        inputChatTextField = new JTextField();
        sendMessageButton = new JButton("전송");
        centerLabel = new JLabel("환영합니다.", JLabel.CENTER);
        userFrameList = new ArrayList<UserFrame>(8);
    }

    /**
     * @return gameFrame GameFrame
     * 게임프레임 반환
     * **/
    public static GameFrame getInstance() {
        return gameFrame;
    }

    /**
     * @param message String 출력할 메시지
     * 채팅창에 메시지를 출력
     **/
    public void appendMessageToTextPane(String message) {
        StyledDocument document = (StyledDocument) this.textPane.getDocument();
        try {
            document.insertString(document.getLength(), message + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
            throw new MessageAppendToPanelFailure("Message Append to Panel Failed");
        }
    }

    /**
     * 로그인 성공 시 실행, GameFrame 을 띄움
     * **/
    public void boot() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 1000, 620);

        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        textPane.setBackground(Color.WHITE);
        textPane.setEditable(false);

        scrollPane.setBounds(696, 30, 280, 490);
        scrollPane.setViewportView(textPane);
        mainPanel.add(scrollPane);

        inputChatTextField.setBounds(696, 532, 210, 32);
        mainPanel.add(inputChatTextField);

        sendMessageButton.setBounds(916, 532, 60, 32);
        sendMessageButton.setForeground(Color.WHITE);
        sendMessageButton.setBackground(Color.BLACK);
        sendMessageButton.addActionListener(new SubmitMessageAction());
        mainPanel.add(sendMessageButton);

        centerLabel.setBounds(200, 200, 300, 200);
        centerLabel.setBackground(Color.red);
        centerLabel.setForeground(Color.white);
        mainPanel.add(centerLabel, 2);

        this.addWindowListener(new CloseWindowAction());

        setVisible(true);
    }

    /**
     * @param user User 접속한 유저 객체
     * 유저 접속 시 UserFrame을 mainPanel에 붙임
     **/
    public void attachUserFrame(String userId) {
    	UserFrame userFrame = new UserFrame(userId);
    	this.userFrameList.add(userFrame);
    	
    	switch(this.userFrameList.indexOf(userFrame)) {
	    	case 0 :
	    		userFrame.getCharacterButton().setBounds(50, 50, 100, 100);
	    		userFrame.getIdLabel().setBounds(87, 30, 60, 30);
	    		break;
	    	case 1 :
	    		userFrame.getCharacterButton().setBounds(300, 50, 100, 100);
	    		userFrame.getIdLabel().setBounds(337, 30, 60, 30);
	    		break;
	    	case 2 :
	    		userFrame.getCharacterButton().setBounds(550, 50, 100, 100);
	    		userFrame.getIdLabel().setBounds(587, 30, 60, 30);
	    		break;
	    	case 3 :
	    		userFrame.getCharacterButton().setBounds(50, 250, 100, 100);
	    		userFrame.getIdLabel().setBounds(87, 230, 60, 30);
	    		break;
	    	case 4 :
	    		userFrame.getCharacterButton().setBounds(550, 250, 100, 100);
	    		userFrame.getIdLabel().setBounds(587, 230, 60, 30);
	    		break;
	    	case 5 :
	    		userFrame.getCharacterButton().setBounds(50, 450, 100, 100);
	    		userFrame.getIdLabel().setBounds(87, 430, 60, 30);
	    		break;
	    	case 6 :
	    		userFrame.getCharacterButton().setBounds(300, 450, 100, 100);
	    		userFrame.getIdLabel().setBounds(337, 430, 60, 30);
	    		break;
	    	case 7 :
	    		userFrame.getCharacterButton().setBounds(550, 450, 100, 100);
	    		userFrame.getIdLabel().setBounds(587, 430, 60, 30);
	    		break;
    	}
    	this.mainPanel.add(userFrame.getCharacterPanel());
    }
    
    /**
     * 메시지 전송 액션
     * 채팅입력창(inputChatTextField)에 써있는 문자를 서버로 전송
     **/
    class SubmitMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User.getInstance().sendMessage(inputChatTextField.getText());
        }
    }

    /**
     * 윈도우창 닫기 액션
     * 로그아웃 처리 및 프로그램 종료
     **/
    class CloseWindowAction extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(GameFrame.getInstance(),
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                User.getInstance().logout();
                System.exit(0);
            }
        }
    }
}
