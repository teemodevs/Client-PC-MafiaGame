package network;

import client.frame.LoginFrame;
import exception.network.ServerConnectionFailureException;
import game.User;

import java.io.IOException;
import java.net.Socket;

/**
 * 서버와 접속을 담당하는 클래스
 */
public class ServerConnector {
    private final static String IP_ADDRESS = "127.0.0.1"; 	// 서버 접속 IP
    private final static int 	PORT 	   = 30000;    		// 서버 접속 포트

    public void connect() {
        User user = User.getInstance();
        user.connect(this.createSocket());
        LoginFrame.getInstance().boot();
        user.run();
    }

    /**
     * 소켓을 생성하여 반환
     * @return socket Socket
     */
    private Socket createSocket() {
        try {
            return new Socket(IP_ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerConnectionFailureException("Server Connection Failed");
        }
    }
}
