package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

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
        gameFrame.appendMessageToTextPane(this.reason);
    }
}
