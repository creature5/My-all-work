package stacksAndQueues;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Problem8LinkedQueueUnitTests {

	@Test
	public void enqueueEmptyQueueShouldAddElement() {
		Problem7LinkedQueue<Integer> queue = new Problem7LinkedQueue<Integer>();
		assertEquals(0, queue.count());
		queue.enqueue(5);
		assertEquals(1, queue.count());
	}
	
	@Test
	public void enqueueDequeueShouldWorkCorrectly() {
		Problem7LinkedQueue<String> queue = new Problem7LinkedQueue<String>();
		assertEquals(0, queue.count());
		String element = "some value";
		queue.enqueue(element);
		assertEquals(1, queue.count());
		String elementFromQueue = queue.dequeue();
		assertEquals(0, queue.count());
		assertEquals(element, elementFromQueue);
	}
	
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testDequeueFromEmptyStack() {
		Problem7LinkedQueue<Integer> queue = new Problem7LinkedQueue<Integer>();		
		thrown.expect( NoSuchElementException.class );
		thrown.expectMessage("Queue is empty");
		queue.dequeue();
	}
	
	@Test
	public void enqueueDequeue1000ElementsShouldWorkCorrectly() {
		Problem7LinkedQueue<Integer> queue = new Problem7LinkedQueue<Integer>();
		int numberOfElements = 1000;
		for (int i = 1; i <= numberOfElements; i++) {
			queue.enqueue(i);
		}
		
		int element;
		for (int i = 1; i <= numberOfElements; i++) {
			assertEquals(numberOfElements - i + 1, queue.count());
			element = queue.dequeue();
			assertEquals(i, element);
			assertEquals(numberOfElements - i, queue.count());
		}
	}
	
	@Test
	public void enqueue500ElementsToArrayShouldWorkCorrectly() {
		Problem7LinkedQueue<Integer> queue = new Problem7LinkedQueue<Integer>();
		int numberOfElements = 500;
		Integer[] array = new Integer[numberOfElements];
		for (int i = 1; i <= numberOfElements; i++) {
			array[i-1] = i;
		}
		for (int i = array.length - 1; i >= 0; i--) {
			queue.enqueue(array[i]);
		}
		Object[] arrayFromQueue = queue.toArray();
		assertArrayEquals( array, arrayFromQueue);
	}
	
	@Test
	public void testEmptyStackToArray() {
		Problem7LinkedQueue<Integer> queue = new Problem7LinkedQueue<Integer>();
		Integer[] array =  queue.toArray();
		assertArrayEquals( null, array);
	}
}