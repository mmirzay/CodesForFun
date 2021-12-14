package adventofcode2021.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day8input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int counter = 0;
		while (input.hasNext()) {
			String p = input.nextLine();
			String str[] = p.split("\\|");
			String out[] = str[1].split(" ");
			for (String s : out) {
				int len = s.length();
				if (len == 2 || len == 4 || len == 3 || len == 7)
					counter++;
			}
		}
		System.out.println("half1 result: " + counter);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		input.close();

	}

}