// Owen O'Connor
// CSC 201
// Assignment 18

package hashbook;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Creates a simple hashtable and lets users search for phone numbers
 * @author owenoconnor
 * 
 */
public class Hashbook {

	public static void main(String args[])  {
		

	Hashtable<String, String> phonebook = new Hashtable<>(811);
	phonebook.put("John", "845-555-2341");
	phonebook.put("Larry", "845-555-1234");
	phonebook.put("Curly", "845-555-4321");
	phonebook.put("Moe", "845-555-4123");

	Scanner input = new Scanner(System.in);
	String name = null;
	
	while (true) {
	System.out.println("Enter an name to search for:");
	name = input.next();
	if (name.equals("EXIT")) {
		break;
	}
	String num = phonebook.get(name);
	System.out.printf("Phone number for %s is: %s", name, num);
	System.out.println();
	
	}
	
	System.out.println("Goodbye!");
		
}
}