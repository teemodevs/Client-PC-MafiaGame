package protocol.system.subprotocol;

import game.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 유저에 의해 만들어진 방에 대한 번호 정보를 리턴
 * 클라 to 서버 : 서버에 새로운 방 만들기 요청
 */
public class GameRoomMakeProtocol extends SystemProtocol {
    private int 	gameRoomNumber; // 만들어진 방 번호
    private String 	gameRoomName; 	// 만들어진 방 제목

    public int getGameRoomNumber() {
        return gameRoomNumber;
    }

    public GameRoomMakeProtocol setGameRoomNumber(int gameRoomNumber) {
        this.gameRoomNumber = gameRoomNumber;
        return this;
    }

    public String getGameRoomName() {
        return gameRoomName;
    }

    public GameRoomMakeProtocol setGameRoomName(String gameRoomName) {
        this.gameRoomName = gameRoomName;
        return this;
    }


    /**
     * 만들어진 게임방의 방번호를 받아서 입장을 요청 
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        Protocol protocol = new JoinGameRoomProtocol()
        						.setGameRoomNumber(this.gameRoomNumber)
                                .setGameRoomName(this.gameRoomName);
        User.getInstance().sendProtocol(protocol);
    }
}