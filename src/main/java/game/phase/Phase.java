package game.phase;

/**
 * Phase 추상화 인터페이스
 */
public interface Phase {
    /**
     * Phase 시작 시 동작
     */
    void phaseStart();

    /**
     * Phase 종료 시 동작
     */
    void phaseEnd();
}
