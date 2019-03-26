package exception.message;

/**
 * Message Write 실패 시 오류
 */
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
