package all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem1Dijkstra {
	
	private static final int MAX_NUMBER_OF_NODES = 5010;
	
    private PriorityQueue<Problem1Node> priorityQueue = new PriorityQueue<Problem1Node>();
    private HashMap<String, Integer> nodesStringToInt = new HashMap<String, Integer>();

    @SuppressWarnings("unchecked")
	private List<Problem1Node>[] edges = new List[MAX_NUMBER_OF_NODES];
    
    private int numberOfNodes = 0;
    
    private int[] distance = new int[MAX_NUMBER_OF_NODES];
    private int[] parents = new int[MAX_NUMBER_OF_NODES];
    
    private String[] nodesIntToString = new String[MAX_NUMBER_OF_NODES];
    
    private Scanner inputLine;

	private int isContained(String s) {
		if (!nodesStringToInt.containsKey(s)) {
			numberOfNodes++;
			edges[numberOfNodes] = new ArrayList<Problem1Node>();
			nodesStringToInt.put(s, numberOfNodes);
			nodesIntToString[numberOfNodes] = s;
			return numberOfNodes;
		} else {
			return nodesStringToInt.get(s);
		}
	}

	public void readInput() {
        int x,y;
        Problem1Node current;
        inputLine = new Scanner(System.in);
        while (true) {
        	String input;
        	try {
        		input = inputLine.nextLine();
        	} catch (NoSuchElementException e) {
        		break;
        	}
        	if (input.equals("")) break;
            String[] inputs = input.split(" ");
            x = isContained(inputs[0]);
            y = isContained(inputs[1]);
            int lenght = Integer.parseInt(inputs[2]);
            current = new Problem1Node(y, lenght);
            edges[x].add(current);
            current = new Problem1Node(x, lenght);
            edges[y].add(current);
        }
    }

	private void dijkstra(int start) {
        for (int i = 0; i < MAX_NUMBER_OF_NODES; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        Problem1Node current;
        for (int i = 0; i < edges[start].size(); i++) {
            distance[edges[start].get(i).getNum()] = edges[start].get(i).getCos();
            parents[edges[start].get(i).getNum()] = start;
            current = new Problem1Node(edges[start].get(i).getNum(), edges[start].get(i).getCos());
            priorityQueue.add(current);
        }
        distance[start] = 0;
        parents[start] = 0;
        current = new Problem1Node(start, 0);
        priorityQueue.add(current);
        while (priorityQueue.size() > 0) {
            current = priorityQueue.poll();
            if (current.getCos() <= distance[current.getNum()]) {
                for (int i = 0; i < edges[current.getNum()].size(); i++) {
                	int currentEdge = edges[current.getNum()].get(i).getNum();
                	int currentLenghtBetweenEdges = edges[current.getNum()].get(i).getCos();
                    if (distance[current.getNum()] + currentLenghtBetweenEdges < distance[currentEdge]) {
                        distance[currentEdge] = distance[current.getNum()] + currentLenghtBetweenEdges;
                        parents[currentEdge] = current.getNum();
                        Problem1Node newNodeInQueue = new Problem1Node(currentEdge, distance[currentEdge]);
                        priorityQueue.add(newNodeInQueue);
                    }
                }
            }
        }
    }

	private void printPath(int vertex) {
        if (parents[vertex] != 0) {
            printPath(parents[vertex]);
        }
        System.out.print(nodesIntToString[vertex] + " ");
    }
	
	public void findPaths() {
        int start, end;
        while (true) {
        	String input;
        	try {
        		input = inputLine.nextLine();
        	} catch (NoSuchElementException e) {
        		break;
        	}
        	if (input.equals("")) break;
            String[] inputs = input.split(" ");
            start = isContained(inputs[0]);
            end = isContained(inputs[1]);
            dijkstra(start);
            if (distance[end] == Integer.MAX_VALUE) {
            	System.out.println("No path");
            } else {
            	System.out.print(distance[end] + " ");
                printPath(end);
                System.out.println();
            }
        }
        inputLine.close();
    }
	
}
