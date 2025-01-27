package all;

import java.util.*;

public class Problem2PrimesMatrixWithSieveOfEratosthenes {
	private static final int PRIMES_INTERVAL_MAX = 1000000;

	public static void main(String[] args) {
		int matrixDimensions = 0;
		try {
			matrixDimensions = readInput();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int primesNeededCount = matrixDimensions * matrixDimensions;
		Integer[] primeNumbers = findPrimeNumbers(primesNeededCount);
		printPrimesMatrix(primeNumbers, matrixDimensions);
	}

	private static void printPrimesMatrix(Integer[] primeNumbers,
			int matrixDimensions) {
		StringBuilder primesMatrixForPrint = new StringBuilder();
		int nextPrime;
		for (int row = 0; row < matrixDimensions; row++) {
			for (int col = 0; col < matrixDimensions; col++) {
				nextPrime = primeNumbers[row * matrixDimensions + col];
				primesMatrixForPrint.append(String.format("%5d", nextPrime));
			}
			primesMatrixForPrint.append("\n");
		}
		System.out.println(primesMatrixForPrint.toString());
	}

	private static Integer[] findPrimeNumbers(int primesNeededCount) {
		boolean[] notPrime = new boolean[PRIMES_INTERVAL_MAX];
		notPrime[0] = true;
		notPrime[1] = true;

		List<Integer> primeNumbers = new ArrayList<Integer>(primesNeededCount);

		for (int currentNumber = 2; currentNumber < PRIMES_INTERVAL_MAX; currentNumber++) {
			if (notPrime[currentNumber] == false) {
				primeNumbers.add(currentNumber);
				if (primeNumbers.size() == primesNeededCount) {
					break;
				}
				for (int notPrimeNumber = currentNumber * 2; notPrimeNumber < notPrime.length; notPrimeNumber += currentNumber) {
					notPrime[notPrimeNumber] = true;
				}
			}
		}

		return primeNumbers.toArray(new Integer[primeNumbers.size()]);
	}

	private static int readInput() throws Exception {
		Scanner input = new Scanner(System.in);
		String inputLine = input.nextLine();
		input.close();
		int n = 0;

		try {
			n = Integer.parseInt(inputLine);
		} catch (NumberFormatException exception) {
			System.out.println("Invalid input: " + inputLine);
		}

		if (n >= 200) {
			throw new Exception("N > 200");
		}

		return n;
	}
}
