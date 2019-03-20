package client.frame.game;

import javax.swing.*;

public class UserFrame extends JFrame {
    private String userId;
    private JPanel characterPanel;
    private JLabel idLabel;
    private JButton characterButton;
    
    public UserFrame(String userId) {
    	this.userId = userId;
    	this.characterPanel = new JPanel();
    	this.idLabel = new JLabel();
    	this.idLabel.setText(this.userId);
    	this.characterButton = new JButton();
    	this.characterPanel.add(idLabel);
    	this.characterPanel.add(characterButton);
    	this.characterPanel.setVisible(true);
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JButton getCharacterButton() {
		return characterButton;
	}

	public void setCharacterButton(JButton characterButton) {
		this.characterButton = characterButton;
	}
	
	public JPanel getCharacterPanel() {
		return this.characterPanel;
	}
	
	
}
