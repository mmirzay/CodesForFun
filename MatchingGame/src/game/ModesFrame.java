package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ModesFrame extends JFrame {

	JPanel mainPanel;
	JButton btnNormalPlay;
	JButton btnTestPlay;
	JButton btnExit;

	public ModesFrame() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Modes of the Game");
		setBackground(new java.awt.Color(204, 204, 255));

		setUndecorated(true);
		setPreferredSize(GameConstants.MODES_FRAME_DIMENSION);
		setMaximumSize(GameConstants.MODES_FRAME_DIMENSION);
		setMinimumSize(GameConstants.MODES_FRAME_DIMENSION);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		GridLayout gl_mainPanel = new GridLayout(3, 1);
		gl_mainPanel.setVgap(10);
		mainPanel = new JPanel(gl_mainPanel);
		getContentPane().add(mainPanel);

		btnNormalPlay = new JButton("Normal Mode");
		btnNormalPlay.setBackground(new Color(55, 155, 255));
		btnNormalPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showNormalPlay();
			}
		});
		mainPanel.add(btnNormalPlay);

		btnTestPlay = new JButton("Test Mode");
		btnTestPlay.setBackground(new Color(55, 255, 155));
		btnTestPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showTestModePlay();
			}
		});
		mainPanel.add(btnTestPlay);

		btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 155, 55));
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		mainPanel.add(btnExit);

	}

	private void exit() {
		this.dispose();
	}

	private void showNormalPlay() {
		new LevelsFrame(false).setVisible(true);
		this.dispose();
	}

	private void showTestModePlay() {
		new LevelsFrame(true).setVisible(true);
		this.dispose();
	}

	public static void main(String[] args) {
		new ModesFrame().setVisible(true);
	}
}
