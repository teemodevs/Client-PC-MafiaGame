package protocol.system.subprotocol;

import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 방의 모든 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : -
 */
public class UserJoinNotifySubSystemProtocol extends SystemProtocol {
	private String userId; // 방에 참가하는 유저의 id
	
	public String getUserId() {
		return userId;
	}
	
	public UserJoinNotifySubSystemProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	@Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
