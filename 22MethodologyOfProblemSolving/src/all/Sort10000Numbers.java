package all;

import java.util.Arrays;
import java.util.Random;

public class Sort10000Numbers {
	public static void main(String[] args) {
		int[] numbers = new int[10000];
		Random rnd = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rnd.nextInt(2 * numbers.length);
		}
		sortNumbers(numbers);
		System.out.println(Arrays.toString(numbers));
	}

	private static void sortNumbers(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[minIndex]) {
					minIndex = j;
				}
			}
			int oldNumber = numbers[i];
			numbers[i] = numbers[minIndex];
			numbers[minIndex] = oldNumber;
		}
	}
}
