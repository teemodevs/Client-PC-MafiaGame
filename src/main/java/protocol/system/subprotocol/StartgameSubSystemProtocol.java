package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import game.GameContext;
import game.User;
import protocol.system.SystemProtocol;

public class StartgameSubSystemProtocol extends SystemProtocol {
	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameFrame gameFrame = GameFrame.getInstance();
		gameFrame.appendMessageToTextPane("Game Statred");
		GameContext.getInstance().setPlaying(true);
		if (User.getInstance().isRoomMaster())
			gameFrame.setVisibleStartButton(false);
	}
}
