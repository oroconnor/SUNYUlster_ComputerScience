// Owen O'Connor
// CSC 201
// Exam 1

package dlistexam;

/** 
 * @author owenoconnor
 * @since 10/5/21
 * test program for Dlist and DNode
 * added steps per exam instructions
 * and made double linked list generic
 */
public class DListTest {

	public static void main(String[] args) {
		int[] array = {10, 15, 35, 67, 89, 101};
		
		DList dlist = new DList(); // Create an empty DList
		// Add the array contents to double linked list
		for (int i = 0; i < array.length; i++) {  
			dlist.addToTail(array[i]);
		}
		printList(dlist.head); // Print list forward
		printListBackwards(dlist.tail); // Print list backwards
		
		// test with different data type
		String[] array2 = {"apples", "bananas","mangos", "melons", "oranges", "pears"};
		
		DList dlist2 = new DList(); // Create an empty DList
		// Add the array contents to double linked list
		for (int i = 0; i < array2.length; i++) {  
			dlist2.addToTail(array2[i]);
		}
		printList(dlist2.head); // Print list forward
		printListBackwards(dlist2.tail); // Print list backwards
		
		
		
		
	}
	
	/**
	 * prints the double linked list
	 * @param node
	 */
	public static void printList(DNode node) {
	// traverse list from head to tail
	for (DNode cursor = node;cursor!=null;cursor=cursor.getNext()) {
		System.out.printf("%s, ", cursor.getData());
		}
	System.out.println();
	}
	
	/**
	 * prints the double linked list backwards
	 * @param node
	 */
	public static void printListBackwards(DNode node) {
		// traverse list from tail to head
		for (DNode cursor = node;cursor!=null;cursor=cursor.getPrev()) {
			System.out.printf("%s, ", cursor.getData());
			}
		System.out.println();
		}

}