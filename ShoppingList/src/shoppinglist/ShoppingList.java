//Owen O'Connor - CSC180 - Assignment 7
package shoppinglist;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/** @author owenoconnor
 *  @since 03/16/21
 *  Java Program that allows user to add items to a shopping list, and then gives them the option to add or remove
 *  items from shopping list */
public class ShoppingList {

	/**  Displays the Shopping List. 
	 * @param shoppingList is the List containing the shopping list entered by the user */
	public static void displayList(List<String> shoppingList) {
		System.out.printf("%n%s%n", "Here is your shopping list:");
		for (String item : shoppingList) {
			System.out.println(item);
		}
		System.out.println();
	} //end of displayList method
	
	/**  Allows user to add to the Shopping List, one item at a time
	 * @param shoppingList is the List containing the shopping list entered by the user */
	public static List<String> addItems(List<String> shoppingList) {
		boolean inputLoop = true;
		while (inputLoop) {
			Scanner input = new Scanner(System.in);
			System.out.print("Please enter an item to add to list:");
			String newItem = input.nextLine();
			//just validating to make sure they entered something.
			//otherwise, user can enter whatever they want to their list
			if (!newItem.equals("")) shoppingList.add(newItem);  
			boolean choiceLoop = true;
			System.out.println("Would you like to enter another item? Y/N");
			while (choiceLoop) { 
				String choice = input.next();

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
		return shoppingList;
	} // end of addItems method
	
	/**  Allows user to remove items from the Shopping List, one item at a time
	 * @param shoppingList is the List containing the shopping list entered by the user */
	public static List<String> removeItems(List<String> shoppingList) {
		
		boolean inputLoop = true;
		while (inputLoop) {
			Scanner input = new Scanner(System.in);
			System.out.print("Please enter an item to remove from list:");
			String removeItem = input.nextLine();
			if (shoppingList.contains(removeItem)) {
				shoppingList.remove(removeItem); }
			else System.out.println("Hmmm... not seeing that exact match on your list...");
			boolean choiceLoop = true;
			System.out.println("Would you like to remove another item? Y/N");
			while (choiceLoop) { 
				String choice = input.next();

				if (choice.toUpperCase().equals("Y")) { //converts to uppercase in case user inputs "y"
					choiceLoop = false;
					}
				else if (choice.toUpperCase().equals("N")) {
					choiceLoop = false;
					inputLoop = false;
				}
				else {
					System.out.printf("%s%n%s","Sorry, I didn't understand.",
							"Please enter Y or N to make your choice:");
					}
			
				} //end of choice while loop
		
	}
		return shoppingList;
	} // end of removeItems method
	
	/**  Gives users the option to add or remove items from shopping list, or exit the program.
	 * @param shoppingList is the List containing the shopping list entered by the user */
	public static void menu(List<String>  shoppingList) {
			displayList(shoppingList);
			while (true) {
			System.out.printf("%s%n%s%n","Would you like to Add or Remove an item?", "Enter A for Add, R for Remove, or STOP to exit:");
			Scanner input = new Scanner(System.in);
			String choice = input.next();
			if (choice.toUpperCase().equals("A")) { 
				shoppingList = addItems(shoppingList);
				displayList(shoppingList);
				}
			else if (choice.toUpperCase().equals("R")) {
				shoppingList = removeItems(shoppingList);
				displayList(shoppingList);
			}
			else if (choice.toUpperCase().equals("STOP")) {
				displayList(shoppingList);
				System.out.println("Goodbye");
				System.exit(1); // this is how the program ends
				}
			else {
				System.out.println("Sorry, I didn't understand.");
				}
		
		}
	} //end of menu method
	
	/**  Executes the shopping list program and calls the required methods */
	public static void main(String[] args) {
		List<String> shoppingList = new ArrayList<String>();
		System.out.println("Welcome to My Shopping List!");
		shoppingList = addItems(shoppingList);
		menu(shoppingList);
		
	} //end of main

}
