package exception.io;

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
