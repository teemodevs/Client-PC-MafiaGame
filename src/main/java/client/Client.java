package client;

import network.ServerConnector;

public class Client {
    /**
     * EntryPoint of the MafiaGameClient
     */
    public static void main(String[] args) {
        ServerConnector serverConnector = new ServerConnector();
        serverConnector.connect();
    }
}
