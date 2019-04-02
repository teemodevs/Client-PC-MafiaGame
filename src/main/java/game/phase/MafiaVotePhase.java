package game.phase;

import client.frame.game.GameFrame;

import java.awt.*;

/**
 * 마피아 투표 Phase
 */
public class MafiaVotePhase implements Phase {
    private static MafiaVotePhase mafiaVotePhase = new MafiaVotePhase();

    private MafiaVotePhase() {}

    public static MafiaVotePhase getInstance() {
        return mafiaVotePhase;
    }

    @Override
    public void phaseStart() {
        GameFrame.getInstance().appendMessageToTextPane("마피아 투표를 시작합니다.", Color.BLUE);
    }

    @Override
    public void phaseEnd() {

    }
}
