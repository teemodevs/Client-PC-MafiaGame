package game;

import game.job.Job;
import message.MessageSenderReceiver;
import protocol.Protocol;
import protocol.chat.subprotocol.NormalSubChatProtocol;
import protocol.game.subprotocol.JobSubGameProtocol;
import protocol.system.subprotocol.LoginSubSystemProtocol;
import protocol.system.subprotocol.LogoutSubSystemProtocol;
import protocol.system.subprotocol.StartgameSubSystemProtocol;

import java.net.Socket;

public class User extends Thread {
    private static User user = new User();
    private boolean isRoomMaster;
    private String userId; // 현재 유저 id
    private Job job; // 현재 유저 직업
    private MessageSenderReceiver messageSenderReceiver; // 메시시 송수신용

    private User() {}

    public static User getInstance() {
        return user;
    }

    /**
     * 현재 유저의 Id를 반환
     * @return userId String 현재 유저의 Id
     **/
    public String getUserId() {
        return userId;
    }

    /**
     * 로그인 시 현재 유저의 Id를 저장
     * @param userId String 현재 접속한 유저의 Id
     * @return this User 현재 User 객체
     **/
    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 현재 유저의 Job을 구함
     * @return job Job 현재 유저의 직업
     **/
    public Job getJob() {
        return job;
    }

    /**
     * 현재 유저의 Job을 세팅
     * @param job Job 현재 유저가 가질 직업
     **/
    public User setJob(Job job) {
        this.job = job;
        return this;
    }

    /**
     * 현재 유저 방장 여부 반환
     * @return isRoomMaster boolean 현재 유저 방장 여부
     **/
    public boolean isRoomMaster() {
		return isRoomMaster;
	}

    /**
     * 현재 유저를 방장으로 지정/해제
     * @param isRoomMaster boolean 방장 지정/해제
     **/
	public User setRoomMaster(boolean isRoomMaster) {
		this.isRoomMaster = isRoomMaster;
		return this;
	}

	/**
     * 소켓 정보를 받아서 messageSenderReceiver의 socket field에 저장
     * @param socket Socket IP, PORT 정보가 바인딩 된, 연결할 소켓
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
     * 서버에 로그인 요청을 함
     * @param id String 로그인을 시도할 id
     * @param password String 로그인을 시도할 password
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
     **/
    public void logout() {
        Protocol protocol = new LogoutSubSystemProtocol().setUserId(this.userId);
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * message의 내용을 서버에 보냄
     * @param message String 보낼 메시지의 내용(json 아님)
     **/
    public void sendMessage(String message) {
        Protocol protocol = new NormalSubChatProtocol()
                                .setSender(this.getUserId())
                                .setMessage(message);
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * (방장 User 전용) 서버에 게임시작 요청
     **/
    public void startGame() {
    	Protocol protocol = new StartgameSubSystemProtocol();
    	this.messageSenderReceiver.sendMessage(protocol);
    }
    
    /**
     * 선택한 유저에 대해 직업 행동을 요청
     **/
    public void jobAction(String targetUserId) {
    	Protocol protocol = new JobSubGameProtocol()
    							.setTargetUserId(targetUserId);
    	this.messageSenderReceiver.sendMessage(protocol);
    }
    
    /**
     * 프로토콜 전송
     **/
    public void sendProtocol(Protocol protocol) {
    	this.messageSenderReceiver.sendMessage(protocol);
    }

}
