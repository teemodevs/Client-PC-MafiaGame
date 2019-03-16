package protocol.chat.subprotocol;

import protocol.chat.ChatProtocol;

public class NormalSubChatProtocol extends ChatProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
