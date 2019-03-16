package protocol.chat.subprotocol;

import protocol.chat.ChatProtocol;

public class DeadSubChatProtocol extends ChatProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
