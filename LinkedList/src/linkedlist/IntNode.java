// Owen O'Connor
// CSC 201 
// Assignment 6

package linkedlist;

/**
 * @author owenoconnor
 * @since 9/21/21 
 * Object class for use in LinkedList.java
 */
public class IntNode {
	private int data;
	private IntNode link;
	
	/**
	 * @param data - the value stored by that node
	 * @param link - points to the next node
	 * Constructor
	 */
	public IntNode(int data, IntNode link) {
		this.data = data;
		this.link = link;
	}
	/**
	 * @param element - the value to add
	 * @throws OutOfMemoryError
	 * adds a new node containing element after this node
	 */
	public void addNodeAfter(int element) {
		link = new IntNode(element, link);
	}
	
	/**
	 * @throws nullPointerException
	 * deletes the next node in the LinkedList
	 */
	public void removeNodeAfter() {
		link = link.link;
	}
	
	/**
	 * @return the value stored in the node
	 */
	public int getData() {
		return data;
	}
	/**
	 * @param data - a new value to be stored in this node
	 */
	public void setData(int data) {
		this.data = data;
	}
	
	/**
	 * @return the IntNode that this node points to 
	 */
	public IntNode getLink() {
		return link;
	}
	
	/**
	 * @param link a new IntNode for this node to point to
	 */
	public void setLink(IntNode link) {
		this.link = link;
	}

}