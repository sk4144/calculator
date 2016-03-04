
/**This class represents the tools needed to make the calculations in
 * the calculator main class.
 *
 * @author Suebin Kim
 * @version 11/18/15
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionTools <E> {
	//arraylist of valid operators
	private static ArrayList<String> o = new ArrayList<String>(6);
	private static MyStack<String> operators;
	private static StringBuffer postfix;

	/**
	 * Default constructor is private because
	 * no instance of ExpressionTools should be made
	 */
	private ExpressionTools(){

	}

	/**
	 * WRAPPER METHOD: 
	 * Converts the given string expression to a postfix expression
	 * @param ex
	 * 	the infix expression from a line in the input file
	 * @return String
	 * 	The postfix expression
	 * @throws PostFixException
	 */
	public static String infixToPostfix(String ex) throws PostFixException {

		//checks if number of right and left braces
		//match in the string and if the string contains
		//only operator and operand tokens
		int leftBrace = 0;
		int rightBrace = 0;
		char [] c = ex.toCharArray();

		for (char ch : c) {
			if (ch == ')') {
				rightBrace++;
			}
			else if (ch == '(') {
				leftBrace++;
			}
			else if(Character.isLetter(ch)) {
				throw new PostFixException("Expression contains invalid letter token");
			}
			else if (ch != ' ' && !oContains(Character.toString(ch)) && !(Character.isDigit(ch))) {
				throw new PostFixException("Expression contains invalid token");
			}
		}
		if(leftBrace > rightBrace) {
			throw new PostFixException("Missing right brace");

		}
		else if (leftBrace < rightBrace) {
			throw new PostFixException("Missing left brace");
		}

		Scanner scanner = new Scanner(ex);
		operators = new MyStack<String>();
		postfix = new StringBuffer();

		//call to the private helper method
		return infixToPostfix(ex, operators,scanner, postfix);
	}

	/**
	 * Converts the given string expression to a postfix expression
	 * @param ex
	 * 	the infix expression from a line in the input file
	 * @param operators
	 * 	the stack for the operators
	 * @param scanner
	 * 	the scanner to read each token from the string
	 * @param postfix
	 * 	the string buffer to create the postfix string expression
	 * @return String
	 * 	The postfix expression
	 * @throws PostfixException
	 */
	private static String infixToPostfix (String ex, MyStack<String> operators, Scanner scanner, StringBuffer postfix ) throws PostFixException{
		//checks to see if the given string is empty
		if (!(scanner.hasNext())) {
			throw new PostFixException("Blank line - invalid entry");
		}

		//add next token to postfix expression if it is an operand
		while(scanner.hasNext()) {
			String s = scanner.next();
			s= s.trim();
			//checks if the token is an operator
			if (!oContains(s)) { 
				postfix.append(s + " ");

			}
			//push the token onto the operators stack if it is not an operand
			else if(s.equals("(")) {
				operators.push(s);

			}
			else if(s.equals("*") || s.equals("/")|| s.equals("+") || s.equals("-")) {

				if(!operators.empty()) {
					String tmp1 = operators.peek();
					//pop the operator stack and append to the postfix string as long as the
					//top element on the stack has higher rank than the current token
					while(!(operators.empty()) && rank(tmp1) >= rank(s)) {
						postfix.append(operators.pop() + " ");
					}
				}
				operators.push(s);

			}
			//pop the operator stack and append to postfix expression
			//until a matching left brace is found
			else if(s.equals(")")) {
				while(!operators.empty()) {
					String tmp2 = operators.peek();

					if(!(tmp2.equals("("))) {
						postfix.append(operators.pop() + " ");
					}
					//once the left brace is found, pop and discard it
					else {
						operators.pop();
						break;
					}
				}

			}
		}
		//pop the operator stack and append to the postfix expression
		//until the operator stack is empty
		while(!operators.empty()) {
			postfix = postfix.append(operators.pop() + " ");
		}
		scanner.close();
		return postfix.toString();


	}

	/**
	 * Gives a rank from 0-3 to an operator
	 * @param s
	 * 	The operator to be ranked
	 * @return int
	 * 	the rank of the operator with 3 being the highest precedence
	 * 	
	 */
	private static int rank(String s) {
		if(s.equals("*")|| s.equals("/")) {
			return 3;
		}
		else if (s.equals("-") || s.equals("+")) {
			return 2;
		}
		else { //if the operator is a parentheses
			return 0;
		}

	}

	/**
	 * Adds the operators to an operator arraylist and determines
	 * if the given string is included in the arraylist
	 * @param s
	 * 	The string to check for in the operator arraylist
	 * @return boolean
	 * 	True if the string is an operator
	 * 	False if the string is a operand
	 */
	private static boolean oContains(String s) {
		//adding operator elements to the operator arraylist
		o.add("(");
		o.add(")");
		o.add("+");
		o.add("-");
		o.add("*");
		o.add("/");
		return o.contains(s);
	}

	/**
	 * Converts the given string expression to a postfix expression
	 * @param ex
	 * 	the infix expression from a line in the input file
	 * @return String
	 * 	The postfix expression
	 * @throws PostFixException
	 * @author Joanna Kuklowska / Suebin Kim
	 */
	public static int postfixEval(String expression) throws PostFixException {
		MyStack<Integer> stack = new MyStack<Integer>();

		int value;
		String operator;

		int operand1;
		int operand2;

		int result = 0;

		Scanner tokenizer = new Scanner(expression);


		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextInt()) {
				// Process operand.
				value = tokenizer.nextInt();

				stack.push(value);
			} 
			else {
				// Process operator.
				operator = tokenizer.next();

				// Obtain second operand from stack.
				if (stack.empty()) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}
				operand2 = stack.peek();
				stack.pop();

				// Obtain first operand from stack.
				if (stack.empty()) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}
				operand1 = stack.peek();
				stack.pop();

				// Perform operation.
				if (operator.equals("/")) {
					//checks if trying to divide by zero
					if (operand2 == 0) {
						tokenizer.close();
						throw new ArithmeticException("Cannot divide by zero");
					}
					result = operand1 / operand2;
				}
				else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				else {
					tokenizer.close();
					throw new PostFixException("Illegal symbol: " + operator);
				}

				// Push result of operation onto stack.
				stack.push(result);
			}
		}

		tokenizer.close();

		// Obtain final result from stack.
		if (stack.empty())
			throw new PostFixException("Not enough operands - stack underflow");
		result = stack.peek();
		stack.pop();

		// Stack should now be empty.
		if (!stack.empty())
			throw new PostFixException("Too many operands - operands left over");

		// Return the final.
		return result;
	}
}
