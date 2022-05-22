/* Owen O'Connor
 * CSC 201
 * Assignment 3
 *  */

package testdebug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TestDebug {

 public static void main(String[] args) {
 
	 int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4, 20, 60, 2, 1, 3, -7}; // Initialize unsorted array of integers
	 Arrays.sort(array); // Sort the specified array into ascending numerical order
	 printArray("Sorted array", array); // Call printArray method in this class to print array length and array. 
	 
	 Scanner input = new Scanner(System.in); 
	 System.out.println("Enter an integer to find: ");
	 try { // Should handle expected exceptions. "Bug" 6
		 int number = input.nextInt();
		
		 
		 int index = Arrays.binarySearch(array, number); //binarySearch returns (-(insertion point) - 1) if key is not found. Not desired behavior for this program. Bug 5
		 if (index < 0) {
			 System.out.println("Sorry, integer not found.");
		 } else {
		 System.out.println("Found " + number + " @ " + (index + 1)); // Missing space. "Bug" 3. I'm guessing that because 
		 //they had index++, that they going for position (1-16) rather than index value (0-15). I changed index++ to (index + 1)
		 //reflect that. Bug 4.
		 }
		 
	 } // End try block
	 catch (InputMismatchException inputMismatchException) {
		 System.out.printf("Sorry. You didn't enter a valid integer. Program terminating.");
	 } 
 }  // End of Main.
 
 private static void printArray(String message, int array[]) {
 
	 System.out.println(message + ": [length: " + array.length + "]");
	 for (int i = 0; i < array.length; i++) { // i < 13 doesn't print whole array. Bug 7
	 //if(i != 0){  
	 //} Removing if statement with no body and no purpose. Bug 2. 
	 System.out.print(array[i] + " "); 
	 }
	 System.out.println();
 } // End of printArray method
 
} // End of class. Bug 1 missing bracket

/* Bulleted bug list:
 * 1 - missing end of class bracket
 * 2 - if statement with no body
 * 3 - missing space in print statement
 * 4 - index++ being used in print statement for key position
 * 5 - binarySearch item not found condition not handled
 * 6 - no handling of expected inputMismatchException
 * 7 - printArray counter didn't print whole array
 * */
