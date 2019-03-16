package message;

public class Message {
    private String message; // 프로토콜 포맷과 동일한 메시지

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
