// Owen O'Connor
// CSC 201
// Assignment 13
// 10/29/21


package checkout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * Simulation of grocery line and checkout lanes. 3 regular lanes and 1 express lane.
 * @author owenoconnor
 * @since 10/29/21
 */
public class CheckOut {
	
	   public static void main(String[ ] args) {
		   ShoppingQueue initialQueue = new ShoppingQueue();  
		   initialQueue.add(new Shopper("Doc", 10));
		   initialQueue.add(new Shopper("Happy", 150));
		   initialQueue.add(new Shopper("Sleepy", 25));
		   initialQueue.add(new Shopper("Bashful", 75));
		   initialQueue.add(new Shopper("Sneezy", 10));
		   initialQueue.add(new Shopper("Dopey", 45));
		   initialQueue.add(new Shopper("Grumpy", 1));
		   
		   ShoppingQueue squeue = new ShoppingQueue();  
		   ShoppingQueue expressQueue = new ShoppingQueue(15);  

		   //  anybody from the initial line who has 15 or less items gets funnelled into
		   // the express lane in order
		   while (!initialQueue.isEmpty()) {
			   Shopper t = initialQueue.pop();
			   if (t.getItems() <= 15) {
				   expressQueue.add(t);
			   }
			   else squeue.add(t);
		   }
		   
		   ShoppingLane lane1 = new ShoppingLane();
		   ShoppingLane lane2 = new ShoppingLane();
		   ShoppingLane lane3 = new ShoppingLane();
		   ShoppingLane expressLane = new ShoppingLane(15);
		   
		   ArrayList<Shopper> postQueue = new ArrayList<Shopper>();
		   ArrayList<Shopper> allDone = new ArrayList<Shopper>();
		   
		   //Line simulation
		   int elapsedSecs = 0;

		   while (!squeue.isEmpty() || !expressQueue.isEmpty() || lane1.isBusy() || lane2.isBusy( ) ||lane3.isBusy() 
				   || expressLane.isBusy()) {
			  if (!squeue.isEmpty()) {
				  

			   //check the lanes to see if they are busy, and then populate them
			   if (!lane1.isBusy()) { // if lane 1 is open
				   Shopper t = squeue.pop();
				lane1.setItems(t.getItems()); // remove someone from the queue and start them on the line
				postQueue.add(t);
			   } // End lane1 check
			   
			   if (!lane2.isBusy()) { // if lane 2 is open
				   Shopper t = squeue.pop();
				lane2.setItems(t.getItems()); // remove someone from the queue and start them on the line
				postQueue.add(t);
			   } // End lane2 check
			   
			   if (!lane3.isBusy()) { // if lane 3 is open
				   Shopper t = squeue.pop();
				lane3.setItems(t.getItems()); // remove someone from the queue and start them on the line
				postQueue.add(t);
			   } // End lane3 check
			  } 
			  
			  if (!expressQueue.isEmpty()) {
			   if (!expressLane.isBusy()) { //if express lane is open
			   Shopper s = expressQueue.pop();
			expressLane.setItems(s.getItems()); // remove someone from the queue and start them on the line
			postQueue.add(s);
		   } // End lane1 check
			  }
			  

			   for (int i = 0; i < postQueue.size(); i++) {
				   postQueue.get(i).reduceItems();  // since they are in line, reduce their number of items
				   postQueue.get(i).incrementWait();
			   }
			   for (int i = 0; i < squeue.getManyItems(); i++) {
				   squeue.getData()[i+squeue.getFront()].incrementWait();
			   }
			   for (int i = 0; i < expressQueue.getManyItems(); i++) {
				   expressQueue.getData()[i+expressQueue.getFront()].incrementWait();  
			   }
			   
			   lane1.reduceItems();
			   lane2.reduceItems();
			   lane3.reduceItems();
			   expressLane.reduceItems();
			   	   
			   elapsedSecs++;   
			   for (int i = 0; i < postQueue.size(); i++) {
				   if (postQueue.get(i).getItems() == 0) {
					   allDone.add(postQueue.get(i)); // add that shopper to the all done list
					   postQueue.remove(i); // remove them from the list of people checking out
					   
				   }
			   }
		   
	   } // End while loop

		   // print results of simulation
		   int waitTotal = 0;
		   
		   System.out.println("Shoppers and their wait times:");
		   for (int i = 0; i < allDone.size(); i++) {
			   System.out.println(allDone.get(i).getName());
			   System.out.println(allDone.get(i).getWaitTime());
			   waitTotal = waitTotal + allDone.get(i).getWaitTime();
		   }
		   
		   System.out.print("Total time: ");
		   System.out.println(elapsedSecs);
		   
		   int avgWait = waitTotal / allDone.size();
		   System.out.print("Average wait time: ");
		   System.out.println(avgWait);

		   } // End main
	     
	   } // End CheckOut class
	    
