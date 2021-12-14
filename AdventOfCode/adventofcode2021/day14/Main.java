package adventofcode2021.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day14input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int mostCommon = 0;
		int leastCommon = 0;
		String polymerTemplate = input.nextLine();
		input.nextLine();// empty line
		HashMap<String, String> pairs = new HashMap<>(30);
		while (input.hasNext()) {
			String line = input.nextLine();
			String pair = line.split(" -> ")[0];
			String insert = line.split(" -> ")[1];
			pairs.put(pair, insert);

		}
		for (int step = 1; step <= 10; step++) {
			String temp = "";
			int index = 0;
			while (index + 1 < polymerTemplate.length()) {
				String k = polymerTemplate.substring(index, index + 2);
				String v = pairs.get(k);
				temp += k.charAt(0) + v;
				index++;
				if (index + 1 == polymerTemplate.length())
					temp += k.charAt(1);
			}
			polymerTemplate = temp;
		}
		HashMap<Character, Integer> charactersCounter = new HashMap<>();
		for (char ch : polymerTemplate.toCharArray())
			charactersCounter.compute(ch, (k, v) -> (v == null) ? 1 : v + 1);
		mostCommon = charactersCounter.values().stream().mapToInt(v -> v).max().getAsInt();
		leastCommon = charactersCounter.values().stream().mapToInt(v -> v).min().getAsInt();
		int result = mostCommon - leastCommon;
		System.out.println("half1 result: " + result);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		input.close();

	}

}