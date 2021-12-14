package adventofcode2021.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
		long counter = 0;
		while (input.hasNext()) {
			String p = input.nextLine();
			String str[] = p.split("\\|");
			String out[] = str[1].split(" ");
			String seven[] = str[0].split(" ");
			String result = findNumber(seven, out);
			counter += Long.parseLong(result);
		}
		System.out.println("half2 result: " + counter);
		input.close();

	}

	private static String findNumber(String[] pattern, String[] out) {
		String result = "";

		HashMap<Integer, String> numbers = new HashMap<>();
		for (String s : pattern)
			if (s.length() == 2)
				numbers.put(1, s);
			else if (s.length() == 4)
				numbers.put(4, s);
			else if (s.length() == 3)
				numbers.put(7, s);
			else if (s.length() == 7)
				numbers.put(8, s);
		// 9
		String four = numbers.get(4);
		String seve = numbers.get(7);
		// 9
		for (String s : pattern)
			if (s.length() == 6)
				if (s.contains(four.charAt(0) + "") && s.contains(four.charAt(1) + "")
						&& s.contains(four.charAt(2) + "") && s.contains(four.charAt(3) + "")) {
					numbers.put(9, s);
					break;
				}
		// 0
		for (String s : pattern)
			if (s.length() == 6 && s.equals(numbers.get(9)) == false)
				if (s.contains(seve.charAt(0) + "") && s.contains(seve.charAt(1) + "")
						&& s.contains(seve.charAt(2) + "")) {
					numbers.put(0, s);
					break;
				}
		// 6
		for (String s : pattern)
			if (s.length() == 6 && s.equals(numbers.get(9)) == false && s.equals(numbers.get(0)) == false) {
				numbers.put(6, s);
				break;
			}
		// 3
		for (String s : pattern)
			if (s.length() == 5 && s.contains(seve.charAt(0) + "") && s.contains(seve.charAt(1) + "")
					&& s.contains(seve.charAt(2) + "")) {
				numbers.put(3, s);
				break;
			}

		// 5
		String six = numbers.get(6);
		for (String s : pattern)
			if (s.length() == 5 && six.contains(s.charAt(0) + "") && six.contains(s.charAt(1) + "")
					&& six.contains(s.charAt(2) + "") && six.contains(s.charAt(3) + "")
					&& six.contains(s.charAt(4) + "")) {
				numbers.put(5, s);
				break;
			}
		// 2
		for (String s : pattern)
			if (s.length() == 5 && s.equals(numbers.get(3)) == false && s.equals(numbers.get(5)) == false) {
				numbers.put(2, s);
				break;
			}
		for (int i = 0; i <= 9; i++) {
			char[] chars = numbers.get(i).toCharArray();
			Arrays.sort(chars);
			numbers.put(i, Arrays.toString(chars));
		}
		for (String s : out) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			for (int i = 0; i <= 9; i++)
				if (numbers.get(i).equals(Arrays.toString(chars)))
					result += "" + i;
		}
		return result;
	}

}