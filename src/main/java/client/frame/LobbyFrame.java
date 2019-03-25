package client.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.frame.action.CloseWindowWithLogoutAction;
import game.User;
import protocol.Protocol;
import protocol.system.subprotocol.GameRoomListSubSystemProtocol;

/**
 * 로그인에 성공하고 대기실 화면. 서버에 만들어진 GameRoom의 리스트를 확인할 수 있음
 **/
public class LobbyFrame extends JFrame {
	private JPanel mainPanel; // 메인 패널
	private JList<Integer> gameRoomList;
	private JButton makeGameRoomButton;

	private static LobbyFrame lobbyFrame = new LobbyFrame();

	public static LobbyFrame getInstance() {
		return lobbyFrame;
	}
	
	private LobbyFrame() {
		this.mainPanel = new JPanel();
		this.gameRoomList = new JList<>();
		this.makeGameRoomButton = new JButton("방만들기");
	}

	public void boot() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1000, 620);

		/* 메인 패널 설정 */
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);

		gameRoomList.setBackground(Color.WHITE);
		gameRoomList.setBounds(100, 100, 100, 100);
		mainPanel.add(gameRoomList);

		this.updateGameRoomList();
		
		this.addWindowListener(new CloseWindowWithLogoutAction());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public void updateGameRoomList() {
		Protocol protocol = new GameRoomListSubSystemProtocol();
		User.getInstance().sendProtocol(protocol);
	}

	public void setGameRoomList(List<Integer> gameRoomNumberList) {
		System.out.println("LobbyFrame.setGameRoom()");
		DefaultListModel<Integer> listModel = new DefaultListModel<>();
		listModel.addAll(gameRoomNumberList);
		gameRoomList = new JList<Integer>(listModel);
	}

	class GameRoomMakeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	} 
}
