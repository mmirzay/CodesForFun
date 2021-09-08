package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class GameConstants {
	public static final int MAXIMUM_NUMBER_OF_BONUS_IMAGES = 10;
	public static final int BONUS_SCORE = 5;
	public static final int PENALTY_SCORE = 3;

	public static final Dimension MODES_FRAME_DIMENSION = new Dimension(300, 300);
	public static final Dimension LEVELS_FRAME_DIMENSION = new Dimension(800, 300);
	public static final Dimension TITLE_PANEL_DIMENSION = new Dimension(300, 25);
	public static final Dimension CLOSE_DIMENSION = new Dimension(25, 25);
	public static final Dimension FOOTER_PANEL_DIMENSION = new Dimension(25, 25);

	public static final Color CLEAR_HISTORY_BUTTON_BACKGROND = new Color(255, 55, 55);
	public static final Color BACK_BUTTON_BACKGROND = new Color(255, 255, 55);
	public static final Color DISABLED_LEVEL_BUTTON_COLOR = Color.gray;
	public static final Color LEVEL1_COLOR = new Color(105, 205, 255);
	public static final Color TITLE_PANEL_BACKGROUND = new Color(120, 120, 120);
	public static final Color TITLE_BACKGROUND = new Color(85, 185, 255);
	public static final Color TIMER_BACKGROUND = new Color(255, 85, 185);
	public static final Color BONUS_TEXT_BACKGROUND = new Color(255, 185, 85);
	public static final Color BONUS_BUTTON_BACKGROUND = new Color(85, 255, 85);
	public static final Color TITLE_FOREGROUND = Color.white;
	public static final Color GAME_PANEL_BACKGROUND = new Color(155, 155, 155);
	public static final Color TILES_BACKGROUND = new Color(246, 246, 246);

	public static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 14);

	public static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);
	public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

	public static final ImageIcon LOGO_IMAGE = new ImageIcon("src/images/logo.png");
	public static final ImageIcon MATCH_ALL_IMAGE = new ImageIcon("src/images/matchAll.png");
	public static final ImageIcon BONUS_IMAGE = new ImageIcon("src/images/bonus.png");
	public static final ImageIcon CLEAR_HISTORY_IMAGE = new ImageIcon("src/images/clear.png");

	public static final LineBorder HINT_BORDER = new LineBorder(Color.orange, 5);
}
