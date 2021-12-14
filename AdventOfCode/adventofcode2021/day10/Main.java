package adventofcode2021.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day10input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int totalSyntaxError = 0;
		String openChars = "([{<";
		while (input.hasNext()) {
			String s = input.nextLine();
			LinkedList<Character> stack = new LinkedList<>();
			for (char ch : s.toCharArray()) {
				if (openChars.contains(ch + ""))
					stack.addLast(ch);
				else {
					char last = stack.removeLast();
					if (last != '(' && ch == ')') {
						totalSyntaxError += 3;
						break;
					} else if (last != '[' && ch == ']') {
						totalSyntaxError += 57;
						break;
					} else if (last != '{' && ch == '}') {
						totalSyntaxError += 1197;
						break;
					} else if (last != '<' && ch == '>') {
						totalSyntaxError += 25137;
						break;
					}
				}
			}
		}
		System.out.println("half1 result: " + totalSyntaxError);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String openChars = "([{<";
		ArrayList<Long> scores = new ArrayList<>();
		while (input.hasNext()) {
			String s = input.nextLine();
			LinkedList<Character> stack = new LinkedList<>();
			boolean isChunk = false;
			for (char ch : s.toCharArray()) {
				if (openChars.contains(ch + ""))
					stack.addLast(ch);
				else {
					char last = stack.removeLast();
					if (last != '(' && ch == ')') {
						isChunk = true;
						break;
					} else if (last != '[' && ch == ']') {
						isChunk = true;
						break;
					} else if (last != '{' && ch == '}') {
						isChunk = true;
						break;
					} else if (last != '<' && ch == '>') {
						isChunk = true;
						break;
					}
				} // else
			} // for
			if (isChunk)
				continue;
			long score = 0;
			while (stack.isEmpty() == false) {
				char last = stack.removeLast();
				if (last == '(')
					score = score * 5 + 1;
				else if (last == '[')
					score = score * 5 + 2;
				else if (last == '{')
					score = score * 5 + 3;
				else if (last == '<')
					score = score * 5 + 4;
			}
			scores.add(score);
		} // while
		Long[] sortedScores = scores.toArray(new Long[0]);
		Arrays.sort(sortedScores);
		System.out.println("half2 result: " + sortedScores[sortedScores.length / 2]);
		input.close();
	}

}