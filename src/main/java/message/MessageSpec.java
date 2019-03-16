package message;

public enum MessageSpec {
    HEADER;

    public int getLength() {
        return 5;
    }
}
