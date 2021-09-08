package com.game.utility;

import com.game.exceptions.PositionException;

public class Position {
	private int x;
	private int y;
	private static int maxX;
	private static int maxY;

	static {
		maxX = GameBoard.getBoard().getWidth();
		maxY = GameBoard.getBoard().getHieght();
	}

	public Position() throws PositionException {
		this.setPoint(0, 0);
	}

	public Position(int x, int y) throws PositionException {
		this.setPoint(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPoint(int x, int y) throws PositionException {
		if( x >= maxX || y >= maxY)
			throw new PositionException(String.format("Cell at point (%d , %d) is outside of the board.", x, y));
		if (GameBoard.getBoard().isOccupied(x, y))
			throw new PositionException(String.format("Cell at point (%d , %d) is occupied by another Robot.", x, y));
		this.x = x;
		this.y = y;
	}

	public void moveToward(Direction direction) throws PositionException {
		switch (direction) {
		case North -> {
			if (y == maxY)
				throw new PositionException(
						String.format("It Can not move toward %s to outside of the board!", direction));
			this.setPoint(x, y + 1);
		}
		case East -> {
			if (x == maxX)
				throw new PositionException(
						String.format("Can not move toward %s to outside of the board!", direction));
			this.setPoint(x + 1, y);
		}
		case South -> {
			if (y == 0)
				throw new PositionException(
						String.format("Can not move toward %s to outside of the board!", direction));
			this.setPoint(x, y - 1);
		}
		case West -> {
			if (x == 0)
				throw new PositionException(
						String.format("Can not move toward %s to outside of the board!", direction));
			this.setPoint(x - 1, y);
		}
		}
	}

	@Override
	public String toString() {
		return "Position [x:" + x + ", y:" + y + "]";
	}

}
