package protocol.system.subprotocol;

import client.frame.GameFrame;
import client.frame.LoginFrame;
import exception.game.login.LoginFailureException;
import protocol.system.SystemProtocol;

public class LoginSubSystemProtocol extends SystemProtocol {
    private String userId;
    private String password;
    private boolean isLoginSuccess;
    private String loginFailedReason;

    public String getUserId() {
        return userId;
    }

    public LoginSubSystemProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginSubSystemProtocol setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public LoginSubSystemProtocol setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        return this;
    }

    public String getLoginFailedReason() {
        return loginFailedReason;
    }

    public LoginSubSystemProtocol setLoginFailedReason(String loginFailedReason) {
        this.loginFailedReason = loginFailedReason;
        return this;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");

        if( !this.isLoginSuccess ) {
            throw new LoginFailureException("Login Failed, reason = " + this.loginFailedReason);
        }

        LoginFrame.getInstance().setVisible(false);
        GameFrame.getInstance().boot();
    }
}
