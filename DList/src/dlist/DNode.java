// Owen O'Connor
// CSC 201
// Assignment 7

package dlist;

// Doubly Linked List Example

/** 
 * @author owenoconnor
 * @since 9/24/21
 * DNodes for use in Dlist double linked list
 */
public class DNode {
	private int data;
	private DNode prev; // points to the previous node in the DList
	private DNode next; // points to the next node in the DList
	
	public DNode(int data, DNode prev, DNode next) {
		this.setData(data);
		this.prev = prev;
		this.next = next;
	}
	
	/**
	 * getter for the backlink
	 * @return 
	 */
	public DNode getPrev() {
		return this.prev;
	}
	
	/**
	 * setter for the backlink
	 * @param prev
	 */
	public void setPrev(DNode prev) {
		this.prev = prev;
	}
	
	/**
	 * setter for the forwardlink
	 * @param next
	 */
	public void setNext(DNode next) {
		this.next = next;
	}
	
	/**
	 * getter for the forwardlink
	 * @return
	 */
	public DNode getNext() {
		return this.next;
	}

	/**
	 * getter for the value of a node
	 * @return
	 */
	public int getData() {
		return data;
	}

	/**
	 * setter for the value of a node
	 * @param data
	 */
	public void setData(int data) {
		this.data = data;
	}
}