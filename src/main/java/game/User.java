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

    /**
     * @return userId String 현재 유저의 Id
     * 현재 유저의 Id를 반환
     **/
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId String 현재 접속한 유저의 Id
     * @return this User 현재 User 객체
     * 로그인 시 현재 유저의 Id를 저장
     **/
    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * @param socket Socket IP, PORT 정보가 바인딩 된, 연결할 소켓
     * 소켓 정보를 받아서 messageSenderReceiver의 socket field에 저장
     * **/
    public void connect(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
    }

    /**
     * messageSenderReceiver를 사용하여 서버에서 전송된 메시지를 계속해서 수신
     * 받은 메시지는 로직 수행에 필요한 정보가 담겨있는 Protocol Type으로 변환되고 이를 수행함
     * **/
    @Override
    public void run() {
        while (true) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute();
        }
    }

    /**
     * @param id String 로그인을 시도할 id
     * @param password String 로그인을 시도할 password
     * 서버에 로그인 요청을 함
     * **/
    public void login(String id, String password) {
        Protocol protocol = new LoginSubSystemProtocol()
                                .setUserId(id)
                                .setPassword(password);
        this.userId = id;
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * 서버에 로그아웃 요청을 보냄
     * **/
    public void logout() {
        Protocol protocol = new LogoutSubSystemProtocol().setUserId(this.userId);
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * @param message String 보낼 메시지의 내용(json 아님)
     * message의 내용을 서버에 보냄
     * **/
    public void sendMessage(String message) {
        Protocol protocol = new NormalSubChatProtocol()
                                .setSender(this.getUserId())
                                .setMessage(message);
        this.messageSenderReceiver.sendMessage(protocol);
    }

}
