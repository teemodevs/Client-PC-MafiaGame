package exception.game;

/**
 * GameRoom 입장 실패 시 오류
 */
public class JoinGameRoomFailureException extends RuntimeException {
	private String message;
	
	public JoinGameRoomFailureException(String message) {
		this.message = message;
	} 
	
	@Override
	public String toString() {
		return this.message;
	}

}
