package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 처형투표 찬성 요청
 */
public class ExecuteVoteAgreeProtocol extends GameProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
