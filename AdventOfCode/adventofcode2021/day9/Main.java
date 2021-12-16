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

	static int basinSize = 0;

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
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

		ArrayList<Integer> basins = new ArrayList<>();

		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++) {
				basinSize = 0;
				int n = nums[i][j];
				if (n < 9)
					computeBasinSize(nums, row, column, i, j);

				if (basinSize != 0)
					basins.add(basinSize);

			}
		basins.sort((a, b) -> b - a);
		long result = basins.get(0) * basins.get(1) * basins.get(2);
		System.out.println("half2 result: " + result);
		input.close();
	}

	private static void computeBasinSize(int[][] nums, int row, int column, int i, int j) {
		basinSize++;

		nums[i][j] = 9;
		if (j + 1 < column && nums[i][j + 1] < 9)
			computeBasinSize(nums, row, column, i, j + 1);
		if (j - 1 >= 0 && nums[i][j - 1] < 9)
			computeBasinSize(nums, row, column, i, j - 1);
		if (i + 1 < row && nums[i + 1][j] < 9)
			computeBasinSize(nums, row, column, i + 1, j);
		if (i - 1 >= 0 && nums[i - 1][j] < 9)
			computeBasinSize(nums, row, column, i - 1, j);
	}
}