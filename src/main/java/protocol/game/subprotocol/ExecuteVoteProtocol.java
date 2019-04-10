package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 처형투표 찬/반 요청
 */
public class ExecuteVoteProtocol extends GameProtocol {
    private boolean agree;

    public ExecuteVoteProtocol setAgree(boolean agree) {
        this.agree = agree;
        return this;
    }

    public boolean isAgree() {
        return agree;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
