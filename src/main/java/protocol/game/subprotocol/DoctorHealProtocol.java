package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 의사 힐 성공 알림 프로토콜
 * 서버 to 클라 : 모든 유저에게 의사가 힐을 성공했다고 알림
 * 클라 to 서버 : -
 */
public class DoctorHealProtocol extends GameProtocol {

	private String healUserId; // 치료된 유저

	public String getHealUserId() {
		return healUserId;
	}

	public DoctorHealProtocol setHealUserId(String healUserId) {
		this.healUserId = healUserId;
		return this;
	}

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}
}