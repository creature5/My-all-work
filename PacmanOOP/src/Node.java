
public class Node {
	int y;
	int x;
	int l;
	Node parent;

	public Node(int y, int x, int l, Node parent) {
		this.y = y;
		this.x = x;
		this.l = l;
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "" + y + " " + x + " " + l;
	}
}
