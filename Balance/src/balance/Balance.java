// Owen O'Connor
// CSC 201
// Assignment 11
// Heavy use of textbook examples

package balance;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Tests expression for balance of parentheses and brackets
 * @author owenoconnor
 * @since 10/15/21
 *
 */
public class Balance {
	
	public static final Pattern UNSIGNED_DOUBLE = Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
	public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
	
	public static boolean isBalanced(String expression) {
		final char LEFT_NORMAL = '(';
		final char RIGHT_NORMAL = ')';
		final char LEFT_CURLY = '{';
		final char RIGHT_CURLY = '}';
		final char LEFT_SQUARE = '[';
		final char RIGHT_SQUARE = ']';
	
		
		Stack<Character> store = new Stack<Character>();
		int i;
		boolean failed = false;
		
		for (i = 0; !failed && (i < expression.length()); i++) {
			switch (expression.charAt(i)) {
			case LEFT_NORMAL:
			case LEFT_CURLY:
			case LEFT_SQUARE:
				store.push(expression.charAt(i));
				break;
			case RIGHT_NORMAL:
				if (store.isEmpty() || (store.pop() != LEFT_NORMAL))
					failed = true;
				break;
			case RIGHT_CURLY:
				if (store.isEmpty() || (store.pop() != LEFT_CURLY))
					failed = true;
				break;
			case RIGHT_SQUARE:
				if (store.isEmpty() || (store.pop() != LEFT_SQUARE))
					failed = true;
				break;
			
			
			} 
			
		}
		
		
		
		return (store.isEmpty() && !failed);
	} // End isBalanced
	
	/**
	 * Sets up the evaluation based on the parentheses and brackets
	 * @param expression
	 * @return
	 */
	public static double evaluate(String expression) {
		Stack<Double> numbers = new Stack<Double>();
		Stack<Character> operations = new Stack<Character>();
		
		Scanner in = new Scanner(expression);
		String next;
		
		while (in.hasNext()) {
			if (in.hasNext(UNSIGNED_DOUBLE)) {
				next = in.findInLine(UNSIGNED_DOUBLE);
				Double n = Double.valueOf(next);
				numbers.push(n);
			}
			else {
				next = in.findInLine(CHARACTER);
				switch (next.charAt(0)) {
				case '+':
				case '-':
				case '*':
				case '/':
					operations.push(next.charAt(0));
					break;
				case ')':
					evaluateStackTops(numbers, operations);
					break;
				case '(':
					break;
				default: 
					throw new IllegalArgumentException("Illegal character");
				
				}
				
			}
		} // End while loop
		
	//	if (numbers.size() != 1)
		//	throw new IllegalArgumentException("Illegal input expression");
		return numbers.pop();
	} //  End evaluate()
	
/**
 * Executes the evaluation of each small chunk of expression
 * @param numbers
 * @param operations
 */
	public static void evaluateStackTops (Stack<Double> numbers, Stack<Character> operations) {
		double operand1, operand2;
		if ((numbers.size() < 2) || (operations.isEmpty())) 
			throw new IllegalArgumentException("Illegal expression");
		operand2 = numbers.pop();
		operand1 = numbers.pop();
		
		switch (operations.pop()) {
		case '+': numbers.push(operand1 + operand2);
					break;
		case '-':numbers.push(operand1 - operand2);
					break;
		case '*':numbers.push(operand1 * operand2);
					break;
		case '/':numbers.push(operand1 / operand2);
					break;
		default: throw new IllegalArgumentException("Illegal operation");
		}
		
	} // End evaluateStackTops()
	
}
