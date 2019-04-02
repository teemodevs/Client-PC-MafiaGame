package game.phase;

import client.frame.game.GameFrame;

import java.awt.*;

/**
 * 아침 Phase
 */
public class MorningPhase implements Phase {
    private static MorningPhase morningPhase = new MorningPhase();

    private MorningPhase() {}

    public static MorningPhase getInstance() {
        return morningPhase;
    }

    @Override
    public void phaseStart() {
        GameFrame.getInstance().appendMessageToTextPane("아침이 되었습니다.", Color.BLUE);
    }

    @Override
    public void phaseEnd() {

    }
}
