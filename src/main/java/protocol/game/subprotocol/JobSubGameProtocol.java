package protocol.game.subprotocol;

import protocol.game.GameProtocol;

public class JobSubGameProtocol extends GameProtocol {
	private String targetUserId;
	
    public String getTargetUserId() {
		return targetUserId;
	}

	public JobSubGameProtocol setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
