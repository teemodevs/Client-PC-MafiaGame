package client.frame.game;

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
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {
    private static final int MAX_USERS = 8;
    private static GameFrame gameFrame = new GameFrame();

    /**
     * 메인 패널
     **/
    private JPanel mainPanel;
    private JLabel centerLabel;          // GameFrame 중앙 라벨
    private List<UserFrame> userFrameList;  // 유저 패널 리스트

    /**
     * 채팅창
     **/
    private JTextField inputChatTextField; // 채팅입력창
    private JScrollPane scrollPane;    // 채팅창 스크롤 바
    private JButton sendMessageButton; // 채팅창 전송버튼
    private JTextPane textPane;        // 채팅창


    private GameFrame() {
        mainPanel = new JPanel();
        textPane = new JTextPane();
        scrollPane = new JScrollPane();
        inputChatTextField = new JTextField();
        sendMessageButton = new JButton("전송");
        centerLabel = new JLabel("환영합니다.", JLabel.CENTER);
        userFrameList = new ArrayList<>(8);

        // 8개의 유저프레임을 미리 설정해놓고 붙여놓음
        for (int index = 0; index < MAX_USERS; index++) {
            UserFrame userFrame = new UserFrame(index);
            this.userFrameList.add(userFrame);
            this.mainPanel.add(userFrame.getCharacterButton());
            this.mainPanel.add(userFrame.getIdLabel());
        }
    }

    /**
     * 게임프레임 반환
     * @return gameFrame GameFrame
     **/
    public static GameFrame getInstance() {
        return gameFrame;
    }

    /**
     * 채팅창에 메시지를 출력
     * @param message String 출력할 메시지
     **/
    public void appendMessageToTextPane(String message) {
        StyledDocument document = (StyledDocument) this.textPane.getDocument();
        try {
            document.insertString(document.getLength(), message + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
            throw new MessageAppendToPanelFailure("Message Append to Panel Failed");
        }
    }

    /**
     * 로그인 성공 시 실행, GameFrame 을 띄움
     **/
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

        centerLabel.setBounds(200, 200, 300, 200);
        centerLabel.setBackground(Color.red);
        centerLabel.setForeground(Color.white);
        mainPanel.add(centerLabel, 2);

        this.addWindowListener(new CloseWindowAction());

        setVisible(true);
    }

    private UserFrame getEmptyUserFrame() {
        for (UserFrame userFrame : this.userFrameList) {
            if (!userFrame.isLogined())
                return userFrame;
        }
        return null;
    }

    /**
     * 유저가 새로 로그인할 때 UserFrame에 할당 후 활성화
     * @param loginUserIdList String 서버 기준에서 접속한 유저의 id List
     **/
    public void attachUserFrame(List<String> loginUserIdList) {
        // 클라이언트 기준 접속한 유저의 id List
        List<String> clientUserIdList = new ArrayList<>();

        for (UserFrame userFrame : this.userFrameList) {
            if (userFrame.isLogined())
                clientUserIdList.add(userFrame.getIdLabel().getText());
        }

        // 서버 기준의 접속 id 리스트에서, 클라 기준 접속 id 리스트를 제외하여 새로 접속한 유저의 id를 구함
        loginUserIdList.removeAll(clientUserIdList);

        // 새로 접속한 유저를 UserFrame에 반영
        for (String userId : loginUserIdList) {
            UserFrame newUserFrame = getEmptyUserFrame();
            newUserFrame.getIdLabel().setText(userId);
            newUserFrame.setLogined(true);
            newUserFrame.getIdLabel().setVisible(true);
            newUserFrame.getCharacterButton().setVisible(true);
        }

    }

    /**
     * 특정 유저가 로그아웃하면 해당 유저 자리에 있는 UserFrame을
     * 다른 유저가 입장 시 할당될 수 있게 비활성화
     * @param logoutUserId String 로그아웃 한 유저의 id
     **/
    public void detachUserFrame(String logoutUserId) {
        for (UserFrame userFrame : this.userFrameList) {
            if (userFrame.isLogined() && userFrame.getIdLabel().getText().equals(logoutUserId)) {
                userFrame.setLogined(false);
                userFrame.getIdLabel().setText(null);
                userFrame.getIdLabel().setVisible(false);
                userFrame.getCharacterButton().setVisible(false);
            }
        }
    }

    /**
     * 메시지 전송 액션
     * 채팅입력창(inputChatTextField)에 써있는 문자를 서버로 전송
     **/
    class SubmitMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User.getInstance().sendMessage(inputChatTextField.getText());
        }
    }

    /**
     * 윈도우창 닫기 액션
     * 로그아웃 처리 및 프로그램 종료
     **/
    class CloseWindowAction extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(GameFrame.getInstance(),
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                User.getInstance().logout();
                System.exit(0);
            }
        }
    }
}
