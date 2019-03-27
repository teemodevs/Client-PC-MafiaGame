package client.frame.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import game.User;
import resource.ResourceLoader;

/**
 * GameFrame에서 유저 접속 시 할성화되는 UserFrame
 */
public class UserFrame {

	private JLabel 	idLabel;		 // userid
	private JButton characterButton; // 캐릭터 사진 및 버튼
	private boolean isLogined;		 // 할당 여부 (현재 GameRoom에 로그인 되어 있는지)

	/**
	 * UserFrame Constructor
	 * @param index integer 생성될 UserFrame의 인덱스 번호
	 */
	public UserFrame(int index) {
		this.isLogined = false;

		this.idLabel = new JLabel();
		this.idLabel.setForeground(Color.RED);
		this.idLabel.setVisible(false);

		this.characterButton = new JButton();
		this.characterButton.setBackground(Color.BLACK);
		this.characterButton.setIcon(ResourceLoader.getImageIconResource("img/civil.png"));
		this.characterButton.addActionListener(new JobActionListener());
		this.characterButton.setVisible(false);

		switch (index) {
		case 0:
			this.characterButton.setBounds(50, 50, 100, 100);
			this.idLabel.setBounds(87, 30, 60, 30);
			break;
		case 1:
			this.characterButton.setBounds(300, 50, 100, 100);
			this.idLabel.setBounds(337, 30, 60, 30);
			break;
		case 2:
			this.characterButton.setBounds(550, 50, 100, 100);
			this.idLabel.setBounds(587, 30, 60, 30);
			break;
		case 3:
			this.characterButton.setBounds(50, 250, 100, 100);
			this.idLabel.setBounds(87, 230, 60, 30);
			break;
		case 4:
			this.characterButton.setBounds(550, 250, 100, 100);
			this.idLabel.setBounds(587, 230, 60, 30);
			break;
		case 5:
			this.characterButton.setBounds(50, 450, 100, 100);
			this.idLabel.setBounds(87, 430, 60, 30);
			break;
		case 6:
			this.characterButton.setBounds(300, 450, 100, 100);
			this.idLabel.setBounds(337, 430, 60, 30);
			break;
		case 7:
			this.characterButton.setBounds(550, 450, 100, 100);
			this.idLabel.setBounds(587, 430, 60, 30);
			break;
		}

	}

	/**
	 * User Id JLabel 반환
	 * @return idLabel JLabel User Id가 입력되는 JLabel
	 */
	public JLabel getIdLabel() {
		return idLabel;
	}

	/**
	 * 캐릭터 JButton 반환
	 * @return characterButton JButton 캐릭터 버튼
	 */
	public JButton getCharacterButton() {
		return characterButton;
	}

	/**
	 * 활성화(로그인) 여부 반환
	 * @return isLogined boolean 활성화 여부
	 */
	public boolean isLogined() {
		return isLogined;
	}

	/**
	 * 활성화 여부 지정
	 * @param isLogined boolean 활성화 여부
	 */
	public void setLogined(boolean logined) {
		isLogined = logined;
	}

	
	/**
	 * 직업 액션 : 선택 시 해당 유저의 직업에 따라 액션을 서버에 요청
	 */
	class JobActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			User.getInstance().jobAction(idLabel.getText());
		}
	}
}