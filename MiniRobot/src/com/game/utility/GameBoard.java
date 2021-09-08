package com.game.utility;

public class GameBoard {
	private int width;
	private int height;
	private boolean[][] occupiedCells;
	private static GameBoard board = null;

	private GameBoard() {
		this.width = Constants.DEFAULT_BOARD_WIDTH;
		this.height = Constants.DEFAULT_BOARD_HEIGHT;
		this.occupiedCells = new boolean[this.width][this.height];
	}

	public static GameBoard getBoard() {
		if (board == null)
			board = new GameBoard();
		return board;
	}

	public int getWidth() {
		return width;
	}

	public int getHieght() {
		return height;
	}

	public void setNewDimension(int width, int height) {
		this.width = width;
		this.height = height;
		this.occupiedCells = new boolean[this.width][this.height];
	}

	public boolean isOccupied(int i, int j) {
		return occupiedCells[i][j];
	}

	public void setOccupied(int i, int j) {
		this.occupiedCells[i][j] = true;
	}

}
