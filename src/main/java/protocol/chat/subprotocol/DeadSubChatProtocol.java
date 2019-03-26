package protocol.chat.subprotocol;

import protocol.chat.ChatProtocol;

/**
 * 서버 to 클라 : 다른 유저가 채팅한 정보를 알림 (죽은 유저에게만 전송)
 * 클라 to 서버 : 죽은 해당 유저가 채팅을 보냄
 */
public class DeadSubChatProtocol extends ChatProtocol {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
