package exercises;

import java.util.concurrent.*;

public class Exercise4SumTask {
	private Integer sum = new Integer(0);
	private Object lock = new Object();

	public static void main(String[] args) {
		Exercise4SumTask test = new Exercise4SumTask();
		System.out.println("What is sum ? " + test.sum);
	}

	public Exercise4SumTask() {
		ExecutorService executor = Executors.newFixedThreadPool(1000);

		for (int i = 0; i < 1000; i++) {
			executor.execute(new SumTask());
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}
	}

	class SumTask implements Runnable {
		public void run() {
			synchronized (lock) {
				int value = sum.intValue() + 1;
				sum = new Integer(value);
			}
		}
	}
}
