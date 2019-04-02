package game.phase;

import client.frame.game.GameFrame;

import java.awt.*;

/**
 * 처형 찬반 투표 Phase
 */
public class ExecuteVotePhase implements Phase {
    private static ExecuteVotePhase executeVotePhase = new ExecuteVotePhase();

    private ExecuteVotePhase() {}

    public static ExecuteVotePhase getInstance() {
        return executeVotePhase;
    }

    @Override
    public void phaseStart() {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane("처형투표를 시작합니다.", Color.BLUE);
        gameFrame.setKillButtonVisible(true);
        gameFrame.setSaveButtonVisible(true);
    }

    @Override
    public void phaseEnd() {

    }
}
