package all;

public class Problem4ParsedExpression {
	private Double[] numbers;
	private Character[] operators;
	
	public Problem4ParsedExpression(Double[] numbers, Character[] operators) {
		super();
		this.numbers = numbers;
		this.operators = operators;
	}
	
	public Double[] getNumbers() {
		return numbers;
	}
	
	public void setNumbers(Double[] numbers) {
		this.numbers = numbers;
	}
	
	public Character[] getOperators() {
		return operators;
	}
	
	public void setOperators(Character[] operators) {
		this.operators = operators;
	}
}
