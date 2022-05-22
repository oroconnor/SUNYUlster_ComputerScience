//Owen O'Connor - CSC 180 - assignment #9
package search;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.SecureRandom;


/** @author owenoconnor
 *  @since 03/31/21
 *  Java Program that allows user to enter integers and see the performance of two different
 *  search algorithms  */
public class Search {

	/** Performs the two different search algorithms and prints the results
	 * 
	 * @param nums the array of integers 1-100 created by the program
	 * @param x the user input integer to search for
	 */
	public static void search(int nums[],int x) {
		//linear search
		int lin = 0; //steps to find x with linear search
		for (int i = 0; i <nums.length; i++) {
			lin++;
			if (x == nums[i]) break;
		}
		
		System.out.printf("%n%s%d%n", "Linear Search # of Steps:", lin);
		
	
		//binary search - inspired by textbook example
		//this search method requires a sorted array in ascending order. 
		int bin = 0; //steps to find x with binary search
		int low = 0;
		int high = nums.length - 1;
		int middle = (low + high + 1) / 2;
		
		boolean contbinary = true;
		while (contbinary) {
			bin++;
			if (x == nums[middle]) {  //if x is found, it exits loop
				contbinary = false;
			}
			else if (x < nums[middle]) {
				high = middle - 1; //eliminates upper half if middle is too high
			}
			else {
				low = middle + 1; //eliminates lower half if middle is too low
			}
			middle = (low + high + 1) / 2;
		}
		
		System.out.printf("%s%d%n%n", "Binary Search # of Steps:", bin);
	}
	
	/** Performs the linear search for the simulation at end of program
	 * 
	 * @param nums the array of integers 1-100 created by the program
	 * @param r the user input integer to search for
	 */
	public static int linsim(int nums[],int r) {
		//linear search
		int lin = 0; //steps to find x with linear search
		for (int i = 0; i <nums.length; i++) {
			lin++;
			if (r == nums[i]) break;
		}
		return(lin);
	}
		
	/** Performs the binary search for the simulation at end of program
	 * 
	 * @param nums the array of integers 1-100 created by the program
	 * @param r the user input integer to search for
	 */
		public static int binsim(int nums[],int r) {
		//binary search - inspired by textbook example
		//this search method requires a sorted array in ascending order. 
		int bin = 0; //steps to find x with binary search
		int low = 0;
		int high = nums.length - 1;
		int middle = (low + high + 1) / 2;
		
		boolean contbinary = true;
		while (contbinary) {
			bin++;
			if (r == nums[middle]) {  //if x is found, it exits loop
				contbinary = false;
			}
			else if (r < nums[middle]) {
				high = middle - 1; //eliminates upper half if middle is too high
			}
			else {
				low = middle + 1; //eliminates lower half if middle is too low
			}
			middle = (low + high + 1) / 2;
		}
		
		return(bin);
	}
		
		/** Main program - runs the user search loops and the closing simulation
		 */
	public static void main(String[] args) {
		
		int [] nums = new int[1024]; //create array
		int n = 1;
		//and populate array
		for (int i = 0; i < nums.length; i++) {
			nums[i] = n;
			n++;
		}
		
		System.out.printf("%s%n%n","Welcome to Search Compare!");


		boolean inputLoop = true;
		while (inputLoop) {
			Scanner input = new Scanner(System.in);

			try { //validates to make sure user inputs integer between 1-100
			System.out.println("Enter an integer:");
			int x = input.nextInt();
			
			if (x < 1 || x > 1024) {
				throw new OutOfRangeException();
			}
			search(nums,x);
			
			}
				
			catch (OutOfRangeException e) {
				System.out.println("Your choice is out of range.");
				System.out.println("Please enter an integer between 1 and 100.");
			}
			catch (InputMismatchException e) {
				System.out.println("OOPS! Please only enter integers!");
			}
			
			boolean choiceLoop = true;
			System.out.println("Would you like to enter another integer? Y/N");
			while (choiceLoop) { 
				Scanner cinput = new Scanner(System.in);
				String choice = cinput.next();

				if (choice.toUpperCase().equals("Y")) { //converts to uppercase in case user inputs "y"
					choiceLoop = false;
					}
				else if (choice.toUpperCase().equals("N")) {
					choiceLoop = false;
					inputLoop = false;
				}
				else {
					System.out.printf("%s%n%s","Sorry, I didn't understand.!",
							"Please enter Y or N to make your choice:");
					}
			
				} //end of choice while loop
		
		} //end of input loop
		
		//bonus simulation - runs the two different search methods 1000 times on random integers
		// and computes average number of steps for both methods
		SecureRandom randnum = new SecureRandom();
		int anum = 1000; //number of times simulation is run, can be adjusted
		int[] linarray = new int[anum];
		int[] binarray = new int[anum];
		for (int i = 0; i < anum; i++) {
			int r = randnum.nextInt(nums.length) + 1; //generates the random integer to use
			linarray[i] = linsim(nums, r); //adds the # of steps from each individual simulation to an array
			binarray[i] = binsim(nums,r);
		}
		//calculating the averages
		int linsum = 0;
		int binsum = 0;
		for (int i = 0; i < anum; i++) {
			linsum += linarray[i];
			binsum += binarray[i];
		}
		double linavg = linsum / anum;
		double binavg = binsum / anum;
		
		//print output and exit
		System.out.printf("%n%s%n", "Running simulation...");
		System.out.printf("%n%s%d%s%n%s%.2f%n%n", "For a simulation of ",anum, " random integers (between 1-100), the average number of steps",
				"for the linear search was: ---> ",linavg);
		System.out.printf("%s%d%s%n%s%.2f%n%n", "For a simulation of ",anum, " random integers (between 1-100), the average number of steps",
				"for the binary search was: ---> ",binavg);
		System.out.println("Thanks for using the Search Compare program! Good Bye!");
		
	}

}
