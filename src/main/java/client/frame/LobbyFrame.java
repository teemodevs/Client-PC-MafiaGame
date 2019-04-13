package client.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import client.frame.action.CloseWindowWithLogoutAction;
import game.User;
import protocol.Protocol;
import protocol.system.subprotocol.GameRoomListProtocol;
import protocol.system.subprotocol.JoinGameRoomProtocol;

/**
 * 로그인에 성공하고 대기실 화면. 서버에 만들어진 GameRoom의 리스트를 확인할 수 있음
 */
public class LobbyFrame extends JFrame {
	private JPanel	mainPanel; 			// 메인 패널
	private JTable	gameRoomListTable;	// 접속 가능한 GameRoom 리스트
	private JButton	makeGameRoomButton;	// 방만들기 버튼

	private static LobbyFrame lobbyFrame = new LobbyFrame();

	public static LobbyFrame getInstance() {
		return lobbyFrame;
	}
	
	private LobbyFrame() {
		this.mainPanel = new JPanel();
		this.gameRoomListTable = new JTable(new GameRoomTableModel());
		this.makeGameRoomButton = new JButton("방만들기");
	}

	/**
	 * 로그인 성공시 수행, LobbyFrame을 실제로 띄움
	 */
	public void boot() {
		this.appendMainPanel();
		this.appendTable();
		this.appendMakeGameRoomButton();
		
		this.addWindowListener(new CloseWindowWithLogoutAction());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	private void appendMainPanel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1000, 620);

		/* 메인 패널 설정 */
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
	}

	private void appendTable() {
		this.updateGameRoomListRequest();
		gameRoomListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameRoomListTable.getTableHeader().setReorderingAllowed(false);
		gameRoomListTable.getTableHeader().setResizingAllowed(false);
		gameRoomListTable.addMouseListener(new GameRoomJoinAction());

		JScrollPane jScrollPane = new JScrollPane(gameRoomListTable);
		jScrollPane.setBounds(100, 100, 100, 100);
		jScrollPane.setBackground(Color.WHITE);
		mainPanel.add(jScrollPane);
	}

	private void appendMakeGameRoomButton() {
		makeGameRoomButton.setBounds(100, 80, 100, 20);
		makeGameRoomButton.addActionListener(new GameRoomCreateFrameAction());
		mainPanel.add(makeGameRoomButton);
	}

	/**
	 * 서버에 접속 가능한 GameRoom 리스트를 요청
	 */
	private void updateGameRoomListRequest() {
		Protocol protocol = new GameRoomListProtocol();
		User.getInstance().sendProtocol(protocol);
	}

	/**
	 * 서버로부터 받은, 접속 가능한 GameRoom 리스트를 바탕으로 List 업데이트
	 * @param gameRoomMap Map<Integer, String> 접속 가능한 GameRoom의 맵
	 */
	public void updateGameRoomList(Map<Integer, String> gameRoomMap) {
		DefaultTableModel tableModel = (DefaultTableModel) this.gameRoomListTable.getModel();
		for (Integer gameRoomNumber : gameRoomMap.keySet())
			tableModel.addRow(new Object[]{gameRoomNumber, gameRoomMap.get(gameRoomNumber)});
	}

	/**
	 * 방만들기 창 띄움
	 */
	class GameRoomCreateFrameAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			GameRoomCreateFrame gameRoomCreateFrame = GameRoomCreateFrame.getInstance();
			gameRoomCreateFrame.boot();
		}
	}
	
	/**
	 * 방 들어가기 액션 (번호더블클릭) : 서버에 방에 접속한다고 요청
	 */
	class GameRoomJoinAction extends MouseAdapter {
		public void mouseClicked(MouseEvent mouseEvent) {
			JTable table = (JTable) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 2) {
	        	// 클릭한 위치에 해당하는 요소의 인덱스
				Point point = mouseEvent.getPoint();
				int index = gameRoomListTable.rowAtPoint(point);
	            // 빈칸 클릭이 아닌 경우
	            if (index != -1) {
	            	int rowIndex = table.getSelectedRow();
	            	int columnIndex = table.getSelectedColumn();
					Object value = table.getValueAt(rowIndex, columnIndex);
					System.out.println(value.toString());
	            	Protocol protocol = new JoinGameRoomProtocol()
										.setGameRoomNumber(rowIndex);
	            	User.getInstance().sendProtocol(protocol);
	            }
	        } 
		}
	} 
}