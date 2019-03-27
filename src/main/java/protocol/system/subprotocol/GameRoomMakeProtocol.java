package protocol.system.subprotocol;

import game.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 유저에 의해 만들어진 방에 대한 번호 정보를 리턴
 * 클라 to 서버 : 서버에 새로운 방 만들기 요청
 */
public class GameRoomMakeProtocol extends SystemProtocol {
	
	private int newGameRoomNumber;	
	
    public int getNewGameRoomNumber() {
		return newGameRoomNumber;
	}

	public void setNewGameRoomNumber(int newGameRoomNumber) {
		this.newGameRoomNumber = newGameRoomNumber;
	}
	
    /**
     * 만들어진 게임방의 방번호를 받아서 입장을 요청 
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        Protocol protocol = new JoinGameRoomProtocol()
        						.setGameRoomNumber(this.newGameRoomNumber);
        User.getInstance().sendProtocol(protocol);
    }
}