package game;

import message.MessageSenderReceiver;
import protocol.Protocol;
import protocol.system.subprotocol.LoginSubSystemProtocol;

import java.net.Socket;

public class User extends Thread {
    private static User user = new User();

    private User() {}

    public static User getInstance() {
        return user;
    }
    // 메시시 송수신용
    private MessageSenderReceiver messageSenderReceiver;

    public void connect(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
    }

    @Override
    public void run() {
        while (true) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute();
        }
    }

    public void login(String id, String password) {
        Protocol protocol = new LoginSubSystemProtocol()
                                .setUserId(id)
                                .setPassword(password);
        this.messageSenderReceiver.sendMessage(protocol);
    }

}
