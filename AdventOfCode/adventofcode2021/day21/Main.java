package adventofcode2021.day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day21input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int diceRollesCounter = 0;
		int losePlayerScore = 0;
		String line = input.nextLine();
		int player1Position = Integer.parseInt(line.charAt(line.length() - 1) + "");
		line = input.nextLine();
		int player2Position = Integer.parseInt(line.charAt(line.length() - 1) + "");
		int player1Score = 0;
		int player2Score = 0;
		boolean turn = true;// player1 turn== true, player2 turn==false
		int space = 1;
		diceRollesCounter += 3;
		while (player1Score < 1000 && player2Score < 1000) {
			int diceLastScore = 3 * (space + 1);
			if (turn) {
				player1Position = (player1Position + diceLastScore) % 10;
				if (player1Position == 0)
					player1Position = 10;
				player1Score += player1Position;
				turn = false;
			} else {
				player2Position = (player2Position + diceLastScore) % 10;
				if (player2Position == 0)
					player2Position = 10;
				player2Score += player2Position;
				turn = true;
			}
			space = (space + 3) % 100;
			if (space == 0)
				space = 100;
			diceRollesCounter += 3;

		}
		if (player1Score < player2Score)
			losePlayerScore = player1Score;
		else
			losePlayerScore = player2Score;
		diceRollesCounter -= 3;
		System.out.println("half1 result: " + diceRollesCounter * losePlayerScore);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		input.close();

	}

}