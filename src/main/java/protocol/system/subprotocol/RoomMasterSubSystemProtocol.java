package protocol.system.subprotocol;

import java.awt.Color;

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
        gameFrame.appendMessageToTextPane("your are master", Color.BLUE);
        User.getInstance().setRoomMaster(true);
        gameFrame.addStartButton();
		
        
        System.out.println(this.masterId);
    }
    
    
}
