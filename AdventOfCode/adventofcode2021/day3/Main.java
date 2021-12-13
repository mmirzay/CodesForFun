package adventofcode2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day3input.txt");
		half1(file);
		half2(file);
	}

	public static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int gama = 0;
		int epsilon = 0;
		int zeros[] = null;
		int ones[] = null;
		while (input.hasNext()) {
			String strBits = input.nextLine();
			if (zeros == null) {
				zeros = new int[strBits.length()];
				ones = new int[zeros.length];
			}
			for (int i = 0; i < zeros.length; i++) {
				boolean isZero = strBits.charAt(i) == '0';
				zeros[i] += isZero ? 1 : 0;
				ones[i] += isZero ? 0 : 1;
			}
		}
		StringBuilder strGama = new StringBuilder();
		StringBuilder strEpsilon = new StringBuilder();
		for (int i = 0; i < zeros.length; i++)
			if (zeros[i] > ones[i]) {
				strGama.append("0");
				strEpsilon.append("1");
			} else {
				strGama.append("1");
				strEpsilon.append("0");
			}
		gama = Integer.parseInt(strGama.toString(), 2);
		epsilon = Integer.parseInt(strEpsilon.toString(), 2);
		int powerConsumption = gama * epsilon;
		System.out.println("half1 result: " + powerConsumption);
		input.close();
	}

	public static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int oxygenRate = 0;
		int co2Rate = 0;
		ArrayList<String> allBits = new ArrayList<>(1500);
		ArrayList<String> oxygenBits = new ArrayList<>(1500);
		ArrayList<String> co2Bits = new ArrayList<>(1500);
		int bitPosition = 0;
		while (input.hasNext())
			allBits.add(input.nextLine());

		oxygenBits.addAll(allBits);
		while (oxygenBits.size() > 1) {
			Boolean isMoreZero = isZeroMoreThanOne(oxygenBits, bitPosition);
			int p = bitPosition;
			if (isMoreZero)
				oxygenBits.removeIf(s -> s.charAt(p) == '1');
			else
				oxygenBits.removeIf(s -> s.charAt(p) == '0');
			bitPosition++;

		}

		co2Bits.addAll(allBits);
		bitPosition = 0;
		while (co2Bits.size() > 1) {
			Boolean isMoreZero = isZeroMoreThanOne(co2Bits, bitPosition);
			int p = bitPosition;
			if (isMoreZero)
				co2Bits.removeIf(s -> s.charAt(p) == '0');
			else
				co2Bits.removeIf(s -> s.charAt(p) == '1');
			bitPosition++;

		}
		oxygenRate = Integer.parseInt(oxygenBits.get(0), 2);
		co2Rate = Integer.parseInt(co2Bits.get(0), 2);
		int lifeSupportrating = oxygenRate * co2Rate;
		System.out.println("half2 result: " + lifeSupportrating);
		input.close();
	}

	private static boolean isZeroMoreThanOne(ArrayList<String> list, int bitPosition) {
		int oneCounter = 0;
		int zeroCounter = 0;
		for (String line : list)
			if (line.charAt(bitPosition) == '0')
				zeroCounter++;
			else
				oneCounter++;
		return zeroCounter > oneCounter;
	}
}