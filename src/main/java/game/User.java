package game;

import message.MessageSenderReceiver;
import protocol.Protocol;
import protocol.chat.subprotocol.NormalSubChatProtocol;
import protocol.system.subprotocol.LoginSubSystemProtocol;
import protocol.system.subprotocol.LogoutSubSystemProtocol;

import java.net.Socket;

public class User extends Thread {
    private static User user = new User();

    private String userId; // 현재 유저 id
    private MessageSenderReceiver messageSenderReceiver; // 메시시 송수신용

    private User() {}
    public static User getInstance() {
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

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

    public void logout() {
        Protocol protocol = new LogoutSubSystemProtocol().setUserId(this.userId);
        this.messageSenderReceiver.sendMessage(protocol);
    }

    public void sendMessage(String message) {
        Protocol protocol = new NormalSubChatProtocol()
                                .setSender(this.getUserId())
                                .setMessage(message);
        this.messageSenderReceiver.sendMessage(protocol);
    }

}
