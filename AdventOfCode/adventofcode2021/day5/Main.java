package adventofcode2021.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day5input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int numberOfDangerousPoints = 0;
		int[][] points = new int[1000][1000];
		while (input.hasNext()) {
			String line = input.nextLine();
			int x1 = Integer.parseInt(line.split(" -> ")[0].split(",")[0]);
			int y1 = Integer.parseInt(line.split(" -> ")[0].split(",")[1]);
			int x2 = Integer.parseInt(line.split(" -> ")[1].split(",")[0]);
			int y2 = Integer.parseInt(line.split(" -> ")[1].split(",")[1]);
			if (y1 == y2)
				if (x1 < x2)
					for (int j = x1; j <= x2; j++)
						points[y1][j] += 1;
				else
					for (int j = x2; j <= x1; j++)
						points[y1][j] += 1;
			else if (x2 == x1)
				if (y1 < y2)
					for (int i = y1; i <= y2; i++)
						points[i][x1] += 1;
				else
					for (int i = y2; i <= y1; i++)
						points[i][x1] += 1;
		}
		for (int j = 0; j < 1000; j++)
			for (int i = 0; i < 1000; i++)
				if (points[j][i] >= 2)
					numberOfDangerousPoints++;
		System.out.println("half1 result: " + numberOfDangerousPoints);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int numberOfDangerousPoints = 0;
		int[][] points = new int[1000][1000];
		while (input.hasNext()) {
			String line = input.nextLine();
			int x1 = Integer.parseInt(line.split(" -> ")[0].split(",")[0]);
			int y1 = Integer.parseInt(line.split(" -> ")[0].split(",")[1]);
			int x2 = Integer.parseInt(line.split(" -> ")[1].split(",")[0]);
			int y2 = Integer.parseInt(line.split(" -> ")[1].split(",")[1]);
			if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
//				System.out.println("(" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");
				if (x1 < x2 && y1 < y2)
					for (int j = x1, i = y1; j <= x2; i++, j++)
						points[i][j] += 1;
				else if (x2 < x1 && y2 < y1)
					for (int j = x2, i = y2; j <= x1; i++, j++)
						points[i][j] += 1;
				else if (x1 < x2 && y1 > y2)
					for (int j = x1, i = y1; j <= x2; i--, j++)
						points[i][j] += 1;
				else if (x2 < x1 && y1 < y2)
					for (int j = x1, i = y1; i <= y2; i++, j--)
						points[i][j] += 1;

			} else if (y1 == y2)
				if (x1 < x2)
					for (int j = x1; j <= x2; j++)
						points[y1][j] += 1;
				else
					for (int j = x2; j <= x1; j++)
						points[y1][j] += 1;
			else if (x2 == x1)
				if (y1 < y2)
					for (int i = y1; i <= y2; i++)
						points[i][x1] += 1;
				else
					for (int i = y2; i <= y1; i++)
						points[i][x1] += 1;

		}
		for (int j = 0; j < 1000; j++)
			for (int i = 0; i < 1000; i++)
				if (points[j][i] >= 2)
					numberOfDangerousPoints++;
		System.out.println("half2 result: " + numberOfDangerousPoints);
		input.close();

	}

}