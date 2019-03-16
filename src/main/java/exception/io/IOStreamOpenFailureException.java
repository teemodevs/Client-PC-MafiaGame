package exception.io;

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
