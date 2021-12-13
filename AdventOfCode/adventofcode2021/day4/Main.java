package adventofcode2021.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day4input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int finalScore = -1;
		String[] strNumbers = input.nextLine().split(",");
		int[] numbers = new int[strNumbers.length];
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = Integer.parseInt(strNumbers[i]);

		Map<Integer, Integer[][]> boards = new HashMap<>();
		int boardNumber = 0;
		while (input.hasNext()) {
			input.nextLine();
			boardNumber++;
			Integer[][] boardNumbers = new Integer[5][5];
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					boardNumbers[i][j] = input.nextInt();
			boards.put(boardNumber, boardNumbers);
		}
		mainLoop: for (Integer num : numbers)
			for (int b = 1; b <= boards.size(); b++) {
				Integer[][] board = boards.get(b);
				outer: for (int i = 0; i < 5; i++)
					for (int j = 0; j < 5; j++)
						if (board[i][j] == num) {
							board[i][j] = -1;
							break outer;
						}
				if (checkBoardWin(board)) {
					finalScore = getFinalScore(board, num);
					break mainLoop;
				}
			}

		System.out.println("half1 result: " + finalScore);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		int finalScore = -1;
		String[] strNumbers = input.nextLine().split(",");
		int[] numbers = new int[strNumbers.length];
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = Integer.parseInt(strNumbers[i]);

		Map<Integer, Integer[][]> boards = new HashMap<>();
		int boardNumber = 0;
		while (input.hasNext()) {
			input.nextLine();
			boardNumber++;
			Integer[][] boardNumbers = new Integer[5][5];
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					boardNumbers[i][j] = input.nextInt();
			boards.put(boardNumber, boardNumbers);
		}
		boolean winnerBoards[] = new boolean[boards.size()];
		int maxNumberOfBoards = winnerBoards.length;
		mainLoop: for (Integer num : numbers)
			for (int b = 1; b <= boards.size(); b++) {
				Integer[][] board = boards.get(b);
				outer: for (int i = 0; i < 5; i++)
					for (int j = 0; j < 5; j++)
						if (board[i][j] == num) {
							board[i][j] = -1;
							break outer;
						}
				if (checkBoardWin(board) && winnerBoards[b - 1] == false) {
					winnerBoards[b - 1] = true;
					maxNumberOfBoards--;
					if (maxNumberOfBoards == 0) {
						finalScore = getFinalScore(board, num);
						break mainLoop;
					}
				}
			}

		System.out.println("half2 result: " + finalScore);
		input.close();
	}

	private static boolean checkBoardWin(Integer[][] board) {
		for (int i = 0; i < 5; i++) {
			int rowSum = 0;
			for (int j = 0; j < 5; j++)
				rowSum += board[i][j];
			if (rowSum == -5)
				return true;
		}

		for (int j = 0; j < 5; j++) {
			int colSum = 0;
			for (int i = 0; i < 5; i++)
				colSum += board[i][j];
			if (colSum == -5)
				return true;
		}
		return false;
	}

	private static int getFinalScore(Integer[][] board, int lastNumber) {
		int sumOfUnmarked = 0;
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				if (board[i][j] != -1)
					sumOfUnmarked += board[i][j];
		return sumOfUnmarked * lastNumber;
	}

}