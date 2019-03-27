package protocol.system.subprotocol;

import client.frame.LobbyFrame;
import client.frame.game.GameFrame;
import exception.game.JoinGameRoomFailureException;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 특정 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : 특정 유저가 방에 입장하는 것을 요청
 */
public class JoinGameRoomProtocol extends SystemProtocol {
	private String 			userId;				// 방에 참가하는 유저의 id
	private int 			gameRoomNumber;		// 참가하려는 방의 번호
	private boolean 		isJoinSuccess;		// 참가 성공 여부
	private String 			joinFailedReason;	// 참가 실패 시 사유
	
	public String getUserId() {
		return userId;
	}
	
	public JoinGameRoomProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getGameRoomNumber() {
		return gameRoomNumber;
	}

	public JoinGameRoomProtocol setGameRoomNumber(int gameRoomNumber) {
		this.gameRoomNumber = gameRoomNumber;
		return this;
	}

	public boolean isJoinSuccess() {
		return isJoinSuccess;
	}

	public JoinGameRoomProtocol setJoinSuccess(boolean isJoinSuccess) {
		this.isJoinSuccess = isJoinSuccess;
		return this;
	}

	public String getJoinFailedReason() {
		return joinFailedReason;
	}

	public JoinGameRoomProtocol setJoinFailedReason(String joinFailedReason) {
		this.joinFailedReason = joinFailedReason;
		return this;
	}

    /**
     *  방에 입장 성공 시 클라이언트 화면을 LobbyFrame에서 GameFrame으로 변경
     */
	@Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        if(!this.isJoinSuccess) {
        	throw new JoinGameRoomFailureException(this.joinFailedReason);
        }
        
        LobbyFrame.getInstance().setVisible(false);
        
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.boot();
        
        //gameFrame.appendMessageToTextPane(this.userId + " login", Color.BLUE);
        //gameFrame.appendMessageToTextPane("login users : " + this.loginUsers.toString(), Color.BLUE);
        
        //gameFrame.attachUserFrame(this.loginUsers);
    }
    
    
}
