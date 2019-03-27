package protocol.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.chat.subprotocol.DeadChatProtocol;
import protocol.chat.subprotocol.MafiaChatProtocol;
import protocol.chat.subprotocol.NormalChatProtocol;
import protocol.chat.subprotocol.SystemChatProtocol;

/**
 * 채팅 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeadChatProtocol.class,   name = "DEAD"),
        @JsonSubTypes.Type(value = MafiaChatProtocol.class,  name = "MAFIA"),
        @JsonSubTypes.Type(value = NormalChatProtocol.class, name = "NORMAL"),
        @JsonSubTypes.Type(value = SystemChatProtocol.class, name = "SYSTEM")
})
public abstract class ChatProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.CHAT;
    }
}
