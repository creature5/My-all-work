package all;

public class Problem7Edge implements Comparable<Problem7Edge>{
    public char edgeNodeOne;
    public char edgeNodeTwo;
    public Integer cost = 0;
    
    public char getEdgeNodeOne() {
		return edgeNodeOne;
	}
	public void setEdgeNodeOne(char edgeNodeOne) {
		this.edgeNodeOne = edgeNodeOne;
	}
	public char getEdgeNodeTwo() {
		return edgeNodeTwo;
	}
	public void setEdgeNodeTwo(char edgeNodeTwo) {
		this.edgeNodeTwo = edgeNodeTwo;
	}
	

    public Problem7Edge(char nodeOne, char nodeTwo) {
        this.edgeNodeOne = nodeOne;
        this.edgeNodeTwo = nodeTwo;
    }
    public int compareTo(Problem7Edge otherEdge) {
        return this.cost.compareTo(otherEdge.cost);
    }
}
