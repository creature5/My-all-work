package treesAndTreeLikeStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Problem3CalculateArithmeticExpression {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please write arithmetic expression : ");
		String inputString = input.nextLine();
		System.out.println(inputString);
		
		try {
			Queue<String> outputQueue = convertToRPN(inputString);
			Queue<String> queue = new LinkedList<String>(outputQueue);
			
			while (!queue.isEmpty()) {
				System.out.println(queue.poll());
			}
			
			System.out.println(calculateExpression(outputQueue));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		input.close();
	}
	
	private static double calculateExpression(Queue<String> queue) throws Exception {
		
        Stack<String> stack = new Stack<String>();
        String token;
        
        // While there are input tokens left 
        while (queue.size() > 0) {
            // Read the next token from input.
            token = queue.poll();
            // If the token is a value, Push it onto the stack.
            
            if (token.matches(numRegex) || token.matches(floatingNumRegex)) {
                stack.push(token);
            } else {
            	// Otherwise, the token is an operator or function
                String operatorResult = "";

                // Else, Pop the top n values from the stack.
                switch (token.trim()) {
                    case "/":
                        operatorResult = executeDivision(stack);
                        break;
                    case "*":
                        operatorResult = executeMultiplication(stack);
                        break;
                    case "+":
                        operatorResult = executeAddition(stack);
                        break;
                    case "-":
                        operatorResult = executeSubtraction(stack);
                        break;
                    case "pow":
                        operatorResult = executePow(stack);
                        break;
                    case "sqrt":
                        operatorResult = executeSqrt(stack);
                        break;
                    case "ln":
                        operatorResult = executeLn(stack);
                        break;
                    default:
                        throw new Exception("The inserted operator or function is not allowed to be used");
                }
                
                stack.push(operatorResult);
            }
        }
        
        if (stack.size() > 1) {
            throw new Exception(" The user input has too many values");
        }
        
        return Double.parseDouble(stack.pop());
    }

    static String numRegex = "-?[0-9]+";
    static String floatingNumRegex = "-?[0-9]+\\.[0-9]+";
    
    private static Queue<String> convertToRPN(String input) throws Exception {
    	
    	 List<String> operators = new ArrayList<String>();
    	 operators.add("*");
    	 operators.add("/");
    	 operators.add("+");
    	 operators.add("-");
    	 
    	 List<String> functions = new ArrayList<String>();
    	 functions.add("ln");
    	 functions.add("sqrt");
    	 functions.add("pow");
    	 
    	 List<String> brackets = new ArrayList<String>();
    	 brackets.add("(");
    	 brackets.add(")");
    	 
    	 List<String> separator = new ArrayList<String>();
    	 separator.add(",");
    	 
         Queue<String> queue = new LinkedList<String>();
         Stack<String> stack = new Stack<String>();
         String concatenation = "";
         
         //while there are tokens to be read
         for (int pos = 0; pos < input.length(); pos++) {
        	 
        	 //read token
        	 String token = concatenation + input.charAt(pos);
        	 
        	// token is number
             /* Unary minus handled - a minus sign is always unary if it 
        	 immediately follows another operator or a left parenthesis, 
        	 or if it occurs at the very beginning of the input. */
        	 if (token.matches(numRegex) || 
        			 (token.trim().compareTo("-") == 0 && pos == 0) ||
        			 (token.trim().compareTo("-") == 0 && (input.charAt(pos-1) == '(' ||
        			 input.charAt(pos - 1) == ',')) || 
        			 (token.trim().compareTo("-") == 0 && operators.indexOf(input.charAt(pos - 1)) != -1)){
        		 //check if the next token is number or decimal point
        		 int counter = 1;
        		 
        		 while ((pos + counter) < input.length() && 
        				 (input.charAt(pos+counter) == '.' ||
        				 Character.isDigit(input.charAt(pos + counter)))) {
        			 token = (token + input.charAt(pos + counter)).trim();
        			 counter++;
        		 }
        		 
        		 pos += counter - 1;
        		 queue.add(token.trim());
        		 concatenation = "";
        		 continue;
        	 }
        	 
        	 //token is a separator
        	 if (separator.contains(token.trim())) {
        		 while(stack.peek() != "(") {
        			 /*Until the token at the top of the stack is a left parenthesis,
                      * pop operators off the stack onto the output queue.*/
        			 queue.add(stack.pop());
        		 }
        		 
        		 /*If the stack runs out without finding a left parenthesis,
                  * then there are mismatched parentheses.*/
        		 if (!stack.contains("(")) {
        			 throw new Exception();
        		 }
        		 concatenation = "";
    			 continue;
        	 }
        	 
        	 //token is bracket
        	 if (brackets.contains(token.trim())) {
                 //token is left parenthesis => push onto the stack
                 if (token.trim().compareTo("(") == 0) {
                	 stack.push(token.trim());
                 }
                 //token is right parenthesis
                 if (token.trim().compareTo(")") == 0)
                 {
                     /*If the stack runs out without finding a left parenthesis,
                      *then there are mismatched parentheses.*/
                     if (!stack.contains("(")) {
                    	 throw new Exception();
                     }
                     /*Until the token at the top of the stack is a left parenthesis,*/
                     while (stack.peek().compareTo("(") != 0)
                     {
                         /* pop operators off the stack onto the output queue.*/
                         queue.add(stack.pop());
                     }
                     /*Pop the left parenthesis from the stack, but not onto the output queue.*/
                     if (stack.peek().compareTo("(") == 0) {
                    	 stack.pop();
                     }
                     /*If the token at the top of the stack is a function token, 
                      * pop it onto the output queue.*/
                     if (stack.size() > 0 && functions.contains(stack.peek())) {
                    	 queue.add(stack.pop());
                     }
                 }
                 concatenation = "";
                 continue;
             }
        	 
        	// token is function
             if (functions.contains(token.trim())) {
                 stack.push(token.trim());
                 concatenation = "";
                 continue;
             }

             // token is operator
             //If the token is an operator, o1, then:
             if (operators.contains(token.trim())) {
                 /* while there is an operator token, o2, at the top of the stack, and
                    either o1 is left-associative and its precedence is equal to that of o2,
                    or o1 has precedence less than that of o2,*/
                 while (stack.size() > 0 && operators.contains(stack.peek()) && 
                		 Precedence(token.trim()) >= Precedence(stack.peek())) {
                     //pop o2 off the stack, onto the output queue;
                     queue.add(stack.pop());
                 }
                 //push o1 onto the stack.
                 stack.push(token.trim());
                 concatenation = "";
                 continue;
             }
             concatenation = token.trim();	 
         }
         
         //While there are still operator tokens in the stack:
         while (stack.size() > 0) {
             //If the operator token on the top of the stack is a parenthesis,
        	 //then there are mismatched parentheses.
             if (brackets.contains(stack.peek())) {
                 throw new Exception("There are mismatched parentheses");
             }
             //Pop the operator onto the output queue.
             queue.add(stack.pop());
         }
         
         return queue;
    }
    	
	private static int Precedence(String token) {
		
		int precedence;
        switch (token) {
            case "+":
            case "-":
                precedence = 2;
                break;
            case "*":
            case "/":
                precedence = 1;
                break;
            default:
                precedence = 0;
                break;
        }

        return precedence;
	}
	
	 private static String executeSubtraction(Stack<String> myStack) throws Exception {
         if (myStack.size() < 2) {
             throw new Exception("There are not enough elements in the stack to perform subtraction.");
         }

         String operatorResult = String.valueOf(-Double.parseDouble(myStack.pop()) + Double.parseDouble(myStack.pop()));
         return operatorResult;
     }

     private static String executeAddition(Stack<String> myStack) throws Exception {
         if (myStack.size() < 2) {
             throw new Exception("There are not enough elements in the stack to perform addition.");
         }

         String operatorResult = String.valueOf(Double.parseDouble(myStack.pop()) + Double.parseDouble(myStack.pop()));
         return operatorResult;
     }

     private static String executeMultiplication(Stack<String> myStack) throws Exception {
         if (myStack.size() < 2) {
             throw new Exception("There are not enough elements in the stack to perform multiplication.");
         }

         String operatorResult = String.valueOf(Double.parseDouble(myStack.pop()) * Double.parseDouble(myStack.pop()));
         return operatorResult;
     }

     private static String executeDivision(Stack<String> myStack) throws Exception {
         if (myStack.size() < 2) {
             throw new Exception("There are not enough elements in the stack to perform division.");
         }

         String operatorResult = String.valueOf(1 / Double.parseDouble(myStack.pop()) * Double.parseDouble(myStack.pop()));
         return operatorResult;
     }

     private static String executeLn(Stack<String> myStack) throws Exception {
         if (myStack.size() < 1) {
             throw new Exception("There are not enough elements in the stack to perform ln calculation.");
         }

         String operatorResult = String.valueOf(Math.log(Double.parseDouble(myStack.pop())));
         return operatorResult;
     }

     private static String executePow(Stack<String> myStack) throws Exception {
         if (myStack.size() < 2) {
             throw new Exception("There are not enough elements in the stack to perform Power calculation.");
         }

         String exp = myStack.pop();
         String myBase = myStack.pop();
         String result = String.valueOf(Math.pow(Double.parseDouble(myBase), Double.parseDouble(exp)));

         return result;
     }

     private static String executeSqrt(Stack<String> myStack) throws Exception {
         if (myStack.size() < 1) {
             throw new Exception("There are not enough elements in the stack to perform SQRT calculation.");
         }

         String result = String.valueOf(Math.sqrt(Double.parseDouble(myStack.pop())));
         return result;
     }
}
