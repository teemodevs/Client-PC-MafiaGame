package game;

public class GameContext {
	private static GameContext gameContext = new GameContext();
	private boolean isPlaying;
	
	public GameContext() {}
	
	public static GameContext getInstance() {
		return gameContext;
	}

	public void gameStart() {
		this.isPlaying = true;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
}