package client.frame;

import game.User;
import resource.ResourceLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 맨 처음 애플리케이션을 켰을 때 로그인 하는 창
 */
public class LoginFrame extends JFrame {
    private static LoginFrame loginFrame = new LoginFrame();

    private JPanel 			contentPanel;	// 메인 프레임
    private JTextField 		idTextField;	// ID 입력창
    private JPasswordField 	pwTextField;	// PW 입력창
    
    private LoginFrame() {
        this.contentPanel = new JPanel();
        this.idTextField  = new JTextField();
        this.pwTextField  = new JPasswordField();
    }

    public static LoginFrame getInstance() {
        return loginFrame;
    }

    /**
     * 소켓 연결 성공 후 수행, LoginFrame을 띄움 
     */
    public void boot() {
        this.floatLoginFrame();
        this.appendIdTextField();
        this.appendPwTextField();
        this.appendLoginButton();
        setVisible(true);
    }

    /**
     * 로그인 창 띄움 
     */
    private void floatLoginFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 380, 400);

        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setLayout(null);
        setContentPane(contentPanel);

        JLabel titleLabel = new JLabel(ResourceLoader.getImageIconResource("img/title.png"));
        titleLabel.setFont(new Font("", Font.PLAIN, 30));
        titleLabel.setBounds(84, 26, 200, 94);
        titleLabel.setForeground(Color.RED);
        contentPanel.add(titleLabel);
    }

    /**
     * id 입력 텍스트 필드 붙임
     */
    private void appendIdTextField() {
        JLabel idLabel = new JLabel(ResourceLoader.getImageIconResource("img/id.png"));
        idLabel.setBounds(64, 140, 90, 43);
        idLabel.setForeground(Color.WHITE);
        contentPanel.add(idLabel);

        idTextField.setBounds(140, 154, 130, 21);
        idTextField.setBackground(Color.BLACK);
        idTextField.setForeground(Color.WHITE);
        idTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        idTextField.setColumns(10);
        contentPanel.add(idTextField);
    }

    /**
     * PW 입력 텍스트 필드 붙임
     */
    private void appendPwTextField() {
        JLabel passwordLabel = new JLabel(ResourceLoader.getImageIconResource("img/password.png"));
        passwordLabel.setBounds(18, 190, 177, 43);
        passwordLabel.setForeground(Color.WHITE);
        contentPanel.add(passwordLabel);

        pwTextField.setColumns(10);
        pwTextField.setBounds(140, 204, 130, 21);
        pwTextField.setBackground(Color.BLACK);
        pwTextField.setForeground(Color.WHITE);
        pwTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        contentPanel.add(pwTextField);
    }

    /**
     * 로그인 버튼 붙임
     */
    private void appendLoginButton() {
        // 로그인 버튼
        JButton loginButton = new JButton(ResourceLoader.getImageIconResource("img/login.png"));
        loginButton.setBounds(100, 280, 174, 40);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.RED);
        loginButton.setBorderPainted(false);
        contentPanel.add(loginButton);

        LoginButtonAction action = new LoginButtonAction();
        loginButton.addActionListener(action);
    }

    /**
     * 로그인 버튼 액션 : 사용자가 입력한 값을 바탕으로 서버에 로그인 요청
     */
    private class LoginButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String id       = idTextField.getText();
            String password = String.valueOf(pwTextField.getPassword());
            User.getInstance().login(id, password);
        }
    }
}