package client.frame;

import game.User;
import protocol.Protocol;
import protocol.system.subprotocol.GameRoomMakeProtocol;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 방만들기 창
 */
public class GameRoomCreateFrame extends JFrame {
    private JPanel      mainPanel;          // 메인 패널
    private JTextField  roomNameTextField;	// 방 제목 입력창

    private static GameRoomCreateFrame gameRoomCreateFrame = new GameRoomCreateFrame();

    public static GameRoomCreateFrame getInstance() {
        return gameRoomCreateFrame;
    }

    private GameRoomCreateFrame() {
        this.mainPanel = new JPanel();
        this.roomNameTextField = new JTextField();
    }

    void boot() {
        this.floatMainFrame();
        this.appendRoomNameTextField();
        this.appendMakeGameRoomButton();
        setVisible(true);
    }

    private void floatMainFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 200, 200);

        /* 메인 패널 설정 */
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);
    }

    private void appendRoomNameTextField() {
        JLabel idLabel = new JLabel("제목 : ");
        idLabel.setBounds(10, 20, 200, 30);
        idLabel.setForeground(Color.WHITE);
        mainPanel.add(idLabel);

        roomNameTextField.setBounds(50, 25, 100, 20);
        roomNameTextField.setBackground(Color.BLACK);
        roomNameTextField.setForeground(Color.WHITE);
        roomNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        roomNameTextField.setColumns(10);
        mainPanel.add(roomNameTextField);
    }

    private void appendMakeGameRoomButton() {
        JButton makeGameRoomButton = new JButton("방 만들기");
        makeGameRoomButton.setBounds(50, 50, 100, 20);
        //makeGameRoomButton.setBackground(Color.BLACK);
        makeGameRoomButton.setForeground(Color.RED);
        makeGameRoomButton.setBorderPainted(false);
        mainPanel.add(makeGameRoomButton);

        makeGameRoomButton.addActionListener(new GameRoomMakeAction());
    }

    /**
     * 방 만들기 버튼 액션 : 방 만들기 요청
     */
    class GameRoomMakeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(roomNameTextField.getText().equals(""))
                return;

            Protocol protocol = new GameRoomMakeProtocol()
                                    .setGameRoomName(roomNameTextField.getText());
            User.getInstance().sendProtocol(protocol);
        }
    }
}
