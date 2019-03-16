package exception.game.login;

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
