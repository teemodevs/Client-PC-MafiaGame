package protocol.system.subprotocol;

import client.frame.LobbyFrame;
import client.frame.LoginFrame;
import exception.game.login.LoginFailureException;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 다른 유저가 로그인 한 정보를 알림
 * 클라 to 서버 : 해당 유저가 로그인을 요청
 */
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

        if( !this.isLoginSuccess )
            throw new LoginFailureException("Login Failed, reason = " + this.loginFailedReason);
        
        // 로그인창 비활성화
        LoginFrame.getInstance().setVisible(false);
        
        // 로비창 활성화
        LobbyFrame lobbyFrame = LobbyFrame.getInstance();
        lobbyFrame.boot();
    }
}
