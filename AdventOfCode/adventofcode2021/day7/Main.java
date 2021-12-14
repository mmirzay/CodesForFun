package adventofcode2021.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day7input.txt");
		half1(file);
		half2(file);
	}

	private static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		ArrayList<Integer> positions = new ArrayList<>(2000);
		HashMap<Integer, Integer> counters = new HashMap<>(1000);
		HashMap<Integer, ArrayList<Integer>> fuels = new HashMap<>(1000);
		for (String s : input.nextLine().split(",")) {
			Integer num = Integer.parseInt(s);
			positions.add(num);
			counters.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
			fuels.put(num, new ArrayList<>(2000));
		}

		for (Integer i : positions)
			for (Integer j : positions)
				if (fuels.get(i).size() < positions.size())
					fuels.get(i).add(Math.abs(i - j));
		long maxSum = Long.MAX_VALUE;

		for (Integer i : positions) {
			long sum = 0;
			for (Integer j : fuels.get(i))
				sum += j;
			if (sum < maxSum)
				maxSum = sum;
		}
		System.out.println("half1 result: " + maxSum);
		input.close();
	}

	private static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);

		input.close();

	}

}