package exercises;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Exercise10UseSynchronizedSets {

	private static Set<Integer> hashSet = Collections
			.synchronizedSet(new HashSet<Integer>());;

	public static void main(String[] args) {

		System.out.println("ConcurrentModificationException test");

		Thread thread1 = new Thread(new CreateHashSetAndAddNumberToIt());
		Thread thread2 = new Thread(new TraverseHashSet());
		thread1.start();
		thread2.start();
	}

	public static class CreateHashSetAndAddNumberToIt implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					int number = (int) (Math.random() * 1000) + 1;
					hashSet.add(number);
					System.out.println(number + " is added to the list");
					Thread.sleep(1000);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static class TraverseHashSet implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					synchronized (hashSet) { // Must synchronize it
						Iterator<Integer> iterator = hashSet.iterator();
						while (iterator.hasNext()) {
							System.out.println(iterator.next());
						}
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}
