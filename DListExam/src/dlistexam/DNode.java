// Owen O'Connor
// CSC 201
// Exam 1

package dlistexam;

// Doubly Linked List 

/** 
 * @author owenoconnor
 * @since 10/5/21
 * DNodes for use in Dlist double linked list
 */
public class DNode<E> {
	private E data;
	private DNode prev; // points to the previous node in the DList
	private DNode next; // points to the next node in the DList
	
	public DNode(E data, DNode prev, DNode next) {
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
	public E getData() {
		return (E) data;
	}

	/**
	 * setter for the value of a node
	 * @param data
	 */
	public void setData(E data) {
		this.data =  data;
	}
}