package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저를 선택
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
