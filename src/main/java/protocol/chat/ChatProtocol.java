package protocol.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.chat.subprotocol.DeadSubChatProtocol;
import protocol.chat.subprotocol.MafiaSubChatProtocol;
import protocol.chat.subprotocol.NormalSubChatProtocol;
import protocol.chat.subprotocol.SystemSubChatProtocol;

/**
 * 채팅 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeadSubChatProtocol.class,   name = "DEAD"),
        @JsonSubTypes.Type(value = MafiaSubChatProtocol.class,  name = "MAFIA"),
        @JsonSubTypes.Type(value = NormalSubChatProtocol.class, name = "NORMAL"),
        @JsonSubTypes.Type(value = SystemSubChatProtocol.class, name = "SYSTEM")
})
public abstract class ChatProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.CHAT;
    }
}
