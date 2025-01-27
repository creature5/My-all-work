package all;

import java.util.*;

public class Problem2ConvexHull {
	
	static Problem2Point mostLeftDownPoint;
	
	private int indexOfMostDownLeftPoint = 0;
	private List<Problem2Point> hull = new ArrayList<Problem2Point>();
	private List<Problem2Point> points = new ArrayList<Problem2Point>();
	
	private static int numberPoints;
	
	public void decrementNumberPointsByOne() {
		numberPoints--;
	}
	
	public List<Problem2Point> getPoints() {
		return points;
	}

	public int getIndexOfMostDownLeftPoint() {
		return indexOfMostDownLeftPoint;
	}

	private boolean orientation(Problem2Point p1, Problem2Point p2, Problem2Point p3) {
		int orientation = p1.getX() * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) 
				+ p3.getX() * (p1.getY() - p2.getY());
		if (orientation < 0)
			return false;
		return true;
	}

	public void readInput() {
		Scanner input = new Scanner(System.in);
        numberPoints = Integer.parseInt(input.nextLine());
        
        mostLeftDownPoint = new Problem2Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        
        for (int i = 0; i < numberPoints; i++) {
            String inputLine = input.nextLine();
            String[] numbers = inputLine.split(" ");
            Problem2Point current = new Problem2Point();
            current.setX(Integer.parseInt(numbers[0]));
            current.setY(Integer.parseInt(numbers[1]));
            if (current.getX() < mostLeftDownPoint.getX()) {
                mostLeftDownPoint.setX(current.getX());
                mostLeftDownPoint.setY(current.getY());
                indexOfMostDownLeftPoint = i;
            } else if (current.getX() == mostLeftDownPoint.getX()) {
                    if (current.getY() < mostLeftDownPoint.getY()) {
                        mostLeftDownPoint.setY(current.getY());
                        indexOfMostDownLeftPoint = i;
                    }
            }
            points.add(current);
        }
        input.close();
    }

	public void findConvexHull() {
		hull.add(mostLeftDownPoint);
		hull.add(points.get(0));
		int j = 1;
		for (int i = 1; i < numberPoints; i++) {
			if (hull.size() == 1) {
				hull.add(points.get(i));
				j++;
				i++;
			}
			if (orientation(hull.get(j - 1), hull.get(j), points.get(i)) == true) {
				hull.add(points.get(i));
				j++;
			} else {
				hull.remove(j);
				i--;
				j--;
			}
		}
	}

	public void printConvexHull() {
//		System.out.println(hull.size() + 1);
		int count = hull.size() - 1;
		System.out.printf("(%d, %d) - ", hull.get(0).getX(), hull.get(0).getY());
		for (int i = count; i > 0; i--) {
			System.out.printf("(%d, %d) - ", hull.get(i).getX(), hull.get(i).getY());
		}
		System.out.printf("(%d, %d)", hull.get(0).getX(), hull.get(0).getY());
	}

}
