package protocol.game.subprotocol;

import client.frame.game.GameFrame;
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
		GameFrame gameFrame = GameFrame.getInstance();
		
		// 죽은 유저의 아이콘을 죽은 아이콘으로 변경
		gameFrame.setCharacterButtonImage(this.killedUserId, "img/death.png");
		
		// 죽은 유저의 버튼을 비활성화
		gameFrame.setCharacterButtonActivation(this.killedUserId, false);
	}
}
