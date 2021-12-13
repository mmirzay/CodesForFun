package adventofcode2021.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("adventofcode/day1input.txt");
		half1(file);
		half2(file);
	}

	public static void half1(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int measurment = input.nextInt();
		int largerCounter = 0;
		while (input.hasNext()) {
			int newMeasurment = input.nextInt();
			if (newMeasurment > measurment)
				largerCounter++;
			measurment = newMeasurment;
		}
		System.out.println("half1 result: " + largerCounter);
		input.close();
	}

	public static void half2(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int frame1 = input.nextInt();
		int frame2 = input.nextInt();
		int frame3 = input.nextInt();
		int largerCounter = 0;
		while (input.hasNext()) {
			int frame4 = input.nextInt();
			if (frame4 > frame1)
				largerCounter++;
			frame1 = frame2;
			frame2 = frame3;
			frame3 = frame4;
		}
		System.out.println("half2 result: " + largerCounter);
		input.close();
	}
}