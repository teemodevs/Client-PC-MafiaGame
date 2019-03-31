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
        @JsonSubTypes.Type(value = EndgameProtocol.class,   		name = "GAME_OVER"),
        @JsonSubTypes.Type(value = LoginProtocol.class,     		name = "USER_LOGIN"),
        @JsonSubTypes.Type(value = LogoutProtocol.class,    		name = "USER_LOGOUT"),
        @JsonSubTypes.Type(value = StartgameProtocol.class, 		name = "GAME_START"),
        @JsonSubTypes.Type(value = RoomMasterProtocol.class, 		name = "GAME_ROOM_MASTER"),
        @JsonSubTypes.Type(value = StartgameFailedProtocol.class, 	name = "GAME_START_FAIL"),
        @JsonSubTypes.Type(value = GameRoomListProtocol.class, 	    name = "GAME_ROOM_LIST"),
        @JsonSubTypes.Type(value = JoinGameRoomProtocol.class, 	    name = "GAME_ROOM_JOIN"),
        @JsonSubTypes.Type(value = GameRoomMakeProtocol.class, 	    name = "GAME_ROOM_MAKE"),
        @JsonSubTypes.Type(value = GameRoomUserListProtocol.class,  name = "GAME_ROOM_USER_LIST"),
        @JsonSubTypes.Type(value = UserJoinNotifyProtocol.class, 	name = "USER_JOIN")
})
public abstract class SystemProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.SYSTEM;
    }
}
