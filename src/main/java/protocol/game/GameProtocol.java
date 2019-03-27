package protocol.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.game.subprotocol.*;

/**
 * 게임 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JobProtocol.class,    name = "JOB"),
        @JsonSubTypes.Type(value = PhaseProtocol.class,  name = "PHASE"),
        @JsonSubTypes.Type(value = ResultProtocol.class, name = "RESULT"),
        @JsonSubTypes.Type(value = JobAllocationProtocol.class,  name = "JOBALLOCATION")
})
public abstract class GameProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.GAME;
    }
}
