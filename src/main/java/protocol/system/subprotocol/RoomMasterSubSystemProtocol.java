package protocol.system.subprotocol;

import client.frame.game.GameFrame;
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
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame.getInstance().appendMessageToTextPane("your are master");
        System.out.println(this.masterId);
    }
}
