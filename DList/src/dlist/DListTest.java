// Owen O'Connor
// CSC 201
// Assignment 7

package dlist;

/** 
 * @author owenoconnor
 * @since 9/24/21
 * test program for Dlist and DNode
 */
public class DListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DList dlist = new DList(); // create an empty DList
		dlist.addToTail(5);
		printList(dlist.head);
		dlist.addToTail(10);
		printList(dlist.head);
		DNode node = dlist.addToTail(15);
		printList(dlist.head);
		dlist.addToTail(20);
		dlist.addAfter(node, 12);
		printList(dlist.head);
		dlist.addToHead(4);
		printList(dlist.head);
		dlist.removeNode(10);
		printList(dlist.head);
		printListBackwards(dlist.tail);
		
		
	}
	
	/**
	 * prints the double linked list
	 * @param node
	 */
	public static void printList(DNode node) {
	// traverse list from head to tail
	for (DNode cursor = node;cursor!=null;cursor=cursor.getNext()) {
		System.out.printf("%d ", cursor.getData());
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
			System.out.printf("%d ", cursor.getData());
			}
		System.out.println();
		}

}