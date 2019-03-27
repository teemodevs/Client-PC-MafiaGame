package protocol.game.subprotocol;

import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 게임 결과를 유저들에게 통보
 * 클라 to 서버 : -
 */
public class ResultProtocol extends GameProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
