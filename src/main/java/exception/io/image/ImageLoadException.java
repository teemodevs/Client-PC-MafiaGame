package exception.io.image;

public class ImageLoadException extends RuntimeException {
    private String message;

    public ImageLoadException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
