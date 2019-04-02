package game.phase;

import client.frame.game.GameFrame;

import java.awt.*;

/**
 * 최후의 변론 Phase
 */
public class ArgumentPhase implements Phase {
    private static ArgumentPhase argumentPhase = new ArgumentPhase();

    private ArgumentPhase() {}

    public static ArgumentPhase getInstance() {
        return argumentPhase;
    }

    @Override
    public void phaseStart() {
        GameFrame.getInstance().appendMessageToTextPane("최후의 변론입니다.", Color.BLUE);
    }

    @Override
    public void phaseEnd() {

    }
}
