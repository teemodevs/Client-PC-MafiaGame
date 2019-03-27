package protocol.system.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import game.GameContext;
import game.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 시작됐다고 알림
 * 클라 to 서버 : 게임시작요청(RoomMaster만 요청 가능)
 */
public class StartgameProtocol extends SystemProtocol {
	
	/**
	 * 게임시작 요청
	 */
	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");

		GameFrame gameFrame = GameFrame.getInstance();
		gameFrame.appendMessageToTextPane("Game Started", Color.BLUE);

		GameContext.getInstance().gameStart();

		if (User.getInstance().isRoomMaster())
			gameFrame.setVisibleStartButton(false);
	}
}
