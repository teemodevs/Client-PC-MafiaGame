package exception.message;

/**
 * Message to Protocol Deserialization 실패 시 오류
 */
public class MessageToProtocolDeserializationFailureException extends RuntimeException {
    private String message;

    public MessageToProtocolDeserializationFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
