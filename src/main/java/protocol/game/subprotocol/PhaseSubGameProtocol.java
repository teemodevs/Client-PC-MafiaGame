package protocol.game.subprotocol;

import protocol.game.GameProtocol;

public class PhaseSubGameProtocol extends GameProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
