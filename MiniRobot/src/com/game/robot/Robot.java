package com.game.robot;

import com.game.exceptions.PositionException;
import com.game.exceptions.RobotException;
import com.game.utility.Direction;
import com.game.utility.Label;
import com.game.utility.Position;

public class Robot {
	private Position position;
	private Label name;
	private Direction direction;

	public Robot(int id, int px, int py) throws RobotException {
		try {
			position = new Position(px, py);
		} catch (PositionException e) {
			throw new RobotException(e.getMessage());
		}
		direction = Direction.North;
		name = new Label(id);
	}

	public void setName(String name) {
		this.getLabel().setName(name);
	}

	public Label getLabel() {
		return name;
	}

	public void setPosition(int x, int y) throws RobotException {
		try {
			this.position.setPoint(x, y);
		} catch (PositionException e) {
			throw new RobotException(e.getMessage());
		}
	}

	private Position getPosition() {
		return position;
	}

	public void setDirection(String direction) {
		this.direction = Direction.valueOf(direction);
	}

	public String getDirection() {
		return this.direction.name();
	}

	public void move() throws RobotException {
		try {
			getPosition().moveToward(this.direction);
		} catch (PositionException e) {
			throw new RobotException(e.getMessage());
		}
	}

	public void rotateLeft() {
		switch (direction) {
		case North -> this.direction = Direction.West;
		case East -> this.direction = Direction.North;
		case South -> this.direction = Direction.East;
		case West -> this.direction = Direction.South;
		}
	}

	public void rotateRight() {
		switch (direction) {
		case North -> this.direction = Direction.East;
		case East -> this.direction = Direction.South;
		case South -> this.direction = Direction.West;
		case West -> this.direction = Direction.North;
		}
	}

	@Override
	public String toString() {
		return "Robot Status | " + name.toString() + " - " + position + " - " + direction + " |";
	}
}