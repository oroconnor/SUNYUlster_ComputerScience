//Owen O'Connor - CSC180 - Assignment 6

package addressbooks;

import java.lang.SecurityException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;

/** @author owenoconnor
*  @since 03/05/21
*  Java Program allow user to input entries into an address book and then search by last name 
*  using serialization*/
public class AddressBookS {
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	/** Searches address book and prints entries that match the search term
	 *  @param lastname the user inputted String that will be used to search entries by last name
	 *  @param count is the number of records that were entered during the user input
	 *   */
	public static void searchFile(String lastname, int count) {
		try {
			openFileForRead();
			System.out.println("The following records meet your search criteria:");
			int counter = 0; //tracks whether any entries were locate
			for (int i=0; i<count;i++) {
			    Address a = (Address) input.readObject();
			    String last = a.getLastName();
				//if the search term matches the last name from an entry, prints the entry in the prescribed format
				if (lastname.equals(last.toUpperCase())) {
					System.out.printf("%-20s %-20s %-20s %-20s%n",a.getFirstName(),a.getLastName(),a.getPhoneNumber(),a.getEmailAddress());
					counter ++; 
				}
				
			} 
			if (counter == 0) {
				//in case it can't find a match
				System.out.println("Sorry, no entries were found for that last name.");
			}
			}
			catch (IOException ioException) {
				System.out.println("Error opening file. Terminating.");
				ioException.printStackTrace();
				System.exit(1);
				
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found. Terminating.");
				System.exit(1);
			}
		
		}	
	
	/** Opens the file so that records can be added */
	public static void openFile() {
		try {
			output = new ObjectOutputStream(Files.newOutputStream(Paths.get("AddressBookS.ser")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.printf("Unable to open file%n");
			System.exit(1);
		}
	} // end OpenFile
	
	/** Closes the file with the records */
	public static void closeFile() {
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Opens the file with the records so that records can be read by program*/
	public static void openFileForRead() {
		try {
			input = new ObjectInputStream(
				Files.newInputStream(Paths.get("AddressBookS.ser")));
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	
	/**  Allows user to input entries into an address book and then search by last name */
	public static void main(String[] args) {
			boolean contInputLoop = true;
			int count = 0; //counts number of records added, so that searchFile() can loop appropriately
			
			try  {
				openFile();
				Scanner input = new Scanner(System.in);
				System.out.println("Welcome to Address Book!");
				System.out.printf("%n%s%n%s%n", 
						"Enter last name, phone number and email address","Enter EOF to end input"); 
				
				do {
				//allows user to input to input anything as long as four fields are entered and each field is separated by spaces
				String entry = input.nextLine();
				
				//loops to allow as many entries as user wants until sentinel value EOF is entered
				while (!entry.equals("EOF")) {
						String [] entries = entry.split(" ");
						if (entries.length == 4) {
							Address record = new Address(entries[0], entries[1],entries[2],entries[3]);
							output.writeObject(record);
							count++;
							}
						else {
							System.out.printf("Last entry not recorded. All entries must have all 4 fields. %n"
									+ "All fields must be seperated by spaces. Try again and continue:%n");
						}
						entry = input.nextLine();
					}

				contInputLoop = false;
				} while (contInputLoop);
				closeFile();

			}
			catch (SecurityException securityException) {
			    	System.out.println("Write permission denied. Terminating");
			    	System.exit(1);
			    }
			catch (FileNotFoundException fileNotFoundException) {
			    	System.out.println("Error opening file. Terminating");
			    	System.exit(1);
			    } 
			catch (IOException e) {
				System.out.println("Error opening file. Terminating.");
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
				searchFile(last.toUpperCase(),count);
				}
			search.close();
			
			}
			}
				
				

			
	
			
			
			
	
		