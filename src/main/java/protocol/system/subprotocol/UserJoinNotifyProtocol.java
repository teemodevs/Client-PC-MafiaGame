package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import game.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

import java.awt.*;

/**
 * 서버 to 클라 : 특정 방의 모든 유저에게 특정 유저가 방에 입장했다고 알림
 * 클라 to 서버 : -
 */
public class UserJoinNotifyProtocol extends SystemProtocol {
	private String userId; // 방에 참가하는 유저의 id
	
	public String getUserId() {
		return userId;
	}
	
	public UserJoinNotifyProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * 해당 유저가 들어가 있는 게임방에 특정 유저가 접속하면 알림을 받음
	 * 해당 게임방의 유저 리스트를 받아와서 화면을 갱신
	 */
	@Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameFrame.getInstance().appendMessageToTextPane(this.userId + "님이 입장하셨습니다.", Color.BLUE);

		// 새로운 유저 입장 시 유저 리스트를 요청
		Protocol protocol = new GameRoomUserListProtocol();
		User.getInstance().sendProtocol(protocol);
    }
}
