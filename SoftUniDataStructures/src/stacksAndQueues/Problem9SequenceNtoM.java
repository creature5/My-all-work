package stacksAndQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Problem9SequenceNtoM {

	public static void main(String[] args) {
		int n, m;
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter n : ");
		n = input.nextInt();
		System.out.println();
		System.out.print("Please enter m : ");
		m = input.nextInt();
		Queue<Item> queue = new LinkedList<Item>(); //from class Item
		queue.add(new Item(n, null));
		
		while (queue.size() > 0) {
			Item currentItem = queue.poll();
			if (currentItem.value < m) {
				queue.add(new Item(currentItem.value * 2, currentItem));
				queue.add(new Item(currentItem.value + 2, currentItem));
				queue.add(new Item(currentItem.value + 1, currentItem));
			}
			if (currentItem.value == m) {
				printSequenceNtoM(currentItem);
				break;
			}
			if (queue.size() == 0) {
				System.out.println("No solution.");
			}
		}
		input.close();
	}

	private static void printSequenceNtoM(Item currentItem) {
		Stack<Integer> stack = new Stack<Integer>();
		while (currentItem != null) {
			stack.push(currentItem.value);
			currentItem = currentItem.previousItem;
		}
		
		while (stack.size() > 0) {
			System.out.print(stack.pop());
			if (!stack.isEmpty()) {
				System.out.print(" -> ");
			}
		}
	}
}
