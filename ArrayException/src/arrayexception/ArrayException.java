//Owen O'Connor - CSC 180

package arrayexception;

import java.util.InputMismatchException;
import java.util.Scanner;


/** Computer Science II Programming Assignment #2
 * 
 * @author owenoconnor
 * @since 2-3-2021
 *
 * The following code example will prompt the user to enter 10 integers into an array
 * It will then ask the user to input either
 * 1) Index (0-9) Return the value stored at this index
 * 2) Value (int) Searches the array all occurrences of the value if found
 * 
 * This program includes exception handling for the following exceptions
 * 1) If the user enters an invalid index, displays an  error message
 *  
 * 2) If the user enters an invalid input (i.e. "Hello" instead of an integer)
 *    
 * 3) If the number entered to search the array is not found, it throws
 *    NumberNotFound exception
 */
public class ArrayException {
	
	/**
	 * Prompts the user for fill array and then provides search functions
	 * @param args not used
	 */
	public static void main(String[]args) {
			
		Scanner input = new Scanner(System.in);
		
		// create an array to search
		int[] array = new int[10];
		
		int response;
		int index;
		int value;
		
		// Prompt the user to input 10 integers and store them in the array
		System.out.println("Enter 10 integers");
			
		// Fill the array with user input.
		for (int i = 0; i < array.length; i++) 
			{
				System.out.printf("Enter an integer for index# %d: ", i);
				array[i] = input.nextInt();
			}
			
		// Prompt the user to choose between searching by value or searching by index.
		System.out.printf("%n1)Search by index \n2)Search by value\n");
		response = input.nextInt();
			
		//Search by index
		if (response == 1) {
			boolean continueLoop = true;
			do {
				try 
				{ 
					Scanner scanner = new Scanner(System.in);
					//user inputs index
					System.out.print("Enter an Index: ");
					index = scanner.nextInt();

					//Output the value stored at that index
					System.out.printf("Integer at index %d: %d", index, array[index]);
					
					continueLoop = false;
				}
				catch (InputMismatchException inputMismatchException) {
					//System.err.printf("%nException: %s%n", inputMismatchException);
					System.out.printf("You must enter integers.  Please try again%n%n");
				}

				catch (ArrayIndexOutOfBoundsException arrayOutOfBoundsException) {
					//System.err.printf("%nException: %s%n", arrayOutOfBoundsException);
					System.out.printf("You must choose from index 0-9.  Please try again%n%n");
				}

				
			} while (continueLoop);
			}
				
	
		if (response == 2) {
			boolean continueLoop = true;
			do {
				try 
				{ 
					Scanner scanner = new Scanner(System.in);
					//user inputs value
					System.out.printf("Enter a value: ");
					value = scanner.nextInt();

					//for loop searches for value in the array
					//notfound is turned to true if a value is found in the array
					boolean notfound = true;
					for (int i = 0; i < array.length; i++) {
						//If the value is found, index and value will be displayed.
						if (array[i] == value) {
							System.out.printf("%nValue %d is found at index %d", value, i);
							notfound = false;
							}
					}
					//if the value is not found in the array, this exception is thrown
					if (notfound) throw new NumberNotFound();
					continueLoop = false;
				}
				catch (InputMismatchException inputMismatchException) {
					//System.err.printf("%nException: %s%n", inputMismatchException);
					System.out.printf("You must enter integers.  Please try again%n%n");
				}

				catch (NumberNotFound e) {
					//System.err.printf("%nException: %s%n", NumberNotFound);
					System.out.printf("Your number was not found.  Please try again%n%n");
				}

				
			} while (continueLoop);
			}
		
		
		
		
		
		input.close();
	}
}