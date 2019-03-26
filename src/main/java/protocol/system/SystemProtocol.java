package protocol.system;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.system.subprotocol.*;

/**
 * 시스템 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EndgameSubSystemProtocol.class,   		name = "ENDGAME"),
        @JsonSubTypes.Type(value = LoginSubSystemProtocol.class,     		name = "LOGIN"),
        @JsonSubTypes.Type(value = LogoutSubSystemProtocol.class,    		name = "LOGOUT"),
        @JsonSubTypes.Type(value = StartgameSubSystemProtocol.class, 		name = "STARTGAME"),
        @JsonSubTypes.Type(value = RoomMasterSubSystemProtocol.class, 		name = "ROOMMASTER"),
        @JsonSubTypes.Type(value = StartgameFailedSubSystemProtocol.class, 	name = "STARTGAMEFAIL"),
        @JsonSubTypes.Type(value = GameRoomListSubSystemProtocol.class, 	name = "GAMEROOMLIST"),
        @JsonSubTypes.Type(value = JoinGameRoomSubSystemProtocol.class, 	name = "JOINGAMEROOM"),
        @JsonSubTypes.Type(value = GameRoomMakeSubSystemProtocol.class, 	name = "MAKEGAMEROOM")
})
public abstract class SystemProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.SYSTEM;
    }
}
