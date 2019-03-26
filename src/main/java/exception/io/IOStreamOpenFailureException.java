package exception.io;

/**
 * IO Stream Open 실패 시 오류
 */
public class IOStreamOpenFailureException extends RuntimeException {
    private String message;

    public IOStreamOpenFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
