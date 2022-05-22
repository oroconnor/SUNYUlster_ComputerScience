// Owen O'Connor
// CSC 201
// Assignment 10


package objectstack;

import java.util.Scanner;
/**
 * Lets user input a word and then prints it backwards and evaluates 
 * wheter or not it is a palindrome.
 * @author owenoconnor
 * @since 10/6/21
 */
public class ObjectStackTest {
	public static void main(String[] args) {
	System.out.println("Enter a Word:");
	
	Scanner input = new Scanner(System.in);
	String word = input.next();
	
	System.out.print ("This is the word backwards: ");
	ObjectStack w = new ObjectStack(word.length());
	for (int i = 0; i < word.length(); i++) {
		w.push(word.charAt(i));
	}
	
	String ptest = "";
	for (int i = 0; i < word.length(); i++) {
		char character = (char) w.pop();
		System.out.print(character);
		ptest = ptest + character;
	}
	System.out.println();
	String palindrome;
	if (ptest.equals(word)) {
		palindrome = "is";
	} 
	else {
		palindrome = "is not";
	}
	System.out.printf("It %s a palindrome. ", palindrome);
	}
}

