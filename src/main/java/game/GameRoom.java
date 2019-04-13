package game;

public class GameRoom {
    private int     roomNumber;
    private String  roomName;

    public int getRoomNumber() {
        return roomNumber;
    }

    public GameRoom setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public String getRoomName() {
        return roomName;
    }

    public GameRoom setRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }
}
