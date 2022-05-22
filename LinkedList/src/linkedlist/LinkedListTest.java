// Owen O'Connor
// CSC 201 
// Assignment 6

package linkedlist;

/**
 * 
 * @author owenoconnor
 * @since 9/21/21
 * test program for LinkedList and IntNode classes
 */
public class LinkedListTest {

	public static void main(String[] args) {

		// Create an empty linked list
		// with head and tail set to null
		LinkedList list = new LinkedList();
		
		IntNode node1 = list.addFirst(10);
		System.out.println(list);
		IntNode node2 = list.addFirst(-8);
		System.out.println(list);
		IntNode node3 = list.addFirst(22);
		System.out.println(list);
		IntNode node4 = list.addFirst(5);
		System.out.println(list);
		node3.addNodeAfter(42);
		System.out.println(list);
		node3.removeNodeAfter();
		System.out.println(list);
		list.removeNodeBefore(10);
		System.out.println(list);
		list.addNodeBefore(5, 15);
		System.out.println(list);
		list.removeHead();
		System.out.println(list);
	} //End main

} 