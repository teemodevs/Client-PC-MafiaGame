package protocol.system.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 방장에게 게임을 시작할 수 없다고 알림
 * 클라 to 서버 : -
 */
public class StartgameFailedProtocol extends SystemProtocol {
	private String reason;
	
	
	public String getReason() {
		return reason;
	}


	public StartgameFailedProtocol setReason(String reason) {
		this.reason = reason;
		return this;
	}
	
	/**
	 * 게임시작 불가 메시지 출력
	 */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane(this.reason, Color.BLUE);
    }
}
