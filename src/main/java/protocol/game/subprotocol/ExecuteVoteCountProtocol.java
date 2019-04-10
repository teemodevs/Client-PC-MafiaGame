package protocol.game.subprotocol;

import client.frame.game.GameFrame;
import protocol.game.GameProtocol;

import java.awt.*;


/**
 * 서버 to 클라 : 처형 투표 도중, 투표가 진행된 유저에 대해 현재 투표 수를 알려줌
 * 클라 to 서버 : -
 */
public class ExecuteVoteCountProtocol extends GameProtocol {
	private String userId;
	private int executeVoteCount;

	public String getUserId() {
		return userId;
	}

	public ExecuteVoteCountProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getExecuteVoteCount() {
		return executeVoteCount;
	}

	public ExecuteVoteCountProtocol setExecuteVoteCount(int executeVoteCount) {
		this.executeVoteCount = executeVoteCount;
		return this;
	}
	
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameFrame gameFrame = GameFrame.getInstance();
		gameFrame.appendMessageToTextPane(this.userId + "님에 대한 처형투표 : " + this.executeVoteCount + "표", Color.BLUE);
    }
}
