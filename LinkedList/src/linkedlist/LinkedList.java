// Owen O'Connor
// CSC 201 
// Assignment 6

package linkedlist;

/**
 * @author owenoconnor
 * @since 9/21/21
 * implements a LinkedList composed of IntNodes
 */
public class LinkedList {
	private IntNode head;
	private IntNode tail;
	
	/**
	 * Constructor
	 */
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * @return the head IntNode
	 * getter
	 */
	public IntNode getHead() {
		return head;
	}
	
	/**
	 * @param head
	 * setter
	 */
	public void setHead(IntNode head) {
		this.head = head;
	}
	
	/**
	 * @return the tail IntNode
	 * getter
	 */
	public IntNode getTail() {
		return tail;
	}
	
	/**
	 * @param tail
	 * setter
	 */
	public void setTail(IntNode tail) {
		this.tail = tail;
	}
	
	/**
	 * 
	 * @param element
	 * @return the new node being created at the start of the LinkedList
	 */
	public IntNode addFirst(int element) {
		IntNode node = new IntNode(element, head);
		if (head==null)
		   this.tail = node;
		this.head = node;
		return node;
	}
	
	
	/** 
	 * @param value
	 * @param element
	 * @throws OutOfMemoryError
	 * Add a new node containing element before the first node containing value
	 */
	public void addNodeBefore(int value, int element) {
		IntNode cursor = head;
		IntNode precursor = null;
		while (cursor!=null) {
			if (cursor.getData() == value)
			   if (precursor==null)
				  addFirst(element);
			   else
			      precursor.addNodeAfter(element);
			precursor = cursor;
			cursor = cursor.getLink();
		    }
	}
	
/**
 * @param value - the value that will identify the IntNode after the one to be removed
 * remove the IntNode before the IntNode with the specified value from the LinkedList
 */
	public void removeNodeBefore(int value) {
		IntNode cursor = head;
		IntNode precursor = null;
		IntNode preprecursor = null;
		while (cursor!=null) {
			if (cursor.getData() == value) {
			   if (precursor!= null) {
				 if (preprecursor != null) {
					 preprecursor.setLink(cursor);
				 }
				 else {
					 head.setLink(cursor);
				 }
			   }
			}
			preprecursor = precursor;
			precursor = cursor;
			cursor = cursor.getLink();
		    }
		
	}
		
	/**
	 * Removes the head IntNode	
	 */
	public void removeHead() {
		if (head!=null)
		   head = head.getLink();
	}
	
	public String toString() {
		String result = "";
		IntNode cursor = head;
		while (cursor!=null) {
			if (result.equals(""))
				result = result + cursor.getData();
			else
			   result = result + "-->" + cursor.getData();
			cursor = cursor.getLink();
		}
		return result;
	}
}
