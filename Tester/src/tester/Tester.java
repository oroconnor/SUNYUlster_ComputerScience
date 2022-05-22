//Exam 1 - programming portion - Owen O'Connor

package tester;

import java.util.Scanner;

public class Tester {
/**
 * 	This program asks the user to enter an integer one at time, until the user enters sentinel value -1 to end the program.
 * 	Each integer is compared to the sum of all previous integers, and a total is given at the end of the program.
 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int total = 0; //keeps track of a the sum of each integer inputted
		boolean numsentered = false; //flag to indicate that at least one integer was entered by user
		
		System.out.println("This program will keep track of the Sum of the integers that you enter.");
		System.out.print("Enter an integer or -1 to quit: ");
		int number = input.nextInt();  //input an integer from the user
	
		while (number != -1) {            
			numsentered = true;
			//compare inputted integer to the total
			if (number < total)
				System.out.printf("%d is less than Sum %d%n", number,total);
			else if (number == total)
				System.out.printf("%d is equal to Sum %d%n", number,total);
			else
				System.out.printf("%d is greater than Sum %d%n", number,total);
			total = total + number; //update the total         
			System.out.print("Enter an integer or -1 to quit: ");
			number = input.nextInt();
		}
		//verify that at least one number was entered before presenting the sum
		if (numsentered)
			System.out.printf("The final sum is %d", total);
		else
			System.out.print("No integers were entered");
	}
}
