package exception.network;

public class ServerConnectionFailureException extends RuntimeException {
    private String message;

    public ServerConnectionFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
