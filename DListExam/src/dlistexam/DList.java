
//Owen O'Connor
//CSC 201
//Exam 1
package dlistexam;

import dlistexam.DNode;

/** 
* @author owenoconnor
 * @param <E>
* @since 10/5/21
* enables a double linked list of DNodes
*/
public class DList<E> {
	DNode head;
	DNode tail;
	int size; // how many nodes in the list

	public DList() {
		head = null;
		tail = null;
		size = 0;
	}
	

	/**
	 * Add a new node after the specified node
	 * @param node the node that the new node will be after
	 * @param data the value of the new node
	 */
	public void addAfter(DNode node, E data) {
		DNode newNode = new DNode(data,node,node.getNext());
		node.getNext().setPrev(newNode);
		node.setNext(newNode);
	}
	/**
	 * Add a node to the tail of the DList
	 * @param data the value of the new node
	 * @return
	 */
	public DNode addToTail(E data) {
		/* case 1: List is empty */
		if (head==null) {
			DNode node = new DNode(data,null,null);
			head = node;
			tail = node;
			size++;
			return node;
		}
		else // list is not empty
		{
			DNode node = new DNode(data,tail,null);
			DNode previousNode = tail;
			previousNode.setNext(node);
			tail = node;
			return node;
		}
	} // End addToTail
	
	/**
	 * Add a node to the head of the DList
	 * @param data the value of the new node
	 * @return
	 */
	public DNode addToHead(E data) {
		/* case 1: List is empty */
		if (head==null) {
			DNode node = new DNode(data,null,null);
			head = node;
			tail = node;
			size++;
			return node;
		}
		else // list is not empty
		{
			DNode node = new DNode(data,null,head);
			DNode nextNode = head;
			nextNode.setPrev(node);
			node.setNext(nextNode);
			head = node;
			return node;
		}
	} // End addToHead
	
	
	/**
	 * Extra Credit: removes the node with instances of value in Dlist
	 * @param value the value to remove from the linked list
	 */
	public void removeNode(E value)  {
		//removes the node with instances of value in Dlist
		DNode cursor = head;
		DNode precursor = null;
		while (cursor!=null) {
			if (cursor.getData() == value) {
				if (cursor == head) {
					DNode next = cursor.getNext();
					head = next;
					next.setPrev(null);
				}
				else  if (cursor == tail) {
					DNode prev = cursor.getPrev();
					tail = prev;
					prev.setNext(null);
				}
				else {
				DNode prev = cursor.getPrev();
				DNode next = cursor.getNext();
				prev.setNext(next);
				next.setPrev(prev);
				}	
			}
			cursor = cursor.getNext();
		}
	} // End removeNode
		
	
}