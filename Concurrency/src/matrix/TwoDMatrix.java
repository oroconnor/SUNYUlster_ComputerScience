//CSC 180 Assignment 11 - Owen O'Connor
//2 Dimensional Array version

package matrix;

import java.util.concurrent.*;
import java.util.ArrayList;

/** 
 * calculates the dot product of two 2D arrays in two different ways
 * @author owenoconnor
 * @since 04/25/21	
 */
public class TwoDMatrix {
	/**
	 * main program for concurrency assignment - 2D array version
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int[][] a = {{1,2,3},{4,5,6}};
		int[][] b = {{7,8},{9,10},{11,12}};

		ArrayList<Integer> pdotarray = new ArrayList<Integer>();	
		
		///looping through the two 2D arrays to calculate dot products one at a time
		for (int arow=0; arow<2; arow++) {
			for (int bcolumn=0; bcolumn < 2; bcolumn++) {
				int result = 0;
				for (int acolumn=0; acolumn<3; acolumn++) {
					result = result + a[arow][acolumn]*b[acolumn][bcolumn];
				}
				pdotarray.add(result);
			}			
		}
		
		System.out.printf("%s%n", "Procedural Result:");
		System.out.printf("%s %d  %d%n  %d %d %s","[",pdotarray.get(0),pdotarray.get(1),
				pdotarray.get(2),pdotarray.get(3),"]");

		
		//Concurrent example below
		ArrayList<Integer>cdotarray = new ArrayList<Integer>();	
		
		// create 12 Mult objects to run as threads
		Mult m1 = new Mult(a[0][0],b[0][0]);
		Mult m2 = new Mult(a[0][1],b[1][0]);
		Mult m3 = new Mult(a[0][2],b[2][0]);
		
		Mult m4 = new Mult(a[0][0],b[0][1]);
		Mult m5 = new Mult(a[0][1],b[1][1]);
		Mult m6 = new Mult(a[0][2],b[2][1]);
		
		Mult m7 = new Mult(a[1][0],b[0][0]);
		Mult m8 = new Mult(a[1][1],b[1][0]);
		Mult m9 = new Mult(a[1][2],b[2][0]);
		
		Mult m10 = new Mult(a[1][0],b[0][1]);
		Mult m11 = new Mult(a[1][1],b[1][1]);
		Mult m12 = new Mult(a[1][2],b[2][1]);
	
		// creates a thread pool
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		executorService.execute(m1);
		executorService.execute(m2);
		executorService.execute(m3);
		executorService.execute(m4);
		executorService.execute(m5);
		executorService.execute(m6);
		executorService.execute(m7);
		executorService.execute(m8);
		executorService.execute(m9);
		executorService.execute(m10);
		executorService.execute(m11);
		executorService.execute(m12);
		
		executorService.shutdown();
		
		try {
			boolean calcDone = executorService.awaitTermination(10, TimeUnit.SECONDS);
		
			if (calcDone) {
				// when threads finish we have all the results
				// now add them together to produce the final answer
				cdotarray.add(m1.getResult() + m2.getResult() + m3.getResult()); 
				cdotarray.add(m4.getResult()+ m5.getResult() + m6.getResult()); 
				cdotarray.add(m7.getResult()+ m8.getResult()+ m9.getResult()); 
				cdotarray.add(m10.getResult() + m11.getResult() + m12.getResult()); 
				
				executorService.shutdown();
			}
		} //end of try block
		catch (InterruptedException e) { }
		
		System.out.printf("%n%n%s%n", "Concurrency Result:");
		System.out.printf("%s %d  %d%n  %d %d %s","[",cdotarray.get(0),cdotarray.get(1),
				cdotarray.get(2),cdotarray.get(3),"]");
		
	}
} //end of class TwoDMatrix