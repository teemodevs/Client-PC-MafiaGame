package exception.game.gui;

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
