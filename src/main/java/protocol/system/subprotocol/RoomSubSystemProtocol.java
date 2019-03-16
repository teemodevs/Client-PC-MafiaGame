package protocol.system.subprotocol;

import protocol.system.SystemProtocol;

public class RoomSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
