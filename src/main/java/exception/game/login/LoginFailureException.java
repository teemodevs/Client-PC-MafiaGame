package exception.game.login;

/**
 * 로그인 실패 시 오류
 */
public class LoginFailureException extends RuntimeException{
    private String message;

    public LoginFailureException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
