package protocol.game.subprotocol;

import protocol.game.GameProtocol;

public class ResultSubGameProtocol extends GameProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
