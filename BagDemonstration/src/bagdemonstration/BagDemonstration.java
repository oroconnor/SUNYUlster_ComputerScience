//Owen O'Connor
//CSC201
//Assignment 4

package bagdemonstration;

import java.util.Scanner;

/**
 * @author owenoconnor
 * @since 9/11/21
 * tests IntArrayBag
 */
public class BagDemonstration {
private static Scanner stdin = new Scanner(System.in);
/**
 * @param args
 * main for test program for IntArrayBag
 */
public static void main(String[] args) {
	IntArrayBag ages = new IntArrayBag(); // create new bag
	getAges(ages);
	checkAges(ages);
	System.out.println("May your family live long and prosper.");
	} // End main

/**
 * @param ages - bag to contain inputted ages
 * allows user to input ages
 */
public static void getAges(IntArrayBag ages) {
	int userInput;
	System.out.println("Type the ages of your family members.");
	System.out.println("Type a negative number at the end and press return");
	userInput = stdin.nextInt();
	while (userInput >= 0) {
		ages.add(userInput);
		userInput = stdin.nextInt();
	}
} // End getAges

/**
 * @param ages- bag of user inputted ages
 * allows user to verify ages
 */
public static void checkAges(IntArrayBag ages) {
	int userInput;
	System.out.print("Type those ages again.");	
	System.out.print("Press return after each age.");
	while (ages.size() > 0) {
		System.out.print("Next age: ");
		userInput = stdin.nextInt();
		if (ages.countOccurrences(userInput) == 0) {
			System.out.println("No, that age does not occur!");
		} else {
			System.out.println("Yes, I've got that age and will remove it.");
			ages.remove(userInput);
		}
	}
} // End checkAges
 
} // End Class




