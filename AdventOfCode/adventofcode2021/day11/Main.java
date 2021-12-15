package adventofcode2021.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day11input.txt");
		half1(file);
		half2(file);
	}

	static int nums[][];
	static int numberOfFlashes = 0;

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());

		int row = lines.size();
		int col = lines.get(0).length();
		nums = new int[row][col];
		for (int i = 0; i < row; i++) {
			int j = 0;
			for (char ch : lines.get(i).toCharArray())
				nums[i][j++] = Integer.parseInt(ch + "");
		}

		System.out.println("before any step:");
		printArray(nums, row, col);
		for (int step = 1; step <= 100; step++) {
			increaseByOne(nums, row, col);
			while (true) {
				int cell[] = checkFlashes(nums, row, col);
				if (cell == null)
					break;
				int i = cell[0];
				int j = cell[1];
				increaceAdjacents(nums, row, col, i, j);
			}
		}
		System.out.println("after last step :");
		printArray(nums, row, col);
		System.out.println("half1 result: " + numberOfFlashes);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());

		input.close();
		int row = lines.size();
		int col = lines.get(0).length();
		nums = new int[row][col];
		for (int i = 0; i < row; i++) {
			int j = 0;
			for (char ch : lines.get(i).toCharArray())
				nums[i][j++] = Integer.parseInt(ch + "");
		}
		int step;
		for (step = 1;; step++) {
			increaseByOne(nums, row, col);
			while (true) {
				int cell[] = checkFlashes(nums, row, col);
				if (cell == null)
					break;
				int i = cell[0];
				int j = cell[1];
				increaceAdjacents(nums, row, col, i, j);
			}
			if (isAllFlashing(nums, row, col)) {
				break;
			}
		}
		System.out.println("half2 result: " + step);
	}

	private static boolean isAllFlashing(int[][] nums, int row, int col) {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (nums[i][j] != 0)
					return false;
		return true;
	}

	private static void increaceAdjacents(int[][] nums, int row, int col, int i, int j) {
		nums[i][j] = 0;
		if (i - 1 >= 0 && nums[i - 1][j] != 0) // top
			nums[i - 1][j]++;
		if (i - 1 >= 0 && j + 1 < col && nums[i - 1][j + 1] != 0) // top-right
			nums[i - 1][j + 1]++;
		if (j + 1 < col && nums[i][j + 1] != 0) // right
			nums[i][j + 1]++;
		if (i + 1 < row && j + 1 < col && nums[i + 1][j + 1] != 0) // down-right
			nums[i + 1][j + 1]++;
		if (i + 1 < row && nums[i + 1][j] != 0) // down
			nums[i + 1][j]++;
		if (i + 1 < row && j - 1 >= 0 && nums[i + 1][j - 1] != 0) // down-left
			nums[i + 1][j - 1]++;
		if (j - 1 >= 0 && nums[i][j - 1] != 0) // left
			nums[i][j - 1]++;
		if (i - 1 >= 0 && j - 1 >= 0 && nums[i - 1][j - 1] != 0) // top-left
			nums[i - 1][j - 1]++;

	}

	private static int[] checkFlashes(int[][] nums, int row, int col) {
		int[] cell = new int[2];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				if (nums[i][j] > 9) {
					cell[0] = i;
					cell[1] = j;
					numberOfFlashes++;
					return cell;
				}
		return null;
	}

	private static void increaseByOne(int[][] nums, int row, int col) {
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				nums[i][j]++;
	}

	private static void printArray(int[][] nums, int row, int col) {
		for (int i = 0; i < row; i++, System.out.println())
			for (int j = 0; j < col; j++)
				System.out.print(nums[i][j] + " ");
		System.out.println();
	}

}