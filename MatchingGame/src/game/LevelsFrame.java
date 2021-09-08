package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelsFrame extends JFrame {

	private int level;
	boolean testMode;

	JPanel mainPanel;
	JButton btnLevel1;
	JButton btnLevel2;
	JButton btnLevel3;
	JButton btnLevel4;
	JButton btnLevel5;
	JButton btnLevel6;
	JButton btnLevel7;
	JButton btnLevel8;
	JButton btnLevel9;
	JButton btnBack;
	JButton btnClearHistory;

	public LevelsFrame(boolean isTestMode) {
		this.level = getLevelHistory();
		this.testMode = isTestMode;
		initComponents();
		initLevels(isTestMode);
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("Levels of the Game");
		setBackground(new Color(204, 204, 255));

		setUndecorated(true);
		setPreferredSize(GameConstants.LEVELS_FRAME_DIMENSION);
		setMaximumSize(GameConstants.LEVELS_FRAME_DIMENSION);
		setMinimumSize(GameConstants.LEVELS_FRAME_DIMENSION);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		GridLayout gl_mainPanel = new GridLayout(1, 10);
		gl_mainPanel.setHgap(5);
		mainPanel = new JPanel(gl_mainPanel);
		getContentPane().add(mainPanel);

		btnLevel1 = new JButton("1");
		btnLevel1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(1);
			}
		});
		mainPanel.add(btnLevel1);

		btnLevel2 = new JButton("2");
		btnLevel2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(2);
			}
		});
		mainPanel.add(btnLevel2);

		btnLevel3 = new JButton("3");
		btnLevel3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(3);
			}
		});
		mainPanel.add(btnLevel3);

		btnLevel4 = new JButton("4");
		btnLevel4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(4);
			}
		});
		mainPanel.add(btnLevel4);

		btnLevel5 = new JButton("5");
		btnLevel5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(5);
			}
		});
		mainPanel.add(btnLevel5);

		btnLevel6 = new JButton("6");
		btnLevel6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(6);
			}
		});
		mainPanel.add(btnLevel6);

		btnLevel7 = new JButton("7");
		btnLevel7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(7);
			}
		});
		mainPanel.add(btnLevel7);

		btnLevel8 = new JButton("8");
		btnLevel8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(8);
			}
		});
		mainPanel.add(btnLevel8);

		btnLevel9 = new JButton("9");
		btnLevel9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLevel(9);
			}
		});
		mainPanel.add(btnLevel9);

		btnClearHistory = new JButton();
		btnClearHistory.setBackground(GameConstants.CLEAR_HISTORY_BUTTON_BACKGROND);
		btnClearHistory.setIcon(GameConstants.CLEAR_HISTORY_IMAGE);
		btnClearHistory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearHistory();
			}
		});
		mainPanel.add(btnClearHistory);

		btnBack = new JButton("Back");
		btnBack.setBackground(GameConstants.BACK_BUTTON_BACKGROND);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		mainPanel.add(btnBack);

	}

	private void initLevels(boolean isTestMode) {
		if (isTestMode) {
			btnClearHistory.setEnabled(false);
			return;
		}
		btnLevel1.setEnabled(false);
		btnLevel2.setEnabled(false);
		btnLevel3.setEnabled(false);
		btnLevel4.setEnabled(false);
		btnLevel5.setEnabled(false);
		btnLevel6.setEnabled(false);
		btnLevel7.setEnabled(false);
		btnLevel8.setEnabled(false);
		btnLevel9.setEnabled(false);

		btnLevel1.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel2.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel3.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel4.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel5.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel6.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel7.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel8.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);
		btnLevel9.setBackground(GameConstants.DISABLED_LEVEL_BUTTON_COLOR);

		switch (level) {
		case 9:
			btnLevel9.setEnabled(true);
			btnLevel9.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 90));
		case 8:
			btnLevel8.setEnabled(true);
			btnLevel8.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 80));
		case 7:
			btnLevel7.setEnabled(true);
			btnLevel7.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 70));
		case 6:
			btnLevel6.setEnabled(true);
			btnLevel6.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 60));
		case 5:
			btnLevel5.setEnabled(true);
			btnLevel5.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 50));
		case 4:
			btnLevel4.setEnabled(true);
			btnLevel4.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 40));
		case 3:
			btnLevel3.setEnabled(true);
			btnLevel3.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 30));
		case 2:
			btnLevel2.setEnabled(true);
			btnLevel2.setBackground(new Color(GameConstants.LEVEL1_COLOR.getRGB() - 20));
		case 1:
			btnLevel1.setEnabled(true);
			btnLevel1.setBackground(GameConstants.LEVEL1_COLOR);
		}
	}

	private void back() {
		new ModesFrame().setVisible(true);
		this.dispose();
	}

	private void clearHistory() {
		int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete Levels History?", "Delete History",
				JOptionPane.YES_NO_OPTION);
		if (option != JOptionPane.YES_OPTION)
			return;
		File levelHistory = new File("levelsHitory.txt");
		levelHistory.delete();
		new ModesFrame().setVisible(true);
		this.dispose();
	}

	private int getLevelHistory() {
		File levelHistory = new File("levelsHitory.txt");
		try {
			if (levelHistory.createNewFile()) {
				FileWriter write = new FileWriter(levelHistory);
				write.write(1);
				write.close();
				level = 1;
			} else {
				FileReader reader = new FileReader(levelHistory);
				level = reader.read();
				reader.close();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Something happen while loading levels history: \n" + e);
		}
		return level;
	}

	private void startLevel(int level) {
		String message = "";
		switch (level) {
		case 1:
			message = "Level 1:\n" + " - 4x4 Tiles\n" + " - 4 similar pictures for each tile.";
			break;
		case 2:
			message = "Level 2:\n" + " - 6x6 Tiles\n" + " - 2 similar pictures for each tile.";
			break;
		case 3:
			message = "Level 3:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile.";
			break;
		case 4:
			message = "Level 4:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n"
					+ " - 8 pictures for matching with any tile.";
			break;
		case 5:
			message = "Level 5:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n"
					+ " - Hint button for showing two similar tiles.";
			break;
		case 6:
			message = "Level 6:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n"
					+ " - Mouse over tiles for showing the similar tile.";
			break;
		case 7:
			message = "Level 7:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n" + " - Timer\n"
					+ " - 5 Bonus images\n" + " - Bonus score.";
			break;
		case 8:
			message = "Level 8:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n" + " - 5 Bonus images\n"
					+ " - Bonus score\n" + " - Use bonus score for matching two tiles.";
			break;
		case 9:
			message = "Level 9:\n" + " - 8x8 Tiles\n" + " - 2 similar pictures for each tile\n" + " - 5 Bonus images\n"
					+ " - Bonus score\n" + " - Use bonus score for matching two tiles\n"
					+ " - Use bonus score for matching selected tile.";
			break;
		}
		JOptionPane.showMessageDialog(this, message);
		new GameFrame(level, testMode).setVisible(true);
		this.dispose();
	}
}
