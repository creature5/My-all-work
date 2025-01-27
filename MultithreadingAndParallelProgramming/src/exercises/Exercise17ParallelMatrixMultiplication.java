package exercises;

import java.util.concurrent.*;

public class Exercise17ParallelMatrixMultiplication {
	public static void main(String[] args) {
		// Create a list
		final int N = 2000;
		double[][] matrix1 = new double[N][N];
		for (int i = 0; i < matrix1.length; i++)
			for (int j = 0; j < matrix1[i].length; j++)
				matrix1[i][j] = 1;

		double[][] matrix2 = new double[N][N];
		for (int i = 0; i < matrix2.length; i++)
			for (int j = 0; j < matrix2[i].length; j++)
				matrix2[i][j] = 1;

		long startTime = System.currentTimeMillis();
		@SuppressWarnings("unused")
		double[][] result = parallelmatrixMultiplication(matrix1, matrix2);
		long endTime = System.currentTimeMillis();
		System.out.println("The number of processors is "
				+ Runtime.getRuntime().availableProcessors());
		System.out
				.println("Time for parallel is " + (endTime - startTime) + " milliseconds");

		

		startTime = System.currentTimeMillis();
		matrixMultiplication(matrix1, matrix2);
		endTime = System.currentTimeMillis();
		System.out.println("Time for sequential is " + (endTime - startTime) + " milliseconds");
		
	}

	private static double[][] matrixMultiplication(double[][] matrix1, double[][] matrix2) {
		double[][] matrix3 = new double[matrix1.length][matrix2[0].length];
		for (int i = 0; i < matrix1.length; i++)
			for (int j = 0; j < matrix3.length; j++)
				for (int k = 0; k < matrix3[0].length; k++)
					matrix3[i][j] += matrix1[i][k] * matrix2[k][j];
		return matrix3;
	}

	public static double[][] parallelmatrixMultiplication(double[][] a, double[][] b) {
		double[][] result = new double[a.length][b[0].length];
		RecursiveAction task = new SumTask(a, b, result);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		return result;
	}

	@SuppressWarnings("serial")
	private static class SumTask extends RecursiveAction {
		private double[][] a;
		private double[][] b;
		private double[][] c;

		public SumTask(double[][] a, double[][] b, double[][] c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public void compute() {
			RecursiveAction[] tasks = new RecursiveAction[a.length];
			for (int i = 0; i < a.length; i++)
				tasks[i] = new AddOneRow(i);

			invokeAll(tasks);
		}

		public class AddOneRow extends RecursiveAction {
			int i;

			public AddOneRow(int i) {
				this.i = i;
			}

			@Override
			public void compute() {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int j = 0; j < a[0].length; j++)
					for (int k = 0; k < c[0].length; k++)
						c[i][j] += a[i][k] + b[k][j];
			}
		}
	}
}
