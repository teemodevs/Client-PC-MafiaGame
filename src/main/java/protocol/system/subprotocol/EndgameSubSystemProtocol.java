package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import game.User;
import protocol.system.SystemProtocol;

import java.awt.*;

public class EndgameSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane("게임종료", Color.BLUE);

        if (User.getInstance().isRoomMaster())
            gameFrame.setVisibleStartButton(true);
    }
}
