//Owen O'Connor - CSC180

package addressbook;

import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;


/** @author owenoconnor
 *  @since 02/24/21
 *  Java Program allow user to input entries into an address book and then search by last name */
public class AddressBook {
	
	/** Searches address book and prints entries that match the search term
	 *  @param lastname the user inputted String that will be used to search entries by last name
	 *   */
	public static void searchFile(String lastname) {
		try {
			System.out.println("The following records meet your search criteria:");
			//reading from textfile address book
			Scanner fileinput = new Scanner(Paths.get("AddressBook.txt"));
			int counter = 0; //tracks whether any entries were located
			while (fileinput.hasNext()) {
				String entry = fileinput.nextLine();
				String [] entries = entry.split(" ");
				//if the search term matches the last name from an entry, prints the entry in the prescribed formate
				if (lastname.equals(entries[0].toUpperCase())) {
					System.out.printf("%-20s %-20s %-20s %-20s",entries[1],entries[0],entries[2],entries[3]);
					counter ++; 
				}
			}
			if (counter == 0) {
				//in case it can't find a match
				System.out.println("Sorry, no entries were found for that last name.");
			}
			}
			catch (IOException ioException) {
				System.out.println("Error opening file");
				System.exit(1);
			}
		}	
	/**  Allows user to input entries into an address book and then search by last name */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			boolean contInputLoop = true;
			
			try  (Formatter output = new Formatter("AddressBook.txt")) {
				Scanner input = new Scanner(System.in);
				System.out.println("Welcome to Address Book!");
				System.out.printf("%n%s%n%s%n", 
						"Enter last name, phone number and email address","Enter EOF to end input"); 
				do {
				//allows user to input to input anything as long as four fields are entered and each field is seperated by spaces
				String entry = input.nextLine();
				
				//loops to allow as many entries as user wants until sentinel value EOF is entered
				while (!entry.equals("EOF")) {
						String [] entries = entry.split(" ");
						if (entries.length == 4) {
							output.format("%s %s %s %s %n",entries[0], entries[1],entries[2],entries[3]);
							}
						else {
							System.out.printf("Last entry not recorded. All entries must have all 4 fields. %n"
									+ "All fields must be seperated by spaces. Try again and continue:%n");
						}
						entry = input.nextLine();
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
				
			Scanner search = new Scanner(System.in);	
			System.out.println("Thank you for inputting Address Book Entries.");
			
			//boolean searchLoop = true;
			while (true) {
				System.out.printf("%nEnter last name to search records or STOP to exit:%n");
				
				String last = search.next(); 
				if (last.equals("STOP")) break;
				//validates for last name search term that only contain letters
				while (Pattern.matches("[a-zA-Z]+", last) == false) {
					System.out.println("Last name must contain only letters. Please try again");
					last = search.next(); 
				}
				//method to search for that specific last name and print results
				//converts to uppercase to remove case sensitivity
				searchFile(last.toUpperCase());
				}
			search.close();
			}
				
				

			}
			
	
			
			
			
	
		



