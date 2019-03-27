package protocol.system.subprotocol;

import java.util.List;

import client.frame.LobbyFrame;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 해당 유저에게 접속 가능한 GameRoom의 리스트를 반환
 * 클라 to 서버 : 현재 서버에 접속 가능한 GameRoom의 리스트를 요청
 */
public class GameRoomListSubSystemProtocol extends SystemProtocol {
	private List<Integer> gameRoomNumberList;
	
	public List<Integer> getGameRoomNumberList() {
		return gameRoomNumberList;
	}


	public void setGameRoomNumberList(List<Integer> gameRoomNumberList) {
		this.gameRoomNumberList = gameRoomNumberList;
	}
	
    /**
     * 로비에서 접속 가능한 게임방의 리스트를 업데이트
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        LobbyFrame.getInstance().updateGameRoomList(this.gameRoomNumberList);
    }
}