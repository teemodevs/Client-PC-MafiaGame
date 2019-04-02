package game.phase;

import client.frame.game.GameFrame;

import java.awt.*;

/**
 * 밤 Phase
 */
public class NightPhase implements Phase {
    private static NightPhase nightPhase = new NightPhase();

    private NightPhase() {}

    public static NightPhase getInstance() {
        return nightPhase;
    }

    @Override
    public void phaseStart() {
        GameFrame.getInstance().appendMessageToTextPane("밤이 되었습니다.", Color.BLUE);
    }

    @Override
    public void phaseEnd() {

    }
}
