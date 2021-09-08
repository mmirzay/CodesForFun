package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GameFrame extends javax.swing.JFrame implements ActionListener {

	private int gameLevel;
	private int numberOfPictures;
	private int numberOfRows;
	private int numberOfColumns;
	private int score;
	private int bonusScore;
	private int bonusValue;
	private int numberOfMatchAllImage;
	private boolean isTestMode;
	private boolean won;
	private boolean helping;
	private boolean isFirstSelected;
	private boolean isSecondSelected;
	private boolean isMatchAllImageEnabled;
	private boolean isHintEnabled;
	private boolean isTimerEnabled;
	private boolean isHintSelected;
	private boolean isMouseOverHintEnabled;
	private boolean isBonusImageEnaled;
	private boolean useBonusScoreEnabled;
	private boolean useBonusScoreForTwoTiles;

	private Tile[] tiles;
	private ImageIcon[] icons;

	private Tile firstSelection;
	private Tile secondSelection;
	private Tile matchSelection;

	private JLabel close;
	private JPanel footerPanel;
	private JPanel gamePanel;
	private JLabel help;
	private JButton back;
	private JButton hint;
	private JButton useBonus;
	private JTextField title;
	private JTextField bonusText;
	private JTextField timerText;
	private JPanel titlePanel;

	private int minutes;
	private int seconds;
	private Timer timer;
	private Timer helpTimer;
	private int helpTimerCounter = 10;

	public GameFrame(int level, boolean testMode) {
		gameLevel = level;
		this.isTestMode = testMode;
		if (gameLevel == 1) {
			numberOfPictures = 4;
			numberOfRows = 4;
			numberOfColumns = 4;
		} else if (gameLevel == 2) {
			numberOfPictures = 2;
			numberOfRows = 6;
			numberOfColumns = 6;
		} else if (gameLevel == 3) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
		} else if (gameLevel == 4) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			numberOfMatchAllImage = 8;
			isMatchAllImageEnabled = true;
		} else if (gameLevel == 5) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			isHintEnabled = true;
		} else if (gameLevel == 6) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			isMouseOverHintEnabled = true;
		} else if (gameLevel == 7) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			isTimerEnabled = true;
			isBonusImageEnaled = true;
		} else if (gameLevel == 8) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			isBonusImageEnaled = true;
			useBonusScoreEnabled = true;
		} else if (gameLevel == 9) {
			numberOfPictures = 2;
			numberOfRows = 8;
			numberOfColumns = 8;
			isBonusImageEnaled = true;
			useBonusScoreEnabled = true;
			useBonusScoreForTwoTiles = true;
		}

		tiles = new Tile[numberOfRows * numberOfColumns];
		icons = new ImageIcon[tiles.length / numberOfPictures];

		initComponents();
		initIcons();
		initTiles();
		initTimers();

	}

	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Matching Game");
		setName("MainFrame");
		setUndecorated(true);

		titlePanel = new JPanel();
		titlePanel.setBackground(GameConstants.TITLE_PANEL_BACKGROUND);
		titlePanel.setPreferredSize(GameConstants.TITLE_PANEL_DIMENSION);
		titlePanel.setLayout(new BorderLayout());

		title = new JTextField();
		title.setBackground(GameConstants.TITLE_BACKGROUND);
		title.setFont(GameConstants.TITLE_FONT);
		title.setForeground(GameConstants.TITLE_FOREGROUND);
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setText("Score: " + score);
		title.setToolTipText("After Clicking mouse here use arrow keys to move");
		title.setBorder(null);
		title.setCursor(GameConstants.MOVE_CURSOR);
		if (isTestMode == false) {
			title.setEditable(false);
			title.setHighlighter(null);
		}
		if (isTestMode)
			title.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setScoreInTestMode();
				}
			});
		title.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				titleMouseDragged(evt);
			}
		});
		title.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				titleKeyPressed(evt);
			}
		});
		titlePanel.add(title, BorderLayout.CENTER);

		close = new JLabel();
		close.setFont(GameConstants.TITLE_FONT);
		close.setForeground(Color.red);
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setText("X");
		close.setToolTipText("Close");
		close.setCursor(GameConstants.HAND_CURSOR);
		close.setPreferredSize(GameConstants.CLOSE_DIMENSION);
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				closeMouseClicked(evt);
			}
		});
		titlePanel.add(close, BorderLayout.LINE_END);

		help = new JLabel();
		help.setFont(GameConstants.TITLE_FONT);
		help.setForeground(Color.green);
		help.setHorizontalAlignment(SwingConstants.CENTER);
		help.setText("?");
		help.setToolTipText("Click to see Images");
		help.setCursor(GameConstants.HAND_CURSOR);
		help.setPreferredSize(GameConstants.CLOSE_DIMENSION);
		help.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				helpMouseClicked(evt);
			}
		});
		titlePanel.add(help, BorderLayout.LINE_START);

		getContentPane().add(titlePanel, BorderLayout.NORTH);

		gamePanel = new JPanel();
		gamePanel.setBackground(GameConstants.GAME_PANEL_BACKGROUND);
		if (gameLevel < 2)
			gamePanel.setPreferredSize(new Dimension(numberOfRows * 105, numberOfColumns * 105));
		else
			gamePanel.setPreferredSize(new Dimension(numberOfRows * 85, numberOfColumns * 85));

		gamePanel.setLayout(new GridLayout(numberOfRows, numberOfColumns, 5, 5));
		getContentPane().add(gamePanel, BorderLayout.CENTER);

		footerPanel = new JPanel();
		footerPanel.setPreferredSize(GameConstants.FOOTER_PANEL_DIMENSION);
		footerPanel.setLayout(new GridLayout(1, 2, 5, 0));

		if (isHintEnabled) {
			hint = new JButton();
			hint.setBackground(GameConstants.BONUS_TEXT_BACKGROUND);
			hint.setFont(GameConstants.TITLE_FONT);
			hint.setForeground(GameConstants.TITLE_FOREGROUND);
			hint.setText("Hint");
			hint.setCursor(GameConstants.HAND_CURSOR);
			hint.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					hintActionPerformed();
				}
			});
			footerPanel.add(hint);
		}

		if (isTimerEnabled) {
			timerText = new JTextField();
			timerText.setEditable(false);
			timerText.setBackground(GameConstants.TIMER_BACKGROUND);
			timerText.setFont(GameConstants.TITLE_FONT);
			timerText.setForeground(GameConstants.TITLE_FOREGROUND);
			timerText.setHorizontalAlignment(JTextField.CENTER);
			timerText.setText("Time: 0" + minutes + ":" + seconds + "0");
			timerText.setBorder(null);
			timerText.setHighlighter(null);
			footerPanel.add(timerText);
		}

		if (isBonusImageEnaled) {
			bonusText = new JTextField();
			bonusText.setBackground(GameConstants.BONUS_TEXT_BACKGROUND);
			bonusText.setFont(GameConstants.TITLE_FONT);
			bonusText.setForeground(GameConstants.TITLE_FOREGROUND);
			bonusText.setHorizontalAlignment(JTextField.CENTER);
			bonusText.setText("Bonus: " + bonusScore);
			bonusText.setToolTipText("After Clicking mouse here use arrow keys to move");
			bonusText.setBorder(null);
			if (isTestMode == false) {
				bonusText.setHighlighter(null);
				bonusText.setEditable(false);
			}
			if (isTestMode) {
				bonusText.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setBonusScoreInTestMode();
					}
				});
			}
			footerPanel.add(bonusText);
		}

		if (useBonusScoreEnabled) {
			useBonus = new JButton();
			useBonus.setBackground(GameConstants.BONUS_BUTTON_BACKGROUND);
			useBonus.setFont(GameConstants.TITLE_FONT);
			useBonus.setForeground(GameConstants.TITLE_FOREGROUND);
			useBonus.setText("Use Bonus");
			useBonus.setCursor(GameConstants.HAND_CURSOR);
			useBonus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					useBonusActionPerformed();
				}
			});
			footerPanel.add(useBonus);
		}
		back = new JButton();
		back.setBackground(GameConstants.TITLE_BACKGROUND);
		back.setFont(GameConstants.TITLE_FONT);
		back.setForeground(GameConstants.TITLE_FOREGROUND);
		back.setText("Back to Levels");
		back.setCursor(GameConstants.HAND_CURSOR);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				backActionPerformed(evt);
			}
		});
		footerPanel.add(back);

		getContentPane().add(footerPanel, BorderLayout.SOUTH);

		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 20);
	}

	private void initTimers() {
		helpTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				helpTimerCounter--;
				title.setText("Timer: " + helpTimerCounter);
				if (helpTimerCounter == 3)
					title.setForeground(Color.yellow);
				if (helpTimerCounter == 2)
					title.setForeground(Color.orange);
				if (helpTimerCounter == 1)
					title.setForeground(Color.red);
				if (helpTimerCounter <= 0) {
					hideHelp();
					helping = false;
					score -= 50;
					bonusValue -= 50;
					if (isBonusImageEnaled && score < 0) {
						bonusScore += (bonusValue / 100) * GameConstants.PENALTY_SCORE;
						if (bonusScore < 0)
							bonusScore = 0;
						bonusText.setText("Bonus: " + bonusScore);
						bonusValue %= 100;
					} // if bonus
					title.setText("Score: " + score);
					title.setForeground(GameConstants.TITLE_FOREGROUND);
					helpTimer.stop();
				} // if counter finnished
			}// action
		});

		if (isTimerEnabled) {
			timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = "" + (++seconds);
					String m = "" + minutes;
					if (s.length() == 1)
						s = "0" + s;
					if (m.length() == 1)
						m = "0" + m;
					timerText.setText("Time: " + m + ":" + s);
					if (seconds >= 59) {
						seconds = -1;
						minutes++;
					}
				}
			});
			timer.start();
		}

	}

	private void initIcons() {
		ImageIcon img;
		for (int i = 0; i < icons.length; i++) {
			img = new ImageIcon(getClass().getResource("/images/img" + i + ".png"));
			icons[i] = img;
		}
	}

	private void initTiles() {
		int duplicateImages = 0;
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile(icons[duplicateImages], GameConstants.LOGO_IMAGE);
			tiles[i].addActionListener(this);
			if (isMouseOverHintEnabled)
				tiles[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						showMouseOverHint(e, true);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						showMouseOverHint(e, false);
					}
				});
			tiles[i].setDisabledIcon(tiles[i].getImage());
			tiles[i].setBackground(GameConstants.TILES_BACKGROUND);
			if ((i + 1) % numberOfPictures == 0) {
				tiles[i].setMatchTile(tiles[i - 1]);
				tiles[i - 1].setMatchTile(tiles[i]);
				duplicateImages++;
			}
		}
		if (isMatchAllImageEnabled)
			for (int i = 0; i < numberOfMatchAllImage; i++) {
				tiles[i] = new Tile(GameConstants.MATCH_ALL_IMAGE, GameConstants.LOGO_IMAGE);
				tiles[i].addActionListener(this);
				tiles[i].setDisabledIcon(tiles[i].getImage());
				tiles[i].setBackground(GameConstants.TILES_BACKGROUND);
				if ((i + 1) % 2 == 0) {
					tiles[i].setMatchTile(tiles[i - 1]);
					tiles[i - 1].setMatchTile(tiles[i]);
				}
			}

		if (isBonusImageEnaled) {
			int numberOfBonusImages = 3 + (int) (Math.random() * (GameConstants.MAXIMUM_NUMBER_OF_BONUS_IMAGES - 2));
			if (numberOfBonusImages % 2 != 0)
				numberOfBonusImages++;
			for (int i = 0; i < numberOfBonusImages; i++) {
				tiles[i] = new Tile(GameConstants.BONUS_IMAGE, GameConstants.LOGO_IMAGE);
				tiles[i].addActionListener(this);
				tiles[i].setDisabledIcon(tiles[i].getImage());
				tiles[i].setBackground(GameConstants.TILES_BACKGROUND);
				if ((i + 1) % 2 == 0) {
					tiles[i].setMatchTile(tiles[i - 1]);
					tiles[i - 1].setMatchTile(tiles[i]);
				}
			}
		}
		shuffleTiles();
	}

	private void shuffleTiles() {
		ArrayList<Integer> randomNumbers = new ArrayList<>();
		for (int i = 0; i < tiles.length;) {
			int x = (int) (Math.random() * tiles.length);
			if (randomNumbers.contains(x) == true)
				continue;
			randomNumbers.add(x);
			i++;
		}
		for (int i = 0; i < tiles.length; i++)
			gamePanel.add(tiles[randomNumbers.get(i)]);
	}

	private void setScoreInTestMode() {
		if (isTestMode == false)
			return;
		String newScoreText = title.getText();
		try {
			int newScore = Integer.parseInt(newScoreText);
			score = newScore;
			title.setText("Score: " + score);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The value is incorrect, try positive Integer number again.");
		}
	}

	private void setBonusScoreInTestMode() {
		if (isTestMode == false)
			return;
		String newBonusScoreText = bonusText.getText();
		try {
			int newBonus = Integer.parseInt(newBonusScoreText);
			bonusScore = newBonus;
			bonusText.setText("Bonus: " + bonusScore);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The value is incorrect, try positive Integer number again.");
		}
	}

	private void helpMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1)
			if (helping == false) {
				showHelp();
				helpTimerCounter = 10;
				helping = true;
				title.setText("Timer: " + helpTimerCounter);
				helpTimer.start();
			}
	}

	private void showHelp() {
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].isNoImage() == false) {
				tiles[i].showTile();
				tiles[i].setEnabled(false);
			}
		}
	}

	private void hideHelp() {
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i].isNoImage() == false) {
				tiles[i].setEnabled(true);
				tiles[i].hideTile();
			}
		}
	}

	private void closeMouseClicked(MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1) {
			if (isTimerEnabled)
				timer.stop();
			new LevelsFrame(isTestMode).setVisible(true);
			this.dispose();
		}
	}

	private void backActionPerformed(ActionEvent evt) {
		new LevelsFrame(isTestMode).setVisible(true);
		this.dispose();
	}

	private void hintActionPerformed() {
		if (isHintSelected)
			return;
		boolean showingHint = true;
		while (showingHint) {
			int x = (int) (Math.random() * tiles.length);
			if (tiles[x].isNoImage() == false) {
				tiles[x].setBorder(GameConstants.HINT_BORDER);
				tiles[x].getMatchTile().setBorder(GameConstants.HINT_BORDER);
				showingHint = false;
				isHintSelected = true;
			}
		}
	}

	private void useBonusActionPerformed() {
		if (useBonusScoreEnabled) {
			if (useBonusScoreForTwoTiles) {
				if (bonusScore < 2)
					return;
				if (firstSelection != null && firstSelection.isHidden() == false) {
					secondSelection = firstSelection.getMatchTile();
					bonusScore -= 2;
					check();
					return;
				}
			}
			if (bonusScore < 10)
				return;
			boolean matching = true;
			while (matching) {
				int x = (int) (Math.random() * tiles.length);
				if (tiles[x].isNoImage() == false) {
					firstSelection = tiles[x];
					secondSelection = tiles[x].getMatchTile();
					matching = false;
					check();
				}
			} // while
			bonusScore -= 10;
			bonusText.setText("Bonus: " + bonusScore);
		} // if
	}

	private void showMouseOverHint(MouseEvent e, boolean showHint) {
		if (showHint) {
			((Tile) e.getSource()).setBorder(GameConstants.HINT_BORDER);
			((Tile) e.getSource()).getMatchTile().setBorder(GameConstants.HINT_BORDER);
		} else {
			((Tile) e.getSource()).setBorder(null);
			((Tile) e.getSource()).getMatchTile().setBorder(null);
		}

	}

	private void titleMouseDragged(MouseEvent evt) {
		setLocation(evt.getXOnScreen() - 300, evt.getYOnScreen());
	}

	private void titleKeyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			setLocation(getX() - 5, getY());
		}
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			setLocation(getX() + 5, getY());
		}
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			setLocation(getX(), getY() - 5);
		}
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			setLocation(getX(), getY() + 5);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isSecondSelected)
			return;
		if (isFirstSelected == false) {
			firstSelection = (Tile) e.getSource();
			firstSelection.showTile();
			isFirstSelected = true;
		} else {
			secondSelection = (Tile) e.getSource();
			new Thread() {
				@Override
				public void run() {
					try {
						isSecondSelected = true;
						secondSelection.showTile();
						Thread.sleep(500);
						check();
						Thread.sleep(600);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}.start();
		}
	}

	private void check() {
		if (firstSelection != secondSelection) {
			if (firstSelection.getImage() == secondSelection.getImage()
					|| firstSelection.getImage() == GameConstants.MATCH_ALL_IMAGE
					|| secondSelection.getImage() == GameConstants.MATCH_ALL_IMAGE) {
				if (firstSelection.getImage() == GameConstants.MATCH_ALL_IMAGE
						&& secondSelection.getImage() == GameConstants.MATCH_ALL_IMAGE) {
					firstSelection.hideTile();
					secondSelection.hideTile();
					isFirstSelected = false;
					isSecondSelected = false;
					return;
				}

				if (firstSelection.getImage() == GameConstants.MATCH_ALL_IMAGE)
					matchSelection = secondSelection.getMatchTile();
				if (secondSelection.getImage() == GameConstants.MATCH_ALL_IMAGE)
					matchSelection = firstSelection.getMatchTile();

				new Thread() {
					@Override
					public void run() {
						Sound sound = null;
						try {
							sound = new Sound(getClass().getResource("/sounds/guess.wav"));
						} catch (Exception e) {
						}
						InputStream stream = new ByteArrayInputStream(sound.getSamples());
						sound.play(stream);
					}
				}.start();// correct answer sound
				new Thread() {
					boolean finished = false;

					@Override
					public void run() {
						for (int i = 0; i < 3; i++) {
							try {
								firstSelection.hideTile();
								secondSelection.hideTile();
								if (matchSelection != null)
									matchSelection.hideTile();
								Thread.sleep(100);
								firstSelection.showTile();
								secondSelection.showTile();
								if (matchSelection != null)
									matchSelection.showTile();
								Thread.sleep(100);
							} catch (InterruptedException ex) {
								System.out.println(ex);
							}
						}
						firstSelection.setNoImage();
						secondSelection.setNoImage();
						firstSelection.setVisible(false);
						secondSelection.setVisible(false);
						if (matchSelection != null) {
							matchSelection.setNoImage();
							matchSelection.setVisible(false);
						}
						for (int i = 0; i < tiles.length; i++) {
							if (tiles[i].isNoImage()) {
								finished = true;
							} else {
								finished = false;
								break;
							}
						}
						if (finished) {
							if (score > 0) {
								new Thread() {
									@Override
									public void run() {
										Sound sound = null;
										try {
											sound = new Sound(getClass().getResource("/sounds/won.wav"));
										} catch (Exception e) {
										}
										InputStream stream = new ByteArrayInputStream(sound.getSamples());
										sound.play(stream);
									}
								}.start();// won sound
								JOptionPane.showMessageDialog(gamePanel, "You Won! Your Score is " + score);
								won = true;
								setLevel(gameLevel + 1);
							} else {
								new Thread() {
									@Override
									public void run() {
										Sound sound = null;
										try {
											sound = new Sound(getClass().getResource("/sounds/loose.wav"));
										} catch (Exception e) {
										}
										InputStream stream = new ByteArrayInputStream(sound.getSamples());
										sound.play(stream);
									}
								}.start();// loose sound
								JOptionPane.showMessageDialog(gamePanel, "You Loose! Your Score is " + score);
							}
							if (hint != null)
								hint.setEnabled(false);
							if (useBonus != null)
								useBonus.setEnabled(false);
						} // if finished
					}
				}.start();// animation
				firstSelection.removeActionListener(this);
				secondSelection.removeActionListener(this);
				score += 100;
				bonusValue += 100;
				if (isBonusImageEnaled) {
					bonusScore += (bonusValue / 300) * GameConstants.BONUS_SCORE;
					if (firstSelection.getImage() == GameConstants.BONUS_IMAGE)
						bonusScore += GameConstants.BONUS_SCORE;
					bonusText.setText("Bonus: " + bonusScore);
					bonusValue %= 300;
				}
				title.setText("Score: " + score);
				if (isHintEnabled && firstSelection.getBorder() == GameConstants.HINT_BORDER)
					isHintSelected = false;
			} // if correct
			else {
				new Thread() {

					@Override
					public void run() {
						Sound sound = null;
						try {
							sound = new Sound(getClass().getResource("/sounds/wrong.wav"));
						} catch (Exception e) {
						}
						InputStream stream = new ByteArrayInputStream(sound.getSamples());
						sound.play(stream);
					}
				}.start();// wrong answer sound

				firstSelection.hideTile();
				secondSelection.hideTile();
				score -= 10;
				bonusValue -= 10;
				if (isBonusImageEnaled && score < 0) {
					bonusScore += (bonusValue / 100) * GameConstants.PENALTY_SCORE;
					if (bonusScore < 0)
						bonusScore = 0;
					bonusText.setText("Bonus: " + bonusScore);
					bonusValue %= 100;
				}
				title.setText("Score: " + score);
			}
		} // if
			// selection
			// !=
			// second
		else {
			firstSelection.hideTile();
			secondSelection.hideTile();
		}
		isFirstSelected = false;
		isSecondSelected = false;
	}// check

	private void setLevel(int level) {
		if (isTestMode)
			return;
		if (won == false)
			return;
		if (level > 9)
			return;
		File levelHistory = new File("levelsHitory.txt");
		try {
			FileReader reader = new FileReader(levelHistory);
			int lastlevel = reader.read();
			reader.close();
			if (lastlevel >= level)
				return;
			FileWriter write = new FileWriter(levelHistory);
			write.write(level);
			write.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Something happen while setting levels history: \n" + e);
		}
	}
}
