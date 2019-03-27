package protocol.system.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 다른 유저가 로그아웃 한 정보를 알림
 * 클라 to 서버 : 해당 유저가 로그아웃을 요청
 */
public class LogoutProtocol extends SystemProtocol {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public LogoutProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    /**
     * 로그아웃 결과를 클라이언트에 반영
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.appendMessageToTextPane(userId + " logout", Color.BLUE);
        gameFrame.detachUserFrame(userId);

    }
}
