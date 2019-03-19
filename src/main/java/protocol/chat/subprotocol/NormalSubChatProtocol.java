package protocol.chat.subprotocol;

import client.frame.game.GameFrame;
import protocol.chat.ChatProtocol;

public class NormalSubChatProtocol extends ChatProtocol {
    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public NormalSubChatProtocol setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public NormalSubChatProtocol setSender(String sender) {
        this.sender = sender;
        return this;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameFrame.getInstance().appendMessageToTextPane(sender + " : " + message);
    }
}
