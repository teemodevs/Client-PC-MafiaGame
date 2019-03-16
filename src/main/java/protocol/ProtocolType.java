package protocol;

import protocol.chat.ChatProtocol;
import protocol.game.GameProtocol;
import protocol.system.SystemProtocol;

public enum ProtocolType {
    CHAT(ChatProtocol.class),
    GAME(GameProtocol.class),
    SYSTEM(SystemProtocol.class);

    private Class object;

    ProtocolType(Class object) {
        this.object = object;
    }

    public Class getProtocolType() {
        return this.object;
    }

}