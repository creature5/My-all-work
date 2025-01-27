package all;

public class Problem2Point implements Comparable<Problem2Point>{

	private int x;
	private int y;
	
	
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Problem2Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Problem2Point() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public int compareTo(Problem2Point other) {
		Problem2Point mostLeftDownPoint = Problem2ConvexHull.mostLeftDownPoint;
		int orientedArea = mostLeftDownPoint.getX() * (this.y - other.y) 
				+ this.x * (other.y - mostLeftDownPoint.getY()) 
						+ other.x * (mostLeftDownPoint.getY() - this.y);
        return -orientedArea;
	}

}
