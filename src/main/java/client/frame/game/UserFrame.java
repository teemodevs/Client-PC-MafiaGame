package client.frame.game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import resource.ResourceLoader;

public class UserFrame {

	private JLabel idLabel;
	private JButton characterButton;
	private boolean isLogined;

	public UserFrame(int index) {
		this.isLogined = false;

		this.idLabel = new JLabel();
		this.idLabel.setForeground(Color.RED);
		this.idLabel.setVisible(false);

		this.characterButton = new JButton();
		this.characterButton.setBackground(Color.BLACK);
		this.characterButton.setIcon(ResourceLoader.getImageIconResource("img/civil.png"));
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

	public JLabel getIdLabel() {
		return idLabel;
	}

	public JButton getCharacterButton() {
		return characterButton;
	}

	public boolean isLogined() {
		return isLogined;
	}

	public void setLogined(boolean logined) {
		isLogined = logined;
	}

}
