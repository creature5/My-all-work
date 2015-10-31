package all;

public class Lion extends Felidae{
	private int weight;
	
	public Lion(boolean male, int weight) {
		super(male); // visible - Felidae's public constructor.
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
