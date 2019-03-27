package exception.io.image;

/**
 * 이미지 로드 실패 시 오류
 */
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
