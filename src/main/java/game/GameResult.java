package game;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * 게임 결과 클래스
 */
public class GameResult {
    private String winTeam;
    private Map<String, String> userJobMap = new HashMap<>(); // 유저id - 유저의 직업

    public String getWinTeam() {
        return winTeam;
    }

    public GameResult setWinTeam(String winTeam) {
        this.winTeam = winTeam;
        return this;
    }

    public Map<String, String> getUserJobMap() {
        return userJobMap;
    }

    @JsonAnySetter
    public GameResult setUserJobMap(String key, String value) {
        this.userJobMap.put(key, value);
        return this;
    }
}
