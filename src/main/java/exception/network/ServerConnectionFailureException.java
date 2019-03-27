package exception.network;

/**
 * Server Connection 실패 시 오류
 */
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
