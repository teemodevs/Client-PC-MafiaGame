package message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.message.MessageToProtocolDeserializationFailureException;
import exception.message.ProtocolToMessageSerializationFailureException;
import protocol.Protocol;
import protocol.ProtocolType;

import java.io.IOException;

public class MessageConverter {
    private ObjectMapper objectMapper;

    public MessageConverter() {
        this.objectMapper = new ObjectMapper();
    }

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

    public Message protocolToMessage(Protocol protocol) {
        try {
            return new Message(objectMapper.writeValueAsString(protocol));
        } catch (JsonProcessingException e) {
            throw new ProtocolToMessageSerializationFailureException("Protocol to Mesaage Serailization Failure");
        }
    }
}
