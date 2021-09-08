package com.game.main;

import java.util.Scanner;

import com.game.controller.GameController;
import com.game.exceptions.ControllerException;
import com.game.utility.GameBoard;
import com.game.utility.GameMessages;

public class Game {
	public static void main(String[] args) {
		GameBoard board = GameBoard.getBoard();
		GameController gameController = new GameController(board);
		try {
			Scanner input = new Scanner(System.in);
			mainMenuLoop: while (true) {
				gameController.showMainMenu();
				int selection = input.nextInt();
				switch (selection) {
				case 1 -> {
					GameMessages.showNewRobotHeader();
					GameMessages.showNewRobotGetName();
					String name = input.next();
					GameMessages.showNewRobotGetX();
					int px = input.nextInt();
					GameMessages.showNewRobotGetY();
					int py = input.nextInt();
					String direction = null;
					directionLoop: while (true) {
						GameMessages.showNewRobotDirection();
						selection = input.nextInt();
						switch (selection) {
						case 1 -> direction = "North";
						case 2 -> direction = "East";
						case 3 -> direction = "South";
						case 4 -> direction = "West";
						default -> GameMessages.showWrongCommand();
						}
						if (direction != null)
							break;
					} // while direction
					try {
						gameController.addNewRobot(px, py, name, direction);
						board.setOccupied(px, py);
					} catch (ControllerException e) {
						GameMessages.showMessage(e.getMessage());
					}
				} // case 1
				case 2 -> {
					robotSelectionLoop: while (true) {
						try {
							gameController.showRobotSelectionPage();
						} catch (ControllerException e1) {
							GameMessages.showMessage(e1.getMessage());
							break robotSelectionLoop;
						}
						selection = input.nextInt();
						if (selection == 0)
							break robotSelectionLoop;
						if (selection < 1 || selection > gameController.getAllRobotNumbers()) {
							GameMessages.showWrongCommand();
							continue robotSelectionLoop;
						}
						try {
							gameController.selectRobot(selection);
						} catch (ControllerException e) {
							GameMessages.showMessage(e.getMessage());
							continue robotSelectionLoop;
						}
						playPageLoop: while (true) {
							gameController.showRobotPlayPage();
							selection = input.nextInt();
							switch (selection) {
							case 1 -> {
								try {
									gameController.moveRobot();
								} catch (ControllerException e) {
									GameMessages.showMessage(e.getMessage());
								}
							}
							case 2 -> {
								try {
									gameController.rotateRobot(true);
								} catch (ControllerException e) {
									GameMessages.showMessage(e.getMessage());
								}
							}
							case 3 -> {
								try {
									gameController.rotateRobot(false);
								} catch (ControllerException e) {
									GameMessages.showMessage(e.getMessage());
								}
							}
							case 4 -> {
								try {
									gameController.reportRobot();
								} catch (ControllerException e) {
									GameMessages.showMessage(e.getMessage());
								}
							}
							case 5 -> {
								break playPageLoop;
							}
							case 6 -> {
								break robotSelectionLoop;
							}
							default -> GameMessages.showWrongCommand();
							}
						} // playPageLoop

					} // robotSelectionLoop
				} // case 2
				case 3 -> {
					try {
						GameMessages.showDoubleDashedLine();
						GameMessages.showCenteredLine("Robots List");
						GameMessages.showDoubleDashedLine();
						gameController.showRobotsList();
						GameMessages.showSingleDashedLine();
					} catch (ControllerException e) {
						GameMessages.showMessage(e.getMessage());
					}
				}
				case 4 -> GameMessages.showMessage("This feature is unavailable. Purchase the premium version :D ");
				case 5 -> {
					break mainMenuLoop;
				}
				default -> GameMessages.showWrongCommand();
				}
			} // mainMenuLoop
			input.close();
		} catch (Exception e) {
			GameMessages.showMessage("Invalid Input. Try again"
					+ "\n"
					+ "error: "+e.getMessage());
			main(args);
		}
	}
}
