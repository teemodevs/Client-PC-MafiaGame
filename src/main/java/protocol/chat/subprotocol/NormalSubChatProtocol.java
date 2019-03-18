package protocol.chat.subprotocol;

import client.frame.GameFrame;
import exception.game.gui.MessageAppendToPanelFailure;
import protocol.chat.ChatProtocol;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class NormalSubChatProtocol extends ChatProtocol {
    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public NormalSubChatProtocol setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public NormalSubChatProtocol setSender(String sender) {
        this.sender = sender;
        return this;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        StyledDocument document = (StyledDocument) GameFrame.getInstance().getTextPane().getDocument();
        try {
            document.insertString(document.getLength(), this.sender + " : " + this.message, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
            throw new MessageAppendToPanelFailure("Message Append to Panel Failed");
        }
    }
}
