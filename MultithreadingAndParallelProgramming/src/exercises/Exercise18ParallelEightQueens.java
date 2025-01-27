package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise18ParallelEightQueens {
	public static List<String> list = Collections
			.synchronizedList(new ArrayList<String>());

	public static final int SIZE = 8; // The size of the chess board

	public int queensPositionOnFirstRow;

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		findQueensCombinations();
		long endTime = System.currentTimeMillis();
		System.out.println("\nParallel time with "
				+ Runtime.getRuntime().availableProcessors()
				+ " processors is " + (endTime - startTime) + " milliseconds");
		System.out.println(list.size());
	}
	
	public Exercise18ParallelEightQueens(int i) {
		queensPositionOnFirstRow = i;
	}

	public static void findQueensCombinations() {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		for (int i = 0; i < 8; i++) {
			Exercise18ParallelEightQueens queens = new Exercise18ParallelEightQueens(i);
			executor.execute(queens.new RunOnSeparateThread());
		}
		System.out.println(5);
		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				executor.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class RunOnSeparateThread implements Runnable {
		public void run() {
			search(); // Search for a solution
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/** Search for a solution */
	private void search() {
		// k - indicates the number of queens placed so far
		// We are looking for a position in the kth row to place a queen
		int k = 1;
		// queens are placed at (i, queens[i])
		// -1 indicates that no queen is currently placed in the ith row
		// Initially, place a queen at (0, 0) in the 0th row
		// last position is for k (number of queens placed so far
		int[] position = { queensPositionOnFirstRow, -1, -1, -1, -1, -1, -1, -1 };
		int[] queens = position.clone();
		while (true){
			while (k >= 1 && k < SIZE) {
				// Find a position to place a queen in the kth row
				int j = findPosition(k, queens);
				if (j < 0) {
					queens[k] = -1;
					k--; // back track to the previous row
				} else {
					queens[k] = j;
					k++;
				}
			}
	
			if (k == 0) {
				break; // No solution
			} else {
				String combination = "";
				for (int i = 0; i < SIZE; i++) {
					combination += queens[i] + " ";
				}
				list.add(combination);
				//A solution is found
				k--;
			}
		}
	}

	public int findPosition(int k, int[] queens) {
		int start = queens[k] + 1; // Search for a new placement

		for (int j = start; j < SIZE; j++) {
			if (isValid(k, j, queens))
				return j; // (k, j) is the place to put the queen now
		}

		return -1;
	}

	/** Return true if a queen can be placed at (row, column) */
	public boolean isValid(int row, int column, int[] queens) {
		for (int i = 1; i <= row; i++)
			if (queens[row - i] == column // Check column
					|| queens[row - i] == column - i // Check upleft diagonal
					|| queens[row - i] == column + i) // Check upright diagonal
				return false; // There is a conflict
		return true; // No conflict
	}

}
