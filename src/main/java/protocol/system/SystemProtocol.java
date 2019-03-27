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
        @JsonSubTypes.Type(value = EndgameProtocol.class,   		name = "ENDGAME"),
        @JsonSubTypes.Type(value = LoginProtocol.class,     		name = "LOGIN"),
        @JsonSubTypes.Type(value = LogoutProtocol.class,    		name = "LOGOUT"),
        @JsonSubTypes.Type(value = StartgameProtocol.class, 		name = "STARTGAME"),
        @JsonSubTypes.Type(value = RoomMasterProtocol.class, 		name = "ROOMMASTER"),
        @JsonSubTypes.Type(value = StartgameFailedProtocol.class, 	name = "STARTGAMEFAIL"),
        @JsonSubTypes.Type(value = GameRoomListProtocol.class, 	name = "GAMEROOMLIST"),
        @JsonSubTypes.Type(value = JoinGameRoomProtocol.class, 	name = "JOINGAMEROOM"),
        @JsonSubTypes.Type(value = GameRoomMakeProtocol.class, 	name = "MAKEGAMEROOM"),
        @JsonSubTypes.Type(value = GameRoomUserListProtocol.class, name = "GAMEROOMUSERLIST"),
        @JsonSubTypes.Type(value = UserJoinNotifyProtocol.class, 	name = "USERJOINNOTIFY")
})
public abstract class SystemProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.SYSTEM;
    }
}
