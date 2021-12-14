package adventofcode2021.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day6input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		LinkedList<Integer> days = new LinkedList<>();
		for (String s : input.nextLine().split(","))
			days.add(Integer.parseInt(s));
		for (int d = 1; d <= 80; d++) {
			int newfish = 0;
			for (int i = 0; i < days.size(); i++)
				days.addLast(days.removeFirst() - 1);
			for (int i = 0; i < days.size(); i++) {
				int r = days.removeFirst();
				if (r < 0) {
					newfish++;
					days.addLast(6);
				} else
					days.addLast(r);
			}
			for (int f = 1; f <= newfish; f++)
				days.addLast(8);
		}
		System.out.println("half1 result: " + days.size());
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		input.close();

	}

}