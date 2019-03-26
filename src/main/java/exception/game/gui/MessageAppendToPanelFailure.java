package exception.game.gui;

/**
 * 메시지 창에 문자를 붙일 때 오류
 */
public class MessageAppendToPanelFailure extends RuntimeException {
    private String message;

    public MessageAppendToPanelFailure(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
