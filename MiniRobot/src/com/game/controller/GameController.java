package com.game.controller;

import java.util.HashMap;
import java.util.Map;

import com.game.exceptions.ControllerException;
import com.game.exceptions.RobotException;
import com.game.robot.Robot;
import com.game.utility.GameBoard;
import com.game.utility.GameMessages;

public class GameController {

	private static int maxRobotsNumber;
	private static int robotNumber = 1;
	private Map<Integer, Robot> allRobots = new HashMap<>();
	private static Robot selectedRobot = null;

	public GameController(GameBoard board) {
		maxRobotsNumber = board.getWidth() * board.getHieght();
	}

	public void addNewRobot(int px, int py, String name, String direction) throws ControllerException {
		if (robotNumber == maxRobotsNumber)
			throw new ControllerException("Can not add new robot. Max number of robots added");
		try {
			Robot robot = new Robot(robotNumber, px, py);
			robot.setName(name);
			robot.setDirection(direction);
			allRobots.put(robotNumber, robot);
			robotNumber++;
			
		} catch (RobotException e) {
			throw new ControllerException(e.getMessage());
		}

		GameMessages.showAdded();
	}

	public void selectRobot(int id) throws ControllerException {
		selectedRobot = allRobots.get(id);
		if (selectedRobot == null)
			throw new ControllerException("Unable to select Robot with ID: " + id);
		GameMessages.showSelected();
	}

	public void moveRobot() throws ControllerException {
		if (selectedRobot == null)
			throw new ControllerException("No selected robot.");

		try {
			selectedRobot.move();
		} catch (RobotException e) {
			throw new ControllerException(e.getMessage());
		}

		GameMessages.showMoved();
	}

	public void rotateRobot(boolean rotateLeft) throws ControllerException {
		if (selectedRobot == null)
			throw new ControllerException("No selected robot.");

		if (rotateLeft)
			selectedRobot.rotateLeft();
		else
			selectedRobot.rotateRight();

		GameMessages.showRotated(rotateLeft);
	}

	public void reportRobot() throws ControllerException {
		if (selectedRobot == null)
			throw new ControllerException("No selected robot.");
		GameMessages.showSingleDashedLine();
		GameMessages.showCenteredLine("Report of Robot");
		GameMessages.showSingleDashedLine();
		GameMessages.showNewLine(selectedRobot.toString());
		GameMessages.showSingleDashedLine();
	}

	public void showRobotsList() throws ControllerException {
		if (allRobots.isEmpty())
			throw new ControllerException("The List is empty");
		for (Robot robot : allRobots.values())
			GameMessages.showNewLine(robot.toString());
	}

	public void showMainMenu() {
		GameMessages.showDoubleDashedLine();
		GameMessages.showCenteredLine("Simple Robot Simulator");
		GameMessages.showDoubleDashedLine();
		GameMessages.showSelectCommandNumber();
		GameMessages.showNumberedLine(1, "Add new Robot");
		GameMessages.showNumberedLine(2, "Play with a Robot");
		GameMessages.showNumberedLine(3, "List all Robots");
		GameMessages.showNumberedLine(4, "Change board dimension (default: 5x5");
		GameMessages.showNumberedLine(5, "Exit");
		GameMessages.showInputLine();
	}

	public void showRobotSelectionPage() throws ControllerException {
		if (allRobots.isEmpty())
			throw new ControllerException("No Robot to play with it. Add a robot before.");

		GameMessages.showDoubleDashedLine();
		GameMessages.showCenteredLine("Robot Selection Page");
		GameMessages.showDoubleDashedLine();
		GameMessages.showTitleLine("Select a robot ID from the List:");
		GameMessages.showSingleDashedLine();
		showRobotsList();
		GameMessages.showSingleDashedLine();
		GameMessages.showTitleLine("To go back to the main menu, enter 0");
		GameMessages.showInputLine();

	}

	public void showRobotPlayPage() {
		GameMessages.showDoubleDashedLine();
		GameMessages.showCenteredLine("Play with seleted Robot");
		GameMessages.showDoubleDashedLine();
		GameMessages.showSelectCommandNumber();
		GameMessages.showNumberedLine(1, "Move");
		GameMessages.showNumberedLine(2, "Rotate to Left");
		GameMessages.showNumberedLine(3, "Rotate to Right");
		GameMessages.showNumberedLine(4, "Report Status");
		GameMessages.showNumberedLine(5, "Back to Robots selection page");
		GameMessages.showNumberedLine(6, "Back to main menu");
		GameMessages.showInputLine();

	}

	public int getAllRobotNumbers() {
		return robotNumber;
	}
}
