package game.phase;

import client.frame.game.GameFrame;
import game.GameContext;

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
        
        if (!GameContext.getInstance().isAlive())
        	return;

        gameFrame.setKillButtonVisible(true);
        gameFrame.setKillButtonEnable(true);
        gameFrame.setSaveButtonVisible(true);
        gameFrame.setSaveButtonEnable(true);
        
    }
}
