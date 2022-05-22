// Owen O'Connor
// CSC 201
// Assignment 9


package arraybag;

/**
 * Program that uses ArrayBag class to produce game show results. 
 * @author owenoconnor
 * @since 10/3/21
 */
public class GameShow {
	
	public static void main(String[] args) {
		
		ArrayBag<String> sbag = new ArrayBag<String>();
		ArrayBag<Integer> ibag = new ArrayBag<Integer>();
		
		// Add names
		sbag.add("Elsa");
		sbag.add("Grace");
		sbag.add("Henri");
		sbag.add("Ida");
		sbag.add("Larry");
		
		// Add dollar amounts
		ibag.add(0);
		ibag.add(50);
		ibag.add(100);
		ibag.add(500);
		ibag.add(10000);
		
		
		// Print out Game Show results
		for (int i = 0; i < 5; i++) {
			System.out.printf("%s has won $%,d!!!%n",(String) sbag.grab(),(Integer) ibag.grab());
		}
		
		
	}

}
