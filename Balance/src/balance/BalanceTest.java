// Owen O'Connor
// CSC 201
// Assignment 11


package balance;

import java.util.Scanner;

public class BalanceTest {

	/**
	 * Simple calculator program using stacks
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the calculator.");
		System.out.println("You can use integers, as well as (,),+,-,*, or / ");
		System.out.println("Entire expression must be contained in parentheses ( ). ");
		System.out.println("Enter a mathmatical expression to evaluate:");
		Scanner input = new Scanner(System.in);
		String expression = input.next();
		
		if (Balance.isBalanced(expression)) {
			
			System.out.println("Balanced!");
			System.out.print("Expression evaluates to: ");
			System.out.println(Balance.evaluate(expression));			
		}
		
		else {
			
			System.out.println("Not Balanced!");
		}
		
	
		
		
		
		
		
		
	}
	
	
	
	
}
