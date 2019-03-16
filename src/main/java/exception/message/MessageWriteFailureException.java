package exception.message;

public class MessageWriteFailureException extends RuntimeException {
    private String message;

    public MessageWriteFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
