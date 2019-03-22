package game;

public class GameContext {
	private static GameContext gameContext = new GameContext();
	private boolean isPlaying;
	
	public GameContext() {}
	
	public static GameContext getInstance() {
		return gameContext;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}


	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
}
