package protocol.system.subprotocol;

import client.frame.LoginFrame;
import protocol.system.SystemProtocol;

public class ServerConnectionSubSystemProtocol extends SystemProtocol {
    private boolean isConnected;

    public boolean getIsConnected() {
        return isConnected;
    }

    public ServerConnectionSubSystemProtocol setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
        return this;
    }

    @Override
    public void execute() {
        if (isConnected)
            LoginFrame.getInstance().boot();
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
