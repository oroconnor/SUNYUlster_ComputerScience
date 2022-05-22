/* CSC180 - Final Project - Owen O'Connor
	Attempting to use style conventions in Cornell Java Style Guide:
 	https://www.cs.cornell.edu/courses/JavaAndDS/JavaStyle.html */

package stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.NumberFormatException;

/** @author owenoconnor
 *  @since 04/15/21
 *  Java Program allows user to load Stream data and compute summary statistics
 *   and trend analysis */
public class Stream {
	/**Displays the loaded data for all the datasets that have been loaded 
	 * @param streamList the collection of objects storing the stream data 
	 *  */
	public static void displayList(List<Brook> streamList) {
		
		System.out.printf("%n%s%n%n", "Stream Data Loaded:");
		System.out.printf("%-20s%-20s%-20s%-20s%n", 
				"Name", "Index", "Temp", "Turbidity");
		for (Brook brook : streamList) {
			
			System.out.printf("%-20s%n", brook.getName());

			for (int i = 0; i < brook.getIndex().size(); i++)  {
				System.out.printf("%-20s%-20s%-20s%-20s%n", "",
						brook.getIndex().get(i),brook.getTemp().get(i),
						brook.getTurbidity().get(i));
			}
		}
		System.out.println();
	} // End streamList method
	
	/**Allows user to load a new dataset. 
	 *  */
	public static List<Brook> addBrooks(List<Brook> streamList) {
		boolean inputLoop = true;
		boolean fileLoop = true;
		while (inputLoop) {
			while (fileLoop) {
				Scanner input = new Scanner(System.in);
				System.out.println("Please enter the filename in the working "
						+ "directory that you would"
						+ " like to load into the program:");
				// Let user enter anything for name
				String name = input.next();
	
				// Read in data from csv file
				try {
					Scanner read = new Scanner(new File(name));
					ArrayList<Double> tempIndex = new ArrayList<Double>();
					ArrayList<Double> tempTemp = new ArrayList<Double>();
					ArrayList<Double> tempTurbidty = new ArrayList<Double>();
					
					read.nextLine(); // Skip first line
					while (read.hasNext()) {  
						String entry = read.nextLine();
						String [] entries = entry.split(",");
						// System.out.printf("%-12s %-12s %n",entries[0],entries[1]);
						tempIndex.add(Double.parseDouble(entries[0]));
						tempTemp.add(Double.parseDouble(entries[1]));
						tempTurbidty.add(Double.parseDouble(entries[2]));		
					}   
					read.close();  // Close the scanner  
					
					Brook brook = new Brook(name, tempIndex, tempTemp, tempTurbidty);
					streamList.add(brook);
					fileLoop = false;
					
				} 
				catch (IOException e) {
					System.out.printf("%s%n%s%n%n", "Unable to locate that file. "
							+ "There are two prepared files that come with the "
							+ "program that you can use: stream1.csv and stream2.csv", 
							"or place your own csv file in "
							+ "the working directory of this program.");
				} 
				catch (NumberFormatException e) {
					System.out.printf("%s%n%s%n%s%n%n", "The file that you loaded does not meet the formatting "
							+ "criteria for this program.", "Try loading one of the included example files"
							+ " (stream1.csv or stream2.csv)", "and then choosing the help page from the "
							+ "main menu for more information.");
				}
			} // End fileLoop
			
			boolean choiceLoop = true;
			System.out.println("Would you like to another file of stream data? "
					+ "Y/N");
			while (choiceLoop) { 
				Scanner cinput = new Scanner(System.in);
				String choice = cinput.next();
				
				// Convert to uppercase in case user inputs "y"
				if (choice.toUpperCase().equals("Y")) { 
					fileLoop = true;
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
		return streamList;
	} //End addBrooks method
	
	
	/** 
	 * Checks for multi-modal flag in summary statistics, and returns a string 
	 * saying "multimodal" instead of flag
	 * @param x result of Brook.mode()
	 * @return either the actual mode or string label "multimodal"
	 */
	public static String modeCheck(double x) {
		if (x == -999.00) {
			return "multimodal";
		}
		else {
		
			return Double.toString(x);
		}
		}
		
	/**
	 * Prints out the summary statistics for all the loaded datasets. 
	 * @param streamList the collection of objects storing the stream data 
	 */
	public static void summary(List<Brook> streamList) {
		System.out.printf("%n%s%n%n", "Summary Statistics:");
		for (Brook brook : streamList) {
			System.out.printf("%-20s%-20s%n%-20s%-20s%-20s%-20s%-20s%-20s%n%-20s"
					+ "%-20.2f%-20.2f%-20.2f%-20s%-20.2f%n%-20s%-20.2f%-20.2f%-20.2f"
					+ "%-20s%-20.2f%n", 
					"Name:", brook.getName(),"", "Average", "Min", "Max", "Mode", 
					"SD", "Temperature:", brook.getSummary().get(0), 
					brook.getSummary().get(1), brook.getSummary().get(2),
					modeCheck((double) brook.getSummary().get(3)), 
					brook.getSummary().get(4),
					"Turbidity:",brook.getSummary().get(5), brook.getSummary().get(6), 
					brook.getSummary().get(7), modeCheck((double) brook.getSummary().get(8)),
					brook.getSummary().get(9)
					);
		System.out.println();
	}
	}
	
	/**
	 * Mann-Kendall trend test for datasets
	 * @param streamList the collection of objects storing the stream data 
	 */
	public static void trends(List<Brook> streamList) {
		Scanner input = new Scanner(System.in);
		boolean trendsLoop = true;
		while (trendsLoop) {
			try {
			System.out.println("Please enter 1 for Temp or 2 for Turbidity:");
			int choice = Integer.parseInt(input.next());
			
			for (Brook brook : streamList) {
			brook.mannKendall(choice);
			trendsLoop = false;
			}
			
			}
			catch (NumberFormatException e) {
				System.out.printf("%s%n", "Oops. Please confine your choice to "
						+ "the integer 1 or 2.");
			}
			catch (NullPointerException e) {
				System.out.printf("%s%n", "Oops. Please confine your choice to "
						+ "the integer 1 or 2.");
			}
		} // End trendsLoop
	} // End trends method
	/**
	 * Access help.txt file for updated help info
	 */
	public static void help() {
		
		try {
			System.out.println("Here is the most up to date help documentation:");
			// Read from help textfile  
			Scanner fileinput = new Scanner(Paths.get("help.txt"));
			while (fileinput.hasNext()) {
				String line = fileinput.nextLine();
				System.out.println(line);
				}
			}
			catch (IOException ioException) {
				System.out.println("Error opening help file");
			}
		System.out.println();
	} // End help method
	

	
	/**
	 * User menu 
	 * @param streamList the collection of objects storing the stream data 
	 */
	public static void menu(List<Brook>  streamList) {
		
		while (true) {
		System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n", "Enter A to add more "
				+ "stream data","Enter S to see summary statistics", 
				"Enter T for Mann-Kendall trend analysis", "Enter H for help "
						+ "documentation", 
				"or Enter STOP to exit", "Please Choose an Option from the Menu:");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		if (choice.toUpperCase().equals("A")) { 
			streamList = addBrooks(streamList);
			}

		else if (choice.toUpperCase().equals("S")) {
			summary(streamList);
			}
		
		else if (choice.toUpperCase().equals("T")) {
			trends(streamList);
			}
		
		else if (choice.toUpperCase().equals("H")) {
			help();
			}
		
		else if (choice.toUpperCase().equals("STOP")) {
			System.out.println("Goodbye");
			System.exit(1); // End the program
			}
		
		else {
			System.out.println("Sorry, I didn't understand.");
			}
	
	}
	}// End menu
	
	/**
	 * Main program 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		List<Brook> streamList = new ArrayList<Brook>();	
		System.out.printf("%s%n%n","Welcome to Stream Trend Analysis!");
		
		addBrooks(streamList);
		displayList(streamList);
		summary(streamList);
		
		menu(streamList);
	} // End main
} // End Stream class