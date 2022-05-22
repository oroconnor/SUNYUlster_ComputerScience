//Owen O'Connor
//CSC 201
//Assignment 2

package militarytimeconverter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import militarytimeconverter.NumberNotInRange;

/** 
 *  @author owenoconnor
 *  @since 08/27/21
 *  Program that lets user input integers that represent hours in Military Time, 
 *  and outputs hours in Regular Time.
 */
public class MilitaryTimeConverter {
	/**
	 * Convert an input # of Hours from Military Time to Regualar Time
	 * @param t - an integer representing # of Hours (0 to 23)
	 * @return - an integer representing the # of Hours (1 to 12) in standard Time
	 */
	public static int convertFromMilitaryTime(int t) {
		//conversion method
		int time;
		if (t < 0 ||  t > 23) {
			throw new IllegalArgumentException("Input must an integer between 0-23");
		}
		if (t == 0) {
			time = 12;
		}
		else if (t > 12) {
			time = t - 12;
		}
		else {
			time = t;
		}
		return time;
	}

	/**
	 * Main program 
	 * @param args
	 */
	public static void main(String[] args) {
		//main program
		// Test cases:
		assert (convertFromMilitaryTime(0) == 12);
		assert (convertFromMilitaryTime(1) == 1);
		assert (convertFromMilitaryTime(2) == 2);
		assert (convertFromMilitaryTime(3) == 3);
		assert (convertFromMilitaryTime(4) == 4);
		assert (convertFromMilitaryTime(6) == 6);
		assert (convertFromMilitaryTime(7) == 7);
		assert (convertFromMilitaryTime(8) == 8);
		assert (convertFromMilitaryTime(9) == 9);
		assert (convertFromMilitaryTime(10) == 10);
		assert (convertFromMilitaryTime(11) == 11);
		assert (convertFromMilitaryTime(12) == 12);
		assert (convertFromMilitaryTime(13) == 1);
		assert (convertFromMilitaryTime(14) == 2);
		assert (convertFromMilitaryTime(15) == 3);
		assert (convertFromMilitaryTime(16) == 4);  
		assert (convertFromMilitaryTime(17) == 5);
		assert (convertFromMilitaryTime(18) == 6);
		assert (convertFromMilitaryTime(19) == 7);
		assert (convertFromMilitaryTime(20) == 8);
		assert (convertFromMilitaryTime(21) == 9);
		assert (convertFromMilitaryTime(22) == 10);
		assert (convertFromMilitaryTime(23) == 11);
		
		boolean inputLoop = true;
		while (inputLoop) {
			boolean conversionLoop = true;	
			while (conversionLoop) {
				//ask for input
				System.out.println("Please enter an hour in Military Time for Conversion:");
				Scanner input = new Scanner(System.in);
			
				//make conversion
				try {
					int t = Integer.parseInt(input.next());
					if (t < 0 ||  t > 23) throw new NumberNotInRange();
					int time = convertFromMilitaryTime(t);
					conversionLoop = false;
					//print out
					System.out.printf("%s%d%n%n","Result in Regular Time: ",time);
					
				}
				catch (InputMismatchException inputMismatchException) {
					System.out.printf("You must enter integers.  Please try again.%n%n");
				}
				catch (NumberFormatException numberFormatException) {
					System.out.printf("You must enter integers.  Please try again.%n%n");
				}
				catch (NumberNotInRange e) {
					System.out.printf("Input must an integer between 0-23.%n%n");
				}
			} // End conversionLoop
				
					
			boolean choiceLoop = true;
			System.out.println("Would you like to input another Military Time Hour for Conversion? Y/N");
			while (choiceLoop) { 
				Scanner cinput = new Scanner(System.in);
				String choice = cinput.next();
				
				// Convert to uppercase in case user inputs "y"
				if (choice.toUpperCase().equals("Y")) { 
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
			
			} // End choice while loop
		} //End inputLoop
	} // End main
} // End class


	

