package client.frame;

import javax.swing.*;

public class GameFrame extends JFrame {
    private static GameFrame gameFrame = new GameFrame();

    private GameFrame() {}

    public static GameFrame getInstance() {
        return gameFrame;
    }

    public void boot() {
        System.out.println("GameFrame.boot()");
    }
}
