package exception.message;

/**
 * Message Read 실패 시 오류
 */
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
