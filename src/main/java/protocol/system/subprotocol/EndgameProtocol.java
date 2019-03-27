package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import game.User;
import protocol.system.SystemProtocol;

import java.awt.*;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 끝났다고 알림
 * 클라 to 서버 : -
 */
public class EndgameProtocol extends SystemProtocol {
	
    /**
     * 게임 종료 시 클라이언트 화면 수정, 방장의 경우 버튼이 보임
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane("게임종료", Color.BLUE);

        if (User.getInstance().isRoomMaster())
            gameFrame.setVisibleStartButton(true);
    }
}
