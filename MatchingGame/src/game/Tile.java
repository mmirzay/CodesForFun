package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class Tile extends JButton {

	private ImageIcon backImage;
	private ImageIcon frontImage;
	private boolean noImage;
	private boolean hidden;
	private Tile matchTile;

	public Tile(ImageIcon backImage, ImageIcon frontImage) {
		this.backImage = backImage;
		this.frontImage = frontImage;
		setSize(100, 100);
		setFocusable(false);
		setIcon(frontImage);
		setHidden(true);
	}

	public synchronized void showTile() {
		setIcon(backImage);
		setHidden(false);
	}

	public synchronized void hideTile() {
		setIcon(frontImage);
		setHidden(true);
	}

	public synchronized void setNoImage() {
		setIcon(null);
		noImage = true;
		hidden = true;
	}

	public ImageIcon getImage() {
		return backImage;
	}

	public synchronized boolean isNoImage() {
		return noImage;
	}

	public Tile getMatchTile() {
		return matchTile;
	}

	public void setMatchTile(Tile matchTile) {
		this.matchTile = matchTile;
	}

	public boolean isHidden() {
		return hidden;
	}

	private void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
