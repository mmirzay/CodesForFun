package adventofcode2021.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day9input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int risk = 0;
		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());

		int row = lines.size();
		int column = lines.get(0).length();
		int nums[][] = new int[row][column];
		for (int i = 0; i < row; i++) {
			int j = 0;
			for (char ch : lines.get(i).toCharArray())
				nums[i][j++] = Integer.parseInt(ch + "");
		}
		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++) {
				int r = 10;
				int d = 10;
				int l = 10;
				int t = 10;
				int n = nums[i][j];
				if (j + 1 < column)
					r = nums[i][j + 1];
				if (i + 1 < row)
					d = nums[i + 1][j];
				if (j - 1 >= 0)
					l = nums[i][j - 1];
				if (i - 1 >= 0)
					t = nums[i - 1][j];
				if (n < r && n < d && n < l && n < t)
					risk += n + 1;

			}
		System.out.println("half1 result: " + risk);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		input.close();
	}

}