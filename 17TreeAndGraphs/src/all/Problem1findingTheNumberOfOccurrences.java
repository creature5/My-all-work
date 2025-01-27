package all;

import java.util.Scanner;

import all.Tree.TreeNode;

public class Problem1findingTheNumberOfOccurrences {

	static int number = 0, occurrences = 0;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Write number for searching: ");
		number = input.nextInt();
		@SuppressWarnings("unchecked")
		Tree<Integer> tree =
				new Tree<Integer>(7,
						new Tree<Integer>(19,
								new Tree<Integer>(6),
								new Tree<Integer>(12),
								new Tree<Integer>(31)),
						new Tree<Integer>(21),
						new Tree<Integer>(14,
								new Tree<Integer>(23),
								new Tree<Integer>(6))
				);

		findingTheNumberOfOccurrences(tree.getRoot());
		System.out.println("the number " + number + " has " + occurrences + " occurrences in the tree.");
		input.close();
	}

	private static void findingTheNumberOfOccurrences(TreeNode<Integer> treeNode) {
		if (treeNode == null) {
			return;
		}
		if (number == treeNode.getValue()) {
			occurrences();
		}
		TreeNode<Integer> child = null;			
		for (int i = 0; i < treeNode.getChildrenCount(); i++) {
			child = treeNode.getChild(i);
			findingTheNumberOfOccurrences(child);
		}	
	}

	private static void occurrences() {
		occurrences++;
	}
}
