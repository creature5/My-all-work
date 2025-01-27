package all;


public class Problem1Student extends Problem1Human implements Comparable<Problem1Student>{
	
	private double mark;
	
	public Problem1Student(String name, String surname, double mark) {
		super(name, surname);
		this.mark = mark;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Problem1Student [mark=" + mark + ", getName()=" + getName()
				+ ", getSurname()=" + getSurname() + "]";
	}

	
	@Override
	public int compareTo(Problem1Student other) {
		if (this.mark > other.mark)	{
			return 1;
		} else if (this.mark < other.mark){
			return -1;
		}
		return 0;
	}

}
