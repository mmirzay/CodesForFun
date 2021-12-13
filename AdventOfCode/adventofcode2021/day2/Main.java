package adventofcode2021.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day2input.txt");
		half1(file);
		half2(file);
	}

	public static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int horizontal = 0;
		int depth = 0;
		while (input.hasNext()) {
			String direction = input.next().trim().toLowerCase();
			int value = input.nextInt();
			switch (direction) {
			case "forward" -> horizontal += value;
			case "down" -> depth += value;
			case "up" -> depth -= value;
			}
		}
		int multiply = horizontal * depth;
		System.out.println("half1 result: " + multiply);
		input.close();
	}

	public static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int horizontal = 0;
		int depth = 0;
		int aim = 0;
		while (input.hasNext()) {
			String direction = input.next().trim().toLowerCase();
			int value = input.nextInt();
			switch (direction) {
			case "forward":
				horizontal += value;
				depth += aim * value;
				break;
			case "down":
				aim += value;
				break;
			case "up":
				aim -= value;
				break;
			}
		}
		int multiply = horizontal * depth;
		System.out.println("half2 result: " + multiply);
		input.close();
	}
}