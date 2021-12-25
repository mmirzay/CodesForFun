package adventofcode2021.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day25input.txt");
		half1(file);
		half2(file);
	}

	static char chars[][];
	static char old[][];
	static boolean moved;

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());

		int row = lines.size();
		int col = lines.get(0).length();
		chars = new char[row][col];
		old = new char[row][col];
		for (int i = 0; i < row; i++) {
			int j = 0;
			for (char ch : lines.get(i).toCharArray())
				chars[i][j++] = ch;
		}
		int step = 1;
		for (;; step++) {
			for (int i = 0; i < row; i++)
				old[i] = chars[i].clone();
			moved = false;
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++)
					moveForward(chars, row, col, i, j);

			for (int i = 0; i < row; i++)
				old[i] = chars[i].clone();
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++)
					moveDownward(chars, row, col, i, j);

			if (moved == false)
				break;
		}
		System.out.println("half1 result: " + step);
		input.close();
	}

	private static void moveForward(char[][] chars, int row, int col, int i, int j) {
		char ch = old[i][j];
		if (ch != '>')
			return;

		int fi = i;
		int fj = (j + 1) % col;
		if (old[fi][fj] == '.') {
			chars[fi][fj] = '>';
			chars[i][j] = '.';
			moved = true;
		}

	}

	private static void moveDownward(char[][] chars, int row, int col, int i, int j) {
		char ch = old[i][j];
		if (ch != 'v')
			return;

		int fi = (i + 1) % row;
		int fj = j;
		if (old[fi][fj] == '.') {
			chars[fi][fj] = 'v';
			chars[i][j] = '.';
			moved = true;
		}

	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		input.close();
		System.out.println("half2 result: ");
	}
}