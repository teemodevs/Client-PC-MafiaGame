package client.frame.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import client.frame.game.GameFrame;
import game.User;

/**
 * 윈도우창 닫기 액션 : 로그아웃 요청 및 프로그램 종료
 */
public class CloseWindowWithLogoutAction extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent windowEvent) {
		if (JOptionPane.showConfirmDialog(GameFrame.getInstance(), "Are you sure you want to close this window?",
				"Close Window?", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			User.getInstance().logout();
			System.exit(0);
		}
	}
}