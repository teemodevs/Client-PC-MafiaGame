package message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.message.MessageToProtocolDeserializationFailureException;
import exception.message.ProtocolToMessageSerializationFailureException;
import protocol.Protocol;
import protocol.ProtocolType;

import java.io.IOException;

/**
 * Message와 Protocol 객체 변환 클래스
 */
public class MessageConverter {
    private ObjectMapper objectMapper;

    public MessageConverter() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Message 객체를 Protocol 객체료 변환 (Deserialization)
     * @param message Message Deserialize 할 JSON String을 가지고 있는 Message 객체
     * @return protocol Protocol Message를 Deserialize하여 생성된 Protocol 객체 
     */
    public Protocol messageToProtocol(Message message) {
        try {
            JsonNode protocolJsonNode = objectMapper.readTree(message.getMessage());
            // 실제 메시지에서 "protocol"을 키 값으로 하는 JSON Node를 얻어서 ProtocolType을 획득
            JsonNode typeJsonNode = protocolJsonNode.get("protocol");
            String stringProtocolType = typeJsonNode.textValue();
            ProtocolType protocolType = ProtocolType.valueOf(stringProtocolType);
            return (Protocol) objectMapper.readValue(message.getMessage(), protocolType.getProtocolType());
        } catch (IOException e) {
            throw new MessageToProtocolDeserializationFailureException("Mesaage to Protocol Deserialization Failure");
        }
    }

    /**
     * Protocol 객체를 Message 객체료 변환 (Serialization)
     * @param protocol Protocol Serialize 할 Protocol 객체
     * @return message Message Protocol을 Serialize하여 생성된 JSON String을 가지고 있는 Message 객체 
     */
    public Message protocolToMessage(Protocol protocol) {
        try {
            return new Message(objectMapper.writeValueAsString(protocol));
        } catch (JsonProcessingException e) {
            throw new ProtocolToMessageSerializationFailureException("Protocol to Mesaage Serailization Failure");
        }
    }
}
