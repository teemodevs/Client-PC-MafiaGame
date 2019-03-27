package protocol.game.subprotocol;

import game.User;
import game.job.JobFactory;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 유저에게 할당된 직업을 통보
 * 클라 to 서버 : -
 */
public class JobAllocationSubGameProtocol extends GameProtocol {
    private String jobName; // 직업명

    public String getJobName() {
        return jobName;
    }

    public JobAllocationSubGameProtocol setJobName(String jobName) {
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
        System.out.println(user.getUserId() + "'s Job : " + user.getJob().getClass().getSimpleName());
    }
}
