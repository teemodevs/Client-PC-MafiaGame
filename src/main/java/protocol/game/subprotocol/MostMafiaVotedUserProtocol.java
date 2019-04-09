package protocol.game.subprotocol;

import client.frame.game.GameFrame;
import protocol.game.GameProtocol;

import java.awt.*;

/**
 * 서버 to 클라 : 마피아 투표 직후, 투표가 유효한 경우 가장 많이 투표를 받은 유저의 Id와 투표수를 알려줌
 * 클라 to 서버 : -
 */
public class MostMafiaVotedUserProtocol extends GameProtocol {
	private String userId;
	private int mafiaVoteCount;

	public String getUserId() {
		return userId;
	}

	public MostMafiaVotedUserProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getMafiaVoteCount() {
		return mafiaVoteCount;
	}

	public MostMafiaVotedUserProtocol setMafiaVoteCount(int mafiaVoteCount) {
		this.mafiaVoteCount = mafiaVoteCount;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameFrame gameFrame = GameFrame.getInstance();
		gameFrame.appendMessageToTextPane(this.userId + "님이 " + this.mafiaVoteCount + "표로, 마피아 투표에서 가장 많이 지목당했습니다.", Color.BLUE);
    }
}
