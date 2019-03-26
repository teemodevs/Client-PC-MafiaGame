package protocol.system.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 방장에게 게임을 시작할 수 없다고 알림
 * 클라 to 서버 : -
 */
public class StartgameFailedSubSystemProtocol extends SystemProtocol {
	private String reason;
	
	
	public String getReason() {
		return reason;
	}


	public StartgameFailedSubSystemProtocol setReason(String reason) {
		this.reason = reason;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane(this.reason, Color.BLUE);
    }
}
