package protocol.system.subprotocol;

import java.awt.Color;
import java.util.List;

import client.frame.LobbyFrame;
import client.frame.game.GameFrame;
import exception.game.JoinGameRoomFailureException;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 특정 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : 특정 유저가 방에 입장하는 것을 요청
 */
public class JoinGameRoomSubSystemProtocol extends SystemProtocol {
	private String userId;
	private int gameRoomNumber;
	private boolean isJoinSuccess;
	private String joinFailedReason;
	private List<String> loginUsers; // 이미 로그인한 유저의 리스트
	
	
	public String getUserId() {
		return userId;
	}


	public JoinGameRoomSubSystemProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}


	public int getGameRoomNumber() {
		return gameRoomNumber;
	}


	public JoinGameRoomSubSystemProtocol setGameRoomNumber(int gameRoomNumber) {
		this.gameRoomNumber = gameRoomNumber;
		return this;
	}


	public boolean isJoinSuccess() {
		return isJoinSuccess;
	}


	public JoinGameRoomSubSystemProtocol setJoinSuccess(boolean isJoinSuccess) {
		this.isJoinSuccess = isJoinSuccess;
		return this;
	}


	public String getJoinFailedReason() {
		return joinFailedReason;
	}


	public JoinGameRoomSubSystemProtocol setJoinFailedReason(String joinFailedReason) {
		this.joinFailedReason = joinFailedReason;
		return this;
	}


	public List<String> getLoginUsers() {
		return loginUsers;
	}


	public JoinGameRoomSubSystemProtocol setLoginUsers(List<String> loginUsers) {
		this.loginUsers = loginUsers;
		return this;
	}


	@Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        if(!this.isJoinSuccess) {
        	throw new JoinGameRoomFailureException(this.joinFailedReason);
        }
        
        LobbyFrame.getInstance().setVisible(false);
        
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.boot();
        
        gameFrame.appendMessageToTextPane(this.userId + " login", Color.BLUE);
        gameFrame.appendMessageToTextPane("login users : " + this.loginUsers.toString(), Color.BLUE);
        
        gameFrame.attachUserFrame(this.loginUsers);
    }
    
    
}
