package exception.game;

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
