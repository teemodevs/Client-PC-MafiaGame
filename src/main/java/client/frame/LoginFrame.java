package client.frame;

import config.ResourceConfig;
import exception.io.image.ImageLoadException;
import game.User;
import network.ServerConnector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private static LoginFrame loginFrame = new LoginFrame();

    private LoginFrame(){
        this.contentPanel = new JPanel();
        this.idTextField = new JTextField();
        this.pwTextField = new JPasswordField();
        this.ipTextField = new JTextField();
    }

    public static LoginFrame getInstance() {
        return loginFrame;
    }

    private JPanel contentPanel;       // 메인 프레임

    private JTextField idTextField;     // ID 입력창
    private JPasswordField pwTextField; // PW 입력창
    private JTextField ipTextField;     // IP 입력창

    private BufferedImage bufferedImage; // 이미지 버퍼

    public void boot() {
        this.floatLoginFrame();
        this.appendIdTextField();
        this.appendPwTextField();
        //this.appendIpTextField();
        this.appendLoginButton();
        setVisible(true);
    }

    // 로그인 창 띄움
    private void floatLoginFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 380, 400);

        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setLayout(null);
        setContentPane(contentPanel);

        try {
            bufferedImage = ImageIO.read(new File(ResourceConfig.RESOURCE_PATH + "img/title.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageLoadException("img/title.png load failure");
        }

        JLabel titleLabel = new JLabel(new ImageIcon(bufferedImage));
        titleLabel.setFont(new Font("", Font.PLAIN, 30));
        titleLabel.setBounds(84, 26, 200, 94);
        titleLabel.setForeground(Color.RED);
        contentPanel.add(titleLabel);
    }

    // id 입력 텍스트 필드 붙임
    private void appendIdTextField() {
        try {
            bufferedImage = ImageIO.read(new File(ResourceConfig.RESOURCE_PATH + "img/id.png"));
        } catch (IOException e) {
            throw new ImageLoadException("img/id.png load failure");
        }
        JLabel idLabel = new JLabel(new ImageIcon(bufferedImage));
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

    // pw 입력 텍스트 필드 붙임
    private void appendPwTextField() {
        try {
            bufferedImage = ImageIO.read(new File(ResourceConfig.RESOURCE_PATH + "img/password.png"));
        } catch (IOException e) {
            throw new ImageLoadException("img/password.png load failure");
        }
        JLabel passwordLabel = new JLabel(new ImageIcon(bufferedImage));
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

    // ip 입력 텍스트 필드 붙임
    private void appendIpTextField() {
        try {
            bufferedImage = ImageIO.read(new File(ResourceConfig.RESOURCE_PATH + "img/ip.png"));
        } catch (IOException e) {
            throw new ImageLoadException("img/ip.png load failure");
        }
        JLabel ipLabel = new JLabel(new ImageIcon(bufferedImage));
        ipLabel.setBounds(18, 240, 177, 43);
        ipLabel.setForeground(Color.WHITE);
        contentPanel.add(ipLabel);

        ipTextField.setColumns(10);
        ipTextField.setBounds(140, 254, 130, 21);
        ipTextField.setBackground(Color.BLACK);
        ipTextField.setForeground(Color.WHITE);
        ipTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        contentPanel.add(ipTextField);
    }

    private void appendLoginButton() {
        // 로그인 버튼
        try {
            bufferedImage = ImageIO.read(new File(ResourceConfig.RESOURCE_PATH + "img/login.png"));
        } catch (IOException e) {
            throw new ImageLoadException("img/login.png load failure");
        }
        JButton loginButton = new JButton(new ImageIcon(bufferedImage));
        loginButton.setBounds(100, 280, 174, 40);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.RED);
        loginButton.setBorderPainted(false);
        contentPanel.add(loginButton);

        ConnectAction action = new ConnectAction();
        loginButton.addActionListener(action);
    }

    private class ConnectAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {

            String id       = idTextField.getText();
            String password = String.valueOf(pwTextField.getPassword());
            //String ip       = ipTextField.getText();
            User.getInstance().login(id, password);

        }
    }


}
