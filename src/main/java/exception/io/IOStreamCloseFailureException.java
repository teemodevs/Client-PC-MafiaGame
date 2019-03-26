package exception.io;

/**
 * IO Stream Close 실패 시 오류
 */
public class IOStreamCloseFailureException extends RuntimeException {
    private String message;

    public IOStreamCloseFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
