package treeAndGraphTraversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Problem2RoundDance {
	
	static Map<Integer, Node<Integer>> map = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please write number of edges in the graph: ");
		int edgesCount = Integer.parseInt(input.nextLine());
		System.out.print("Please write first Node from whom to start: ");
		int startNode = Integer.parseInt(input.nextLine());
		Node<Integer> node = new Node<Integer>(startNode);
		map.put(startNode, node);
		for (int i = 1; i <= edgesCount; i++) {
			System.out.println("Write edge between nodes with space between them(edge " + i + ") : " );
			String[] edge = input.nextLine().split(" ");
			int firstValue = Integer.parseInt(edge[0]);
			Node<Integer> firstNode = getTreeNodeByValue(firstValue);
			int secondValue = Integer.parseInt(edge[1]);
			Node<Integer> secondNode = getTreeNodeByValue(secondValue);
			firstNode.childNodes.add(secondNode);
			secondNode.childNodes.add(firstNode);
		}
		//DepthFirstSearch
		theLongestRoundDance(startNode);
		input.close();
	}
	
	private static void theLongestRoundDance(int startNode) {
		int lengthOfPath = 0, lengthOfPathMax = 0;
		Node<Integer> node = getTreeNodeByValue(startNode);
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		LinkedList<Integer> isVisisted = new LinkedList<Integer>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.peek();
			int stackSize = stack.size();
			if (!isVisisted.contains(node.element)) {
				isVisisted.add(node.element);
				lengthOfPath++;
				for (int i = 0; i < node.childNodes.size(); i++) {
					if (!isVisisted.contains(node.childNodes.get(i).element)) {
						stack.push(node.childNodes.get(i));
					}
				}
			} 
			if (stackSize == stack.size()) {
				if (lengthOfPath > lengthOfPathMax) {
					lengthOfPathMax = lengthOfPath;
				}
				stack.pop();
				lengthOfPath -= 1;
			}	
		}
		System.out.println(lengthOfPathMax);
		
	}

	//dictionary to map nodes by their value
	private static Node<Integer> getTreeNodeByValue(
			int value) {
		if (!map.containsKey(value)) {
			map.put(value, new Node<Integer>(value));
		}
		return map.get(value);
	}
	
}
