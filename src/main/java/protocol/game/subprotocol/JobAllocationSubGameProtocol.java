package protocol.game.subprotocol;

import game.User;
import game.job.JobFactory;
import protocol.game.GameProtocol;

public class JobAllocationSubGameProtocol extends GameProtocol {
    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public JobAllocationSubGameProtocol setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        User user = User.getInstance();
        user.setJob(JobFactory.create(this.jobName));
        System.out.println(user.getUserId() + "'s Job : " + user.getJob().getClass().getSimpleName());
    }
}
