package exception.message;

public class MessageReadFailureException extends RuntimeException {
    private String message;

    public MessageReadFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
