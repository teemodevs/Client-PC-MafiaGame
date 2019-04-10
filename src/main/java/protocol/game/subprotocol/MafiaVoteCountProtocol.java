package protocol.game.subprotocol;

import client.frame.game.GameFrame;
import protocol.game.GameProtocol;

import java.awt.*;


/**
 * 서버 to 클라 : 마피아 투표 도중, 투표가 진행된 유저에 대해 현재 투표 수를 알려줌
 * 클라 to 서버 : -
 */
public class MafiaVoteCountProtocol extends GameProtocol {
	private String userId;
	private int mafiaVoteCount;

	public String getUserId() {
		return userId;
	}

	public MafiaVoteCountProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getMafiaVoteCount() {
		return mafiaVoteCount;
	}

	public MafiaVoteCountProtocol setMafiaVoteCount(int mafiaVoteCount) {
		this.mafiaVoteCount = mafiaVoteCount;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameFrame gameFrame = GameFrame.getInstance();
		gameFrame.appendMessageToTextPane(this.userId + "님에 대한 마피아 투표 : " + this.mafiaVoteCount + "표", Color.BLUE);
    }
}
