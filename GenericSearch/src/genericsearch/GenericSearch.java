package genericsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** @author owenoconnor
 *  @since 04/13/21
 *  Java Program allows user to search lists of different types */
public class GenericSearch {

	/**
	 * The binary search generic method.
	 * @param <T> generic type 
	 * @param ar array, type unspecified
	 * @param x value to search for
	 * @param type string describing the type of array based on user choice
	 */
	public static <T extends Comparable> void binsearch(T[] ar, T x, String type) {
		//binary search - inspired by textbook example
		//this search method requires a sorted array in ascending order. 
				int ind = -1; //index of found value
				int low = 0; 
				int high = ar.length - 1; 
				int middle = (low + high + 1) / 2; 
				int counter = 0;
				
				boolean contbinary = true;
				while (contbinary) {
					//if (low > high) break; 
					counter++; //tracks how many times loop is run
					if (x.equals(ar[middle])) {  //if x is found, it exits loop
						ind = middle;
						contbinary = false;
					}
					else if (x.compareTo(ar[middle]) < 0) {
						high = middle - 1; //eliminates upper half if middle is too high
					}
					else {
						low = middle + 1; //eliminates lower half if middle is too low
					}
					middle = (low + high + 1) / 2;
					if (counter >  ar.length - 1) contbinary = false; // if all items have been searched, exit loop
				}
				
				if (ind == -1) {
					System.out.printf("%n%s%n%n", "Sorry. Item Not Found");
				}
				else {
					String y = String. valueOf(x);
					System.out.printf("%n%s %s %s %d%n%n",type,y, "was found at Index",ind);
				}
	}
	
	/**
	 * Presents user with search menu for binary search of different lists
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer[] integerArray = {1,2,3,4,5,6};
		Double[] doubleArray = {1.1,2.2,3.3,4.4,5.5};
		String[] stringArray = {"A","B","C","D","E"}; //this search is case sensitive for strings
	
		boolean inputLoop = true;
		while (inputLoop) {
		
		Scanner input = new Scanner(System.in);
		System.out.printf("%s%n%n%s%n%s%n%s%n%n", "Which list to search?","   1) Integer", "   2) Double","   3) String");
		
		//validating for correct list options
		boolean listLoop = true;
		String[] options = {"1","2","3"}; 
		List <String> optionsList = Arrays.asList(options);
		String a = null;
		while (listLoop) {
			a = input.next();
			if (optionsList.contains(a)) {
				listLoop = false;
			}
			else System.out.printf("%s%n", "Sorry. Please type 1,2, or 3 to choose a valid list to search.");
		}
		
		Integer arr = Integer.parseInt(a); //won't throw NumberFormatException because listLoop above only allows limited inputs
		
		
		System.out.printf("%s%n", "Please enter the value to search:");
		String x = input.next();
		try {
		switch (arr) { //responding to selection, inputs are parsed to appropriate type, and then passed to binsearch()
			case 1: binsearch(integerArray, Integer.parseInt(x), "Integer"); break;
			case 2: binsearch(doubleArray,Double.parseDouble(x),"Double"); break;
			case 3: binsearch(stringArray,x,"String"); break; 
		}
		} // end of try block
		catch (NumberFormatException e) {
			System.out.printf("%n%s%n%s%n%n", "Sorry. Item Not Found.","Please check that you are search for a value appropriate to the list you are searching.");
		}
	//	catch (ArrayIndexOutOfBoundsException e) {
	//		System.out.printf("%n%s%n%n", "Sorry. Item Not Found");
	//	}
		
		boolean choiceLoop = true;
		System.out.println("Would you like to do another search? Y/N");
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
		
		System.out.printf("%n%s", "Goodbye!");
		
	}

}
