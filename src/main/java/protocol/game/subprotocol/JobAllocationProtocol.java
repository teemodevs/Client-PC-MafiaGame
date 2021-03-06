package protocol.game.subprotocol;

import java.awt.Color;

import client.frame.game.GameFrame;
import game.User;
import game.job.JobFactory;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 유저에게 할당된 직업을 통보
 * 클라 to 서버 : -
 */
public class JobAllocationProtocol extends GameProtocol {
    private String jobName; // 직업명

    public String getJobName() {
        return jobName;
    }

    public JobAllocationProtocol setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    /**
     * 서버로부터 알림을 받은 직업을 현재 User에게 할당
     */
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        User user = User.getInstance();
        user.setJob(JobFactory.create(this.jobName));
        GameFrame.getInstance().appendMessageToTextPane(this.jobName + "입니다.", Color.BLUE);
    }
}
