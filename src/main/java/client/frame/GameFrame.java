package client.frame;

import game.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private static GameFrame gameFrame = new GameFrame();

    private JPanel contentPane;

    /** 채팅창 **/
    private JTextField inputChatTextField; // 보낼 메세지 쓰는곳
    private JScrollPane scrollPane;
    private JButton sendMessageButton; // 전송버튼
    private JButton startButton;       // 게임 시작 버튼
    private JLabel centerLabel;        // GameFrame 중앙 라벨
    private JTextPane textPane; // 수신된 메세지를 나타낼 변수


    private GameFrame() {
        textPane = new JTextPane();
        scrollPane = new JScrollPane();
        inputChatTextField = new JTextField();
        sendMessageButton = new JButton("전송");
        startButton = new JButton("게임시작");
        centerLabel = new JLabel("환영합니다.", JLabel.CENTER);
    }

    public static GameFrame getInstance() {
        return gameFrame;
    }

    public JTextPane getTextPane() {
        return this.textPane;
    }

    public void boot() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 1000, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        textPane.setBackground(Color.WHITE);
        textPane.setEditable(false);

        scrollPane.setBounds(696, 30, 280, 490);
        scrollPane.setViewportView(textPane);
        contentPane.add(scrollPane);

        inputChatTextField.setBounds(696, 532, 210, 32);
        contentPane.add(inputChatTextField);

        sendMessageButton.setBounds(916, 532, 60, 32);
        sendMessageButton.setForeground(Color.WHITE);
        sendMessageButton.setBackground(Color.BLACK);
        sendMessageButton.addActionListener(new SubmitMessageAction());
        contentPane.add(sendMessageButton);

        startButton.setBounds(310, 350, 80, 30);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setVisible(false);
        contentPane.add(startButton, 2);
/*
        timerLabel = new JLabel("0");
        timerLabel.setBounds(345, 240, 100, 30);
        timerLabel.setForeground(Color.YELLOW);
        contentPane.add(timerLabel, 2);
        timerLabel.setVisible(false);

        timerLabel2 = new JLabel("남은 시간");
        timerLabel2.setBounds(325, 220, 100, 30);
        timerLabel2.setForeground(Color.YELLOW);
        contentPane.add(timerLabel2, 2);
        timerLabel2.setVisible(false);*/

        centerLabel.setBounds(200, 200, 300, 200);
        centerLabel.setBackground(Color.red);
        centerLabel.setForeground(Color.white);
        contentPane.add(centerLabel, 2);

        /*deathBtn = new JButton("Kill");
        deathBtn.setBounds(280, 320, 70, 30);
        deathBtn.setBackground(Color.BLACK);
        deathBtn.setForeground(Color.WHITE);
        deathBtn.setVisible(false);
        contentPane.add(deathBtn, 2);*/

       /* liveBtn = new JButton("Save");
        liveBtn.setBounds(360, 320, 70, 30);
        liveBtn.setBackground(Color.BLACK);
        liveBtn.setForeground(Color.WHITE);
        liveBtn.setVisible(false);
        contentPane.add(liveBtn, 2);*/

        setVisible(true);
    }

    class SubmitMessageAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User.getInstance().sendMessage(inputChatTextField.getText());
        }
    }
}
