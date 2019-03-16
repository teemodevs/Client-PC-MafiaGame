package network;

import client.frame.LoginFrame;
import exception.network.ServerConnectionFailureException;
import game.User;

import java.io.IOException;
import java.net.Socket;

public class ServerConnector {
    // 서버 접속 IP
    private final static String IP_ADDRESS = "127.0.0.1";

    // 서버 접속 포트
    private final static int PORT = 30000;

    public void connect() {
        User user = User.getInstance();
        user.connect(this.createSocket());
        LoginFrame.getInstance().boot();
        user.run();
    }

    /**
     * create Socket
     *
     * @return Socket
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
