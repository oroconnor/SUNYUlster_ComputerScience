package dividebyzero;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideByZero {

	public static int quotient(int numerator, int denominator)
	{

		return numerator / denominator;
		
	}
	
	public static void main(String[] args) {
		boolean continueLoop = true;
		do {
		
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Please enter an integer numerator");
			int numerator = scanner.nextInt();
			if (numerator < 0) 
				throw new NegativeNumeratorException();
			System.out.print("Please enter an integer denominator");
			int denominator = scanner.nextInt();
			int result = quotient(numerator,denominator);
			System.out.printf("Result is %d%n", result);
			}
		catch (NegativeNumeratorException e) {
			System.out.println("Numerator must be greater than zero.");
		}
		
		catch (ArithmeticException arithmetiException) {
			System.out.printf("Zero is an invalid denominator.");
		}
		catch (InputMismatchException inputException) {
			System.out.println("You must enter an integer");
		}
		
		finally {
			System.out.println("Try Again? Y/N");
			Scanner scanner = new Scanner(System.in);
			System.out.print("Please enter an integer numerator:");
			String response = scanner.next();
			if (response.equals("Y"))
					continueLoop = true;
			else
					continueLoop = false;
			
			
			
		}
		}
		while (continueLoop);
	}
}