package protocol.game.subprotocol;

import game.phase.Phase;
import game.phase.PhaseEnum;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 Phase 변경을 통보
 * 클라 to 서버 : -
 */
public class PhaseChangeProtocol extends GameProtocol {
    private String phaseName;

    public PhaseChangeProtocol setPhaseName(String phaseName) {
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
        Phase phase = PhaseEnum.valueOf(this.phaseName.toUpperCase()).getPhase();
        phase.phaseStart();
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
