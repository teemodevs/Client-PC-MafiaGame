package protocol.system.subprotocol;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.frame.game.GameFrame;
import game.User;
import protocol.system.SystemProtocol;

public class RoomMasterSubSystemProtocol extends SystemProtocol {
	private String masterId;
	
    public String getMasterId() {
		return masterId;
	}

	public RoomMasterSubSystemProtocol setMasterId(String masterId) {
		this.masterId = masterId;
		return this;
	}
	
	/**
	 * 방장 선정 및 start button을 게임 프레임에 붙임
	 **/
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane("your are master");
        gameFrame.addStartButton();
		
        
        System.out.println(this.masterId);
    }
    
    
}
