package exception.message;

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
