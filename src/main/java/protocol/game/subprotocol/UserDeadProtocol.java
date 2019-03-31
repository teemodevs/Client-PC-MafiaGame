package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 죽은 유저를 알림
 * 서버 to 클라 : 모든 유저에게 특정 유저가 죽었다고 알림
 * 클라 to 서버 : -
 */
public class UserDeadProtocol extends GameProtocol {

	private String killedUserId; // 죽은 유저

	public String getKilledUserId() {
		return killedUserId;
	}

	public UserDeadProtocol setKilledUserId(String killedUserId) {
		this.killedUserId = killedUserId;
		return this;
	}

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}
}
