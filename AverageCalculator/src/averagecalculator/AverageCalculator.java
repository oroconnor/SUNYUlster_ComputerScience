//Owen O'Connor - CSC180 - Midterm Exam

package averagecalculator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/** @author owenoconnor
 *  @since 03/09/21
 *  Java Program allow user to input 10 integers, stores them in text file, and gives option of calculating 
 *  average of the numbers stored in the text file */
public class AverageCalculator {
	
	/**  Opens up text file and calculates the average of the numbers contained in it.  */
	public static void calcAverage() {
		try {
			//reading from text file numbers.txt
			Scanner fileinput = new Scanner(Paths.get("numbers.txt"));
			int counter = 0; // tracks the count of the integers read in
			double total = 0; // running total for use in average calculation
			while (fileinput.hasNext()) { //read in lines from text file and split into array of strings
				String nums = fileinput.nextLine();
				String [] entries = nums.split(","); 
			
			for (int i = 0; i < entries.length; i++) {
				// converts each string in the array to an integer while adding it to running total
				total = total + Integer.parseInt(entries[i]); 
				counter++; 
			}
			}
			if (counter == 0) {
				//in case it can't find a match
				System.out.println("Sorry, no numbers were stored in this file.");
				System.exit(1);
			}
			double avg = total / counter; //calculates average
			System.out.printf("%s%d%n","The number of integers stored in this file is:",counter);
			System.out.printf("%s%.2f%n","The average of the integers stored in this file is:",avg);
			}
			catch (IOException ioException) {
				System.out.println("Error opening file. Terminating");
				System.exit(1);
			}
			catch (NumberFormatException e) {
				//in case Integer.parseInt() encounters something that can't be parsed to integer
				System.out.println("File contained values other than integers. Terminating. ");
				System.exit(1);
			}
		
		}	
	
	/**  Allows user to input integers and offers the option to calculate their average */
	public static void main(String[] args) {
		
			boolean contInputLoop = true; // used in do/while loop for integer input below
			
			try  (Formatter output = new Formatter("numbers.txt")) {
				Scanner input = new Scanner(System.in);
				int counter = 0;
				System.out.println("Welcome to the Average Calculator!");
				System.out.printf("%n%s%n", 
						"Enter 10 integers, one at a time. Hit return after each entry:"); 
				
				do {
				//loops to allow user to enter 10 integers
				//while (contInputLoop) {
					//String entry = input.nextLine();
					while (counter < 10 ) {
						String entry = input.nextLine();
						//validates that it's an integer entered, including negative integers
						if (Pattern.matches("-?[0-9]+", entry) == true) { 
							output.format("%s,",entry);
							counter ++;
							}
						else {
							System.out.printf("Sorry, please only enter integers. %n"
									+ "Try again and continue:%n");
						}
						
					}

				contInputLoop = false;
				} while (contInputLoop);
			}
			catch (SecurityException securityException) {
			    	System.out.println("Write permission denied. Terminating");
			    	System.exit(1);
			    }
			catch (FileNotFoundException fileNotFoundException) {
			    	System.out.println("Error opening file. Terminating");
			    	System.exit(1);
			    }
				
			Scanner input = new Scanner(System.in);	
			System.out.println("Thank you for entering 10 integers!");
			System.out.printf ("%n%s%n%s%n","Would you like to calculate the average of the numbers stored in numbers.txt?",
					"Y/N:");
			boolean choiceloop = true;
			while (choiceloop) { // gets the input for user choice as to whether to calculate average
				String choice = input.next();

				if (choice.toUpperCase().equals("Y")) { //converts to uppercase in case user inputs "y"
					calcAverage();
					choiceloop = false;
					}
				else if (choice.toUpperCase().equals("N")) {
					System.out.println("Ok, thanks for using this program!");
					choiceloop = false;
				}
				else {
					System.out.printf("%s%n%s","Sorry, I didn't understand.!",
							"Please enter Y or N to make your choice:");
					}
			
			} //end of choice while loop
	} //end of main
	
}
