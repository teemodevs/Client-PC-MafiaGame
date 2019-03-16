package protocol.system.subprotocol;

import protocol.system.SystemProtocol;

public class StartgameSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
