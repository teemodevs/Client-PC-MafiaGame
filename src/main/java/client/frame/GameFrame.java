package client.frame;

import exception.game.gui.MessageAppendToPanelFailure;
import game.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    private static GameFrame gameFrame = new GameFrame();

    private JPanel mainPanel;

    /** 채팅창 **/
    private JTextField inputChatTextField; // 보낼 메세지 쓰는곳
    private JScrollPane scrollPane;
    private JButton sendMessageButton; // 전송버튼
    private JButton startButton;       // 게임 시작 버튼
    private JLabel centerLabel;        // GameFrame 중앙 라벨
    private JTextPane textPane; // 수신된 메세지를 나타낼 변수


    private GameFrame() {
        mainPanel = new JPanel();
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

    public void appendMessageToTextPane(String message) {
        StyledDocument document = (StyledDocument) this.textPane.getDocument();
        try {
            document.insertString(document.getLength(), message + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
            throw new MessageAppendToPanelFailure("Message Append to Panel Failed");
        }
    }

    public void boot() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 1000, 620);

        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        textPane.setBackground(Color.WHITE);
        textPane.setEditable(false);

        scrollPane.setBounds(696, 30, 280, 490);
        scrollPane.setViewportView(textPane);
        mainPanel.add(scrollPane);

        inputChatTextField.setBounds(696, 532, 210, 32);
        mainPanel.add(inputChatTextField);

        sendMessageButton.setBounds(916, 532, 60, 32);
        sendMessageButton.setForeground(Color.WHITE);
        sendMessageButton.setBackground(Color.BLACK);
        sendMessageButton.addActionListener(new SubmitMessageAction());
        mainPanel.add(sendMessageButton);

        startButton.setBounds(310, 350, 80, 30);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setVisible(false);
        mainPanel.add(startButton, 2);
/*
        timerLabel = new JLabel("0");
        timerLabel.setBounds(345, 240, 100, 30);
        timerLabel.setForeground(Color.YELLOW);
        mainPanel.add(timerLabel, 2);
        timerLabel.setVisible(false);

        timerLabel2 = new JLabel("남은 시간");
        timerLabel2.setBounds(325, 220, 100, 30);
        timerLabel2.setForeground(Color.YELLOW);
        mainPanel.add(timerLabel2, 2);
        timerLabel2.setVisible(false);*/

        centerLabel.setBounds(200, 200, 300, 200);
        centerLabel.setBackground(Color.red);
        centerLabel.setForeground(Color.white);
        mainPanel.add(centerLabel, 2);

        /*deathBtn = new JButton("Kill");
        deathBtn.setBounds(280, 320, 70, 30);
        deathBtn.setBackground(Color.BLACK);
        deathBtn.setForeground(Color.WHITE);
        deathBtn.setVisible(false);
        mainPanel.add(deathBtn, 2);*/

       /* liveBtn = new JButton("Save");
        liveBtn.setBounds(360, 320, 70, 30);
        liveBtn.setBackground(Color.BLACK);
        liveBtn.setForeground(Color.WHITE);
        liveBtn.setVisible(false);
        mainPanel.add(liveBtn, 2);*/

        this.addWindowListener(new CloseWindowAction());

        setVisible(true);
    }

    class SubmitMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User.getInstance().sendMessage(inputChatTextField.getText());
        }
    }

    class CloseWindowAction extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(GameFrame.getInstance(),
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                User.getInstance().logout();
                System.exit(0);
            }
        }
    }
}
