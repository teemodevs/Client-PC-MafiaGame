package exception.io.sound;

/**
 * 사운드 로드 실패 시 오류
 */
public class SoundLoadException extends RuntimeException {
    private String message;

    public SoundLoadException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
