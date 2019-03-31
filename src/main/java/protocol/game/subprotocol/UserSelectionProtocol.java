package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * Night Phase 에서 상대 유저 선택 시 직업 행동
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저에 대한 직업 행동을 요청
 */
public class UserSelectionProtocol extends GameProtocol {
	private String target;
	
    public String getTarget() {
		return target;
	}

	public UserSelectionProtocol setTarget(String target) {
		this.target = target;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
