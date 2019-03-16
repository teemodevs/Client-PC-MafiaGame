package message;

import exception.io.IOStreamCloseFailureException;
import exception.io.IOStreamOpenFailureException;
import protocol.Protocol;

import java.io.*;
import java.net.Socket;

public class MessageSenderReceiver {
    private Socket socket;

    private PrintWriter out;
    private BufferedReader in;

    public MessageSenderReceiver(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            this.close();
            throw new IOStreamOpenFailureException("IO Stream Open Failed");
        }
    }

    /**
     * 스트림으로부터 클라이언트와 통신하기 위한 포맷의 Protocol Message를 읽음
     * 쓰레드(User)는 이 메서드를 계속해서 체크한다.
     */
    public Protocol receiveMessage() {
        String bodyMessageString = null;
        try {
            bodyMessageString = in.readLine();
        } catch (IOException e) {
            this.close();
        }
        Message message = new Message(bodyMessageString);
        System.out.println("received Message : " + bodyMessageString);
        MessageConverter messageConverter = new MessageConverter();
        return messageConverter.messageToProtocol(message);
    }

    /**
     * 스트림으로 클라이언트와 통신하기 위한 포맷의 Protocol Message를 보냄
     */
    public void sendMessage(Protocol protocol) {
        MessageConverter messageConverter = new MessageConverter();
        Message message = messageConverter.protocolToMessage(protocol);
        System.out.println("sended Message : " + message.getMessage());
        out.println(message.getMessage());
    }

    /**
     * Closing IO streams
     */
    private void close() {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            throw new IOStreamCloseFailureException("IO Stream Close Failed");
        }
    }

}
