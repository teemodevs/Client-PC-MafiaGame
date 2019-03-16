package exception.message;

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
