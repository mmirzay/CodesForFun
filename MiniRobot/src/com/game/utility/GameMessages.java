package com.game.utility;

public class GameMessages {
	private static int length = 50;

	private GameMessages() {

	}

	public static void showAdded() {
		showMessage("Robot added successfully.");
	}

	public static void showSelected() {
		showMessage("Robot selected successfully.");
	}

	public static void showMoved() {
		showMessage("Robot moved successfully.");
	}

	public static void showRotated(boolean rotateLeft) {
		showMessage("Robot rotated " + (rotateLeft ? "left" : "right") + " successfully.");
	}

	public static void showCenteredLine(String line) {
		int spaces = (length - line.length()) / 2;
		for (int i = 0; i < spaces; i++)
			System.out.print(" ");
		System.out.print(line);
		showEmptyLine();
	}

	public static void showNewLine(String line) {
		System.out.println("   - " + line);
	}

	public static void showMessage(String mssg) {
		System.out.println(" >>> " + mssg);
	}

	public static void showDoubleDashedLine() {
		for (int i = 0; i < length; i++)
			System.out.print("=");
		showEmptyLine();
	}

	public static void showSingleDashedLine() {
		for (int i = 0; i < length; i++)
			System.out.print("-");
		showEmptyLine();
	}

	public static void showEmptyLine() {
		System.out.println();
	}

	public static void showTitleLine(String title) {
		System.out.println("# " + title);
	}

	public static void showNumberedLine(int number, String line) {
		System.out.println(" " + number + "- " + line);
	}

	public static void showSelectCommandNumber() {
		showTitleLine("Select the number of command");
	}

	public static void showInputLine() {
		System.out.print("> ");
	}

	public static void showNewRobotHeader() {
		showDoubleDashedLine();
		showCenteredLine("New Robot Configuration");
		showDoubleDashedLine();
	}

	public static void showNewRobotGetName() {
		showTitleLine("Enter a name for Robot:");
		showInputLine();
	}

	public static void showNewRobotGetX() {
		showTitleLine("Enter place X for Robot:");
		showInputLine();
	}

	public static void showNewRobotGetY() {
		showTitleLine("Enter place Y for Robot:");
		showInputLine();
	}

	public static void showNewRobotDirection() {
		showTitleLine("Select direction for Robot:");
		showNumberedLine(1, Direction.North.name());
		showNumberedLine(2, Direction.East.name());
		showNumberedLine(3, Direction.South.name());
		showNumberedLine(4, Direction.West.name());
		showInputLine();
	}

	public static void showWrongCommand() {
		showMessage("Wrong command number. Try again");
	}
}
