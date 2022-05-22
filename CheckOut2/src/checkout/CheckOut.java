package checkout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class CheckOut {


	   public static void main(String[ ] args)
	   {
	     

		   
		   
		   ShoppingQueue squeue = new ShoppingQueue();  

		   squeue.add(new Shopper("Doc", 10));
		   squeue.add(new Shopper("Happy", 150));
		   squeue.add(new Shopper("Sleepy", 25));
		   squeue.add(new Shopper("Bashful", 75));
		   squeue.add(new Shopper("Sneezy", 10));
		   squeue.add(new Shopper("Dopey", 45));
		   squeue.add(new Shopper("Grumpy", 1));
		   

		   
		   
		   ShoppingLane lane1 = new ShoppingLane();
		   ShoppingLane lane2 = new ShoppingLane();
		   ShoppingLane lane3 = new ShoppingLane();
		  ShoppingLane expressLane = new ShoppingLane(15);
		   
		   ArrayList<Shopper> postQueue = new ArrayList<Shopper>();
		   ArrayList<Shopper> allDone = new ArrayList<Shopper>();
		   
		   //Line simulation
		   int elapsedSecs = 0;

		   while (!squeue.isEmpty() || lane1.isBusy() || lane2.isBusy( ) ||lane3.isBusy() 
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

			  

			   for (int i = 0; i < postQueue.size(); i++) {
				   //System.out.printf("postQueue.size():%d", postQueue.size());
				  
				   postQueue.get(i).reduceItems();  // since they are in line, reduce their number of items
				   postQueue.get(i).incrementWait();
				 //  System.out.println(postQueue.get(i).getName());
			//	 System.out.println(postQueue.get(i).getWaitTime());
			   }
			   for (int i = 0; i < squeue.getManyItems(); i++) {
				   
				   squeue.getData()[i+squeue.getFront()].incrementWait();
				   
			   }
			   
			   lane1.reduceItems();
			   lane2.reduceItems();
			   lane3.reduceItems();
			   expressLane.reduceItems();
			   	   
			   elapsedSecs++;   
			   for (int i = 0; i < postQueue.size(); i++) {
				   if (postQueue.get(i).getItems() == 0) {
				//	   System.out.println("test");
					   allDone.add(postQueue.get(i)); // add that shopper to the all done list
					   //System.out.println(postQueue.get(i).getName());
					   //System.out.println(postQueue.get(i).getWaitTime());
					   postQueue.remove(i); // remove them from the list of people checking out
					   
				   }
			   }
		   
	   } // End while loop

		   // print results of simulation
		   System.out.println("all done");
		   for (int i = 0; i < allDone.size(); i++) {
			   System.out.println(allDone.get(i).getName());
			   System.out.println(allDone.get(i).getWaitTime());
			   System.out.println(allDone.get(i).getItems());
			   
		   }
		   System.out.println("post queue");
		   for (int i = 0; i < postQueue.size(); i++) {
			   
			   System.out.println(postQueue.get(i).getName());
			   System.out.println(postQueue.get(i).getWaitTime());
			   System.out.println(postQueue.get(i).getItems());
			   
		   }
		   
		   System.out.println("total time");
		   System.out.println(elapsedSecs);

		   } // End main

		   
	     
	   } // End CheckOut class
	    
