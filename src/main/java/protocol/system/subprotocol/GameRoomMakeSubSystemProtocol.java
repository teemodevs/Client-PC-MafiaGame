package protocol.system.subprotocol;

import game.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 유저에 의해 만들어진 방에 대한 번호 정보를 리턴
 * 클라 to 서버 : 서버에 새로운 방 만들기 요청
 */
public class GameRoomMakeSubSystemProtocol extends SystemProtocol {
	
	private int newGameRoomNumber;	
	
    public int getNewGameRoomNumber() {
		return newGameRoomNumber;
	}

	public void setNewGameRoomNumber(int newGameRoomNumber) {
		this.newGameRoomNumber = newGameRoomNumber;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        Protocol protocol = new JoinGameRoomSubSystemProtocol()
        						.setGameRoomNumber(this.newGameRoomNumber);
        User.getInstance().sendProtocol(protocol);
    }
}