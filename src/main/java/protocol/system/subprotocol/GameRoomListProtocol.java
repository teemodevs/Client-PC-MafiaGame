package protocol.system.subprotocol;

import client.frame.LobbyFrame;
import protocol.system.SystemProtocol;

import java.util.Map;

/**
 * 서버 to 클라 : 해당 유저에게 접속 가능한 GameRoom의 리스트를 반환
 * 클라 to 서버 : 현재 서버에 접속 가능한 GameRoom의 리스트를 요청
 */
public class GameRoomListProtocol extends SystemProtocol {
	private Map<Integer, String> gameRoomMap; // 입장 가능한 게임방의 리스트

	public Map<Integer, String> getGameRoomMap() {
		return gameRoomMap;
	}

	public void setGameRoomMap(Map<Integer, String> gameRoomMap) {
		this.gameRoomMap = gameRoomMap;
	}

    /**
     * 로비에서 접속 가능한 게임방의 리스트를 업데이트
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        LobbyFrame.getInstance().updateGameRoomList(this.gameRoomMap);
    }
}