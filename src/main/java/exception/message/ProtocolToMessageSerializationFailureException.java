package exception.message;

/**
 * Protocol to Message Serialization 실패 시 오류
 */
public class ProtocolToMessageSerializationFailureException extends RuntimeException {
    private String message;

    public ProtocolToMessageSerializationFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
