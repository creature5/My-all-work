package all;

import java.util.*;

//calculate expression with floating point numbers
public class Problem4SimpleExpressionEvaluator {
	
	private static Double[] extractNumbers(String expression) {
		// add signs for multiplication and division
		String[] splitResult = expression.split("[\\Q+-*/\\E]+");
		List<Double> numbers = new ArrayList<Double>();
		for (String number : splitResult) {
			numbers.add(Double.parseDouble(number));
		}
		return numbers.toArray(new Double[numbers.size()]);
	}
	
	private static Character[] extractOperators(String expression) {
		// add signs for multiplication and division
		String operationsCharacters = "+-*/";
		List<Character> operators = new ArrayList<Character>();
		for (int i = 0; i < expression.length(); i++) {
			String c = expression.charAt(i) + "";
			if (operationsCharacters.contains(c)) {
				operators.add(expression.charAt(i));
			}
		}
		return operators.toArray(new Character[operators.size()]);
	}
	
	private static Problem4ParsedExpression calculateMultiplicationAndDivisionFirst(
			Problem4ParsedExpression parsedExp) {
		// we will be getting the numbers and operators in sequence, so to make
		// our life easier, we will
		// use queues
		Queue<Double> numbers = new LinkedList<Double>();
		numbers.addAll(Arrays.asList(parsedExp.getNumbers()));
		Queue<Character> operators = new LinkedList<Character>();
		operators.addAll(Arrays.asList(parsedExp.getOperators()));

		// we will be adding and removing the last element, so the proper data
		// structure is a stack
		Stack<Double> newNumbers = new Stack<Double>();
		Stack<Character> newOperators = new Stack<Character>();

		// push the first number before we start
		newNumbers.push(numbers.poll());

		while (numbers.size() > 0) {
			double nextNumber = numbers.poll();
			char nextOperator = operators.poll();

			if (nextOperator == '*') {
				// if the operator is multiplication, get the previous number
				// and multiply it with the next
				// push the result to the stack. we do not need to do anything
				// with the operators
				double previousNumber = newNumbers.pop();
				newNumbers.push(previousNumber * nextNumber);
			} else if (nextOperator == '/') {
				if (nextNumber == 0) throw new ArithmeticException();
				// if the operator is division, get the previous number and
				// divide it by the next
				// push the result to the stack. we do not need to do anything
				// with the operators
				// exception will be thrown if dividing by 0
				double previousNumber = newNumbers.pop();
				newNumbers.push(previousNumber / nextNumber);
			} else {
				// if the result is not multiplication or division, do nothing
				// and push the number and the operator
				// to their stacks
				newNumbers.push(nextNumber);
				newOperators.push(nextOperator);
			}
		}

		// copy the resulting arrays into the returned object
		parsedExp.setNumbers(newNumbers.toArray(new Double[newNumbers.size()]));
		parsedExp.setOperators(newOperators.toArray(new Character[newOperators.size()]));

		return parsedExp;
	}
	
	private static double calculateExpressionWithPlusesAndMinusesOnly(
			Problem4ParsedExpression parsedExp) {
		Double[] numbers = parsedExp.getNumbers();
		Character[] operators = parsedExp.getOperators();

		double result = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			char operation = operators[i - 1];
			double nextNumber = numbers[i];
			if (operation == '+') {
				result += nextNumber;
			} else if (operation == '-') {
				result -= nextNumber;
			}
		}
		return result;
	}
	
	private static String readExpression() {
		System.out.println("Enter expression:");
		Scanner input = new Scanner(System.in);
		String expression = input.nextLine();
		input.close();
		return expression;
	}
	
	public static void main(String[] args) {
		try {
            String expression = readExpression();

            Double[] numbers = extractNumbers(expression);
            Character[] operators = extractOperators(expression);

            //use a different class to easily pass and return the expression to and from functions
            Problem4ParsedExpression parsedExp = new Problem4ParsedExpression(numbers, operators);

            //calculate all the multiplications and divisions. The function will return ParsedExpression only with
            //pluses and minuses. Will throw exception if there is division by 0.
            parsedExp = calculateMultiplicationAndDivisionFirst(parsedExp);

            double result = calculateExpressionWithPlusesAndMinusesOnly(parsedExp);
            System.out.printf(expression + " = %.2f",result);
        } catch (ArithmeticException ex) {
            System.out.println("Division by zero.");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid expression!");
        }
	}
}
