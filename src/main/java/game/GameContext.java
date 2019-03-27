package game;

/**
 * 게임 컨텍스트 : 현재 게임 진행상황 관련 변수 모음
 */
public class GameContext {
	private static GameContext gameContext = new GameContext();
	private boolean isPlaying; // 현재 플레이 중 여부
	
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
