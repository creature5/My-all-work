package all;

import java.util.*;

public class Problem3WordLabirinth {
	private char[][] lab;
    private boolean[][] visited;
    private List<Character> path;
    private HashMap<String, String> paths; 

    
    public Problem3WordLabirinth(int n) {
		super();
		this.lab = new char[n][n];
		this.visited = new boolean[n][n];
		this.path = new ArrayList<Character>();;
		this.paths = new LinkedHashMap<String, String>();
	}

    public HashMap<String, String> getPaths() {
		return paths;
	}

	public void setMatrixElement(int row, int col, char element) {
		this.lab[row][col] = element;
	}
    
	private boolean inRange(int row, int col) {
        boolean rowInRange = row >= 0 && row < lab.length;
        boolean colInRange = col >= 0 && col < lab.length;
        return rowInRange && colInRange;
    }

    public void findPathToExit(int row, int col) {
        if (!inRange(row, col)) {
            savePath();
            return;
        }
        path.add(lab[row][col]);

        if (Character.isLetter(lab[row][col]) == true && visited[row][col] == false) {
            visited[row][col] = true;

            findPathToExit(row, col - 1);
            findPathToExit(row - 1, col);
            findPathToExit(row, col + 1);
            findPathToExit(row + 1, col);

            visited[row][col] = false;
        }
        path.remove(path.size() - 1);
    }

    private void savePath() {
        StringBuilder currentPath = new StringBuilder();
        for (int i = 1; i < path.size(); i++) {
            currentPath.append(path.get(i));
        }
        if (paths.containsKey(currentPath.toString()) == false) {
            paths.put(currentPath.toString(), currentPath.toString());
        }           
    }

}
