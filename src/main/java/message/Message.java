package message;

/**
 * 유저가 프로토콜을 전송할 때 Serialization 하여 저장할 클래스
 */
public class Message {
    private String message; // 프로토콜 포맷과 동일한 메시지

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
