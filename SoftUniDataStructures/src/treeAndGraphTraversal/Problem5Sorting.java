package treeAndGraphTraversal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Problem5Sorting {
	private static HashSet<String> differentSets = new HashSet<String>();
	private static List<Integer> list = new ArrayList<Integer>();
	private static int elementsForReversing;
	
	public static void main(String[] args) {
		input();
		Queue<List<Integer>> steps = new LinkedList<List<Integer>>();
		list.add(0);
		steps.add(list);
		List<Integer> currentList1 = new ArrayList<Integer>(list);
		currentList1.remove(currentList1.size()-1);
		differentSets.add(currentList1.toString());
		boolean finish = true;
		while (steps.size() > 0) {
			List<Integer> currentList2 = steps.poll();
			finish = true;
			for (int i = 1; i <= currentList2.size()-1; i++) {
				if (i != currentList2.get(i-1)) {
					finish = false;
				}
			}
			if (finish == true) {
				System.out.println(currentList2.get(currentList2.size()-1));
				break;
			}
			
			for (int i = 0; i < currentList2.size() - elementsForReversing; i++) {
				List<Integer> firstList = new ArrayList<Integer>(currentList2);
				int backwards = 0;
				for (int j = i; j < i + elementsForReversing/2; j++) {
					int swap = currentList2.get(j + elementsForReversing + backwards - 1);
					currentList2.set(j + elementsForReversing + backwards - 1, currentList2.get(j));
					currentList2.set(j, swap);
					backwards -= 2;
				}
				currentList1 = new ArrayList<Integer>(currentList2);
				currentList1.remove(currentList1.size()-1);
				if (differentSets.add(currentList1.toString()) == true) {
					int count = currentList2.get(list.size()-1);
					currentList2.set(currentList2.size()-1, count + 1);
					steps.add(currentList2);
				}
				currentList2 = firstList;	
			}
		}
		if ( finish != true) {
			System.out.println(-1);
		}
	}

	private static void input() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please write count of numbers : ");
		int count = Integer.parseInt(input.nextLine());
		System.out.print("Please write numbers separated one from another by a space : ");
		String[] numbers = input.nextLine().split(" ");
		for (int i = 0; i < count; i++) {
			list.add(Integer.parseInt(numbers[i]));
		}
		System.out.print("Please write number of consecutive elements : ");
		elementsForReversing = Integer.parseInt(input.nextLine());
		input.close();
	}
	
}
