package game;

/**
 * 게임 컨텍스트 : 현재 게임 진행상황 관련 변수 모음
 */
public class GameContext {
	private static GameContext gameContext = new GameContext();
	private boolean playing; // 현재 플레이 중 여부
	private boolean alive;   // 현재 생존 여부
	
	public GameContext() {}
	
	public static GameContext getInstance() {
		return gameContext;
	}

	public void gameStart() {
		this.playing = true;
	}
	
	public boolean isPlaying() {
		return this.playing;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
}
