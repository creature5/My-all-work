package all;

public class Problem4Shape {
	private double width;
	private double height;
	
	public Problem4Shape() {
		this(0,0);
	}
	
	public Problem4Shape(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}

	public double calculateSurface() {
		double defaultSurface = 0.0;
		return defaultSurface;
	}
}
