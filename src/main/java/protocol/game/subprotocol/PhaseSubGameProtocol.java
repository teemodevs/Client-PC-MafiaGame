package protocol.game.subprotocol;

import client.frame.game.GameFrame;
import protocol.game.GameProtocol;

import java.awt.*;

public class PhaseSubGameProtocol extends GameProtocol {
    private String phaseName;

    public PhaseSubGameProtocol setPhaseName(String phaseName) {
        this.phaseName = phaseName;
        return this;
    }

    public String getPhaseName() {
        return this.phaseName;
    }

    @Override
    public void execute() {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane(this.phaseName + "입니다", Color.BLUE);
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
