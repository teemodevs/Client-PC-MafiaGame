package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저에 대한 직업 행동을 요청
 */
public class JobProtocol extends GameProtocol {
	private String targetUserId;
	
    public String getTargetUserId() {
		return targetUserId;
	}

	public JobProtocol setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
