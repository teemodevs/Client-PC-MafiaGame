package game.job;

import game.job.civil.Civil;
import game.job.civil.Doctor;
import game.job.civil.Police;
import game.job.mafia.Mafia;

/**
 * 직업 Factory
 */
public class JobFactory {
    public static Job create(String jobName) {
        switch(JobEnum.valueOf(jobName.toUpperCase())) {
            case CIVIL:
                return new Civil();
            case DOCTOR:
                return new Doctor();
            case POLICE:
                return new Police();
            case MAFIA:
                return new Mafia();
            default:
                return null;
        }
    }
}
