package protocol.game.subprotocol;

import client.frame.game.GameFrame;
import protocol.game.GameProtocol;

import java.awt.*;

/**
 * 서버 to 클라 : 모든 유저에게 Phase 변경을 통보
 * 클라 to 서버 : -
 */
public class PhaseSubGameProtocol extends GameProtocol {
    private String phaseName;

    public PhaseSubGameProtocol setPhaseName(String phaseName) {
        this.phaseName = phaseName;
        return this;
    }

    public String getPhaseName() {
        return this.phaseName;
    }

    /**
     * 변경된 Phase에 맞게 클라이언트 화면 구성
     */
    @Override
    public void execute() {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane(this.phaseName + "입니다", Color.BLUE);
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
