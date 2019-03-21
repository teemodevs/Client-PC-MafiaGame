package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

public class StartgameSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane("Game Statred");
    }
}
