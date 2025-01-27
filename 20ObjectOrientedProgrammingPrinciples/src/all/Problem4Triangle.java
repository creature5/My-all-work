package all;

public class Problem4Triangle extends Problem4Shape {
	
	public Problem4Triangle(double width, double height) {
		super(width, height);
	}
	
	@Override
	public double calculateSurface() {
		double triangleSurface = (this.getWidth() * this.getHeight()) / 2;
		return triangleSurface;
	}

	@Override
	public String toString() {
		return "Problem4Triangle [calculateSurface()=" + calculateSurface()
				+ "]";
	}
	
}
