//Owen O'Connor - CSC 180 - assignment #8

package marathon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

/** @author owenoconnor
 *  @since 03/25/21
 *  Java Program that allows user to add runner info to race result list and shows the race results, sorted by duration.  */
public class Marathon {
	
	//provides formating for duration printout
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	/**Displays the formated list of race results
	 * 
	 * @param marathonList the list of Runner objects that stores the runners data
	 */
	public static void displayList(List<Runner> marathonList) {
		Collections.sort(marathonList,new RunnerComparator());
		System.out.printf("%n%s%n%n", "Marathon Results:");
		System.out.printf("%-20s %-20s %-20s %-20s%n", 
				"Runner", "Start Time", "End Time", "Duration");
		for (Runner runner : marathonList) {
			System.out.printf("%-20s %-20s %-20s %-20s%n", 
					runner.getName(), runner.getStartTime(), runner.getEndTime(), df.format(runner.getDuration())+" hours");
		}
		System.out.println();
	} //end of marathonList method
	
	/**Allows user to input more runners to race results
	 * 
	 * @param marathonList the list of Runner objects that stores the runners data
	 */
	public static List<Runner> addRunners(List<Runner> marathonList) {
		boolean inputLoop = true;
		while (inputLoop) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter the information for the next runner:");
			System.out.print("Enter Runner Name:");
			//lets user enter anything for name
			String name = input.next();
			try {
			System.out.print("Enter Start Time Hours:");
			int sh = input.nextInt();
			System.out.print("Enter Start Time Minutes:");
			int sm = input.nextInt();
			System.out.print("Enter Start Time Seconds:");
			int ss = input.nextInt();
			System.out.print("Enter End Time Hours:");
			int eh = input.nextInt();
			System.out.print("Enter End Time Minutes:");
			int em = input.nextInt();
			System.out.print("Enter End Time Seconds:");
			int es = input.nextInt();
			RaceTime start = new RaceTime(sh,sm,ss);
			RaceTime end = new RaceTime(eh,em,es);
			if (comparing(start,end) > 0) {
				throw new NegativeRaceTime();
			}
			
			Runner runner = new Runner(name,start,end);
			marathonList.add(runner);
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("You're going to need to input that runner's information again.");
			}
				
			catch (NegativeRaceTime e) {
				System.out.println("Hmm...Looks like the start time is after the end time. This is not valid.");
				System.out.println("You're going to need to input that runner's information again.");
			}
			catch (InputMismatchException e) {
				System.out.println("Remember: Hours, Minutes, and Seconds all need to be inputted as integers.");
				System.out.printf("%s%n%n","You're going to need to input that runner's information again.");
			}
			
			boolean choiceLoop = true;
			System.out.println("Would you like to enter another runner? Y/N");
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
		
	}
		return marathonList;
	} // end of addRunners method
	

	/**Used to validate that race end times come after start times
	 * @param rt1 first RaceTime object to compare
	 * @param rt2 second RaceTime object to compare
	 */
	public static int comparing(RaceTime rt1, RaceTime rt2) {
		int hourDiff = rt1.getHour() - rt2.getHour();
		if (hourDiff != 0) { return hourDiff;}
		
		int minuteDiff = rt1.getMinute() - rt2.getMinute();
		if (minuteDiff != 0) { return minuteDiff;}
		
		int secondDiff = rt1.getSecond() - rt2.getSecond();
		return secondDiff;
	}
	
	/** The user menu, loop continues until user elects to exit program
	 * @param marathonList the list of Runner objects that stores the runners data
	 */
	public static void menu(List<Runner>  marathonList) {
		displayList(marathonList);
		while (true) {
		System.out.printf("%s%n%s%n","Would you like to Add runners to the Race Results?", "Enter A to enter more runners or STOP to exit:");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		if (choice.toUpperCase().equals("A")) { 
			marathonList = addRunners(marathonList);
			displayList(marathonList);
			}

		else if (choice.toUpperCase().equals("STOP")) {
			displayList(marathonList);
			System.out.println("Goodbye");
			System.exit(1); // this is how the program ends
			}
		else {
			System.out.println("Sorry, I didn't understand.");
			}
	
	}
} //end of menu method
	
	/** Main program 
	 * @param args
	 */
	public static void main(String[] args) {
		//our list of Runner objects that stores runner data
		List<Runner> marathonList = new ArrayList<Runner>();
		System.out.printf("%s%n%n","Welcome to the Marathon Race Results!");
		addRunners(marathonList);
		menu(marathonList);
		

	} //end of main

}
