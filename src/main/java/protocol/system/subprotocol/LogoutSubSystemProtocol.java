package protocol.system.subprotocol;

import client.frame.game.GameFrame;
import protocol.system.SystemProtocol;

public class LogoutSubSystemProtocol extends SystemProtocol {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public LogoutSubSystemProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame.getInstance().appendMessageToTextPane(userId + " logout");
    }
}
