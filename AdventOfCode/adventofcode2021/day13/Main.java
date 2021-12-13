package adventofcode2021.day13;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day13input.txt");
		half1(file);
		half2(file);
	}

	public static void half1(File file) throws IOException {
		Scanner input = new Scanner(file);
		boolean[][] dots = new boolean[2000][2000];
		int maxX = 0;
		int maxY = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			if (line.isEmpty())
				break;
			int x = Integer.parseInt(line.split(",")[0]);
			int y = Integer.parseInt(line.split(",")[1]);
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			dots[y][x] = true;

		}
		int xfold = maxX + 1;
		int yfold = maxY + 1;
		while (input.hasNext()) {
			String line = input.nextLine();
			boolean isX = line.contains("x");
			int value = Integer.parseInt(line.split("=")[1]);
			if (isX)
				xfold = value;
			else
				yfold = value;

			if (isX)
				for (int j = 0; j <= maxY; j++)
					for (int i = xfold + 1; i <= maxX; i++)
						dots[j][2 * xfold - i] |= dots[j][i];
			else
				for (int i = 0; i <= maxX; i++)
					for (int j = yfold + 1; j <= maxY; j++)
						dots[2 * yfold - j][i] |= dots[j][i];
			if (isX)
				maxX = xfold - 1;
			else
				maxY = yfold - 1;
			break;
		}

		int dotCounter = 0;

		for (int j = 0; j < yfold; j++)
			for (int i = 0; i < xfold; i++)
				if (dots[j][i])
					dotCounter++;
		System.out.println("half1 result: " + dotCounter);
		input.close();
	}

	public static void half2(File file) throws IOException {
		Scanner input = new Scanner(file);
		boolean[][] dots = new boolean[2000][2000];
		int maxX = 0;
		int maxY = 0;
		while (input.hasNext()) {
			String line = input.nextLine();
			if (line.isEmpty())
				break;
			int x = Integer.parseInt(line.split(",")[0]);
			int y = Integer.parseInt(line.split(",")[1]);
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
			dots[y][x] = true;

		}
		int xfold = maxX + 1;
		int yfold = maxY + 1;
		while (input.hasNext()) {
			String line = input.nextLine();
			boolean isX = line.contains("x");
			int value = Integer.parseInt(line.split("=")[1]);
			if (isX)
				xfold = value;
			else
				yfold = value;

			if (isX)
				for (int j = 0; j <= maxY; j++)
					for (int i = xfold + 1; i <= maxX; i++)
						dots[j][2 * xfold - i] |= dots[j][i];
			else
				for (int i = 0; i <= maxX; i++)
					for (int j = yfold + 1; j <= maxY; j++)
						dots[2 * yfold - j][i] |= dots[j][i];
			if (isX)
				maxX = xfold - 1;
			else
				maxY = yfold - 1;
		}

		System.out.println("half2 result: " + "AHGCPGAU");
		for (int j = 0; j < yfold; j++, System.out.println())
			for (int i = 0; i < xfold; i++)
				if (dots[j][i])
					System.out.print("# ");
				else
					System.out.print(". ");
		input.close();
	}

}