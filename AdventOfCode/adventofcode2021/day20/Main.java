package adventofcode2021.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day20input.txt");
		half1(file);
//		half2(file);
	}

	private static int[][] imagePixel;
	static int[] algorithm;

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int numbeOfLitPixel = 0;
		String strAlgorithm = input.nextLine();
		algorithm = new int[512];
		for (int i = 0; i < 512; i++)
			algorithm[i] = strAlgorithm.charAt(i) == '#' ? 1 : 0;

		input.nextLine();

		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());

		int row = lines.size();
		int col = lines.get(0).length();
		imagePixel = new int[row][col];
		int counter = 0;
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				imagePixel[i][j] = lines.get(i).charAt(j) == '#' ? 1 : 0;
				counter += imagePixel[i][j];
			}
		System.out.println("counter: " + counter);
		for (int time = 1; time <= 2; time++) {
			int[][] middleArray = new int[row + 4][col + 4];
			for (int i = 0; i < row + 2; i++)
				for (int j = 0; j < col + 2; j++)
					middleArray[i][j] = (i - 2 >= 0 && j - 2 >= 0 ? imagePixel[i - 2][j - 2] : 0);

			int[][] firstTime = new int[row += 4][col += 4];
			for (int i = 0; i < row; i++)
				for (int j = 0; j < col; j++)
					firstTime[i][j] = applyAlgorithm(middleArray, i, j, row, col);

			imagePixel = firstTime;
		}

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				numbeOfLitPixel += imagePixel[i][j];
		System.out.println("half1 result: " + numbeOfLitPixel);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		input.close();
	}

	private static int applyAlgorithm(int[][] imagePixel, int i, int j, int row, int col) {
		String bits = "";

		if (i - 1 >= 0 && j - 1 >= 0 && i - 1 < row && j - 1 < col)
			bits += imagePixel[i - 1][j - 1];
		else
			bits += "0";

		if (i - 1 >= 0 && i - 1 < row && j < col && j >= 0)
			bits += imagePixel[i - 1][j];
		else
			bits += "0";

		if (i - 1 >= 0 && j + 1 < col && i - 1 < row && j + 1 >= 0)
			bits += imagePixel[i - 1][j + 1];
		else
			bits += "0";

		if (i < row && j - 1 >= 0 && j - 1 < col && i >= 0)
			bits += imagePixel[i][j - 1];
		else
			bits += "0";

		if (i < row && j < col && i >= 0 && j >= 0)
			bits += imagePixel[i][j];
		else
			bits += "0";

		if (j + 1 < col && i < row && i >= 0 && j + 1 >= 0)
			bits += imagePixel[i][j + 1];
		else
			bits += "0";

		if (i + 1 < row && j - 1 >= 0 && j - 1 < col && i + 1 >= 0)
			bits += imagePixel[i + 1][j - 1];
		else
			bits += "0";

		if (i + 1 < row && j < col && i + 1 >= 0 && j >= 0)
			bits += imagePixel[i + 1][j];
		else
			bits += "0";

		if (i + 1 < row && j + 1 < col && i + 1 >= 0 && j + 1 >= 0)
			bits += imagePixel[i + 1][j + 1];
		else
			bits += "0";

		return algorithm[Integer.parseInt(bits, 2)];
	}

	private static void printArr(int[][] arr) {
		System.out.println("-".repeat(100));
		for (int i = 0; i < arr.length; i++, System.out.println())
			for (int j = 0; j < arr[i].length; j++)
				System.out.print((arr[i][j] == 1 ? "#" : ".") + " ");
		System.out.println("-".repeat(100));
	}

}