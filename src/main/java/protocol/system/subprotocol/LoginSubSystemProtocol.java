package protocol.system.subprotocol;

import java.util.List;

import client.frame.GameFrame;
import client.frame.LoginFrame;
import exception.game.login.LoginFailureException;
import game.User;
import protocol.system.SystemProtocol;

public class LoginSubSystemProtocol extends SystemProtocol {
    private String userId;
    private String password;
    private boolean isLoginSuccess;
    private String loginFailedReason;
    private List<String> loginUsers; // 이미 로그인한 유저의 리스트

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

    public List<String> getLoginUsers() {
		return loginUsers;
	}

	public LoginSubSystemProtocol setLoginUsers(List<String> loginUsers) {
		this.loginUsers = loginUsers;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");

        if( !this.isLoginSuccess )
            throw new LoginFailureException("Login Failed, reason = " + this.loginFailedReason);
        
        User user = User.getInstance();
        
        // 자기 자신에 대한 로그인 메시지인 경우 GameFrame을 띄움
        if(user.getUserId().equals(this.userId)) {
	        user.setUserId(this.userId);
	        LoginFrame.getInstance().setVisible(false);
	        GameFrame.getInstance().boot();
        }
        
        // 특정 유저가 로그인 했다는 알림
        GameFrame.getInstance().appendMessageToTextPane(this.userId + " login");
        GameFrame.getInstance().appendMessageToTextPane("login users : " + this.loginUsers.toString());
    }
}
