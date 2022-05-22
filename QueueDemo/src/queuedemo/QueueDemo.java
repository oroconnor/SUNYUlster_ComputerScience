// Owen O'Connor
// CSC 201
// Assignment 12 

package queuedemo;

/**
 * Performs test of Queue as specified in assignment 12
 * @author owenoconnor
 * @since 10/23/21
 */
public class QueueDemo {
	
	public static void main(String[] args) {
		
		ArrayQueue queueDemo = new ArrayQueue();
		queueDemo.pop();
		queueDemo.push(23);
		queueDemo.push(2);
		queueDemo.push(73);
		queueDemo.push(21);
		queueDemo.pop();
		queueDemo.pop();
		queueDemo.pop();
		queueDemo.pop();
	} // End main

}
