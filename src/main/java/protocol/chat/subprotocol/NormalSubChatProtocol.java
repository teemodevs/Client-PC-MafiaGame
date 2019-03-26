package protocol.chat.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import protocol.chat.ChatProtocol;

/**
 * 서버 to 클라 : 다른 유저가 채팅한 정보를 알림 (전체 전송)
 * 클라 to 서버 : 해당 유저가 채팅을 보냄
 */
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
        GameFrame.getInstance().appendMessageToTextPane(sender + " : " + message, Color.BLACK);
    }
}
