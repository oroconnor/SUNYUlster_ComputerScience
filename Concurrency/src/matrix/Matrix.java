//CSC 180 Assignment 11 - Owen O'Connor
//Note: Revised example supporting a 2 Dimensional Array in separate file: TwoDMatrix.java

package matrix;

import java.util.concurrent.*;

/** 
 * calculates the dot product of two 1D arrays in two different ways
 * @author owenoconnor
 * starter code provided by Professor John Sheehan
 * @since 04/25/21	
 */
public class Matrix {
	/**
	 * main program for concurrency assignment
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int[] a = {4, 4, 5, 1, 8, 10, 15, 1, 6, 8 };
		int[] b = {2, 7, 5, 4, 8, 12, 13, 1, 7, 8 };
		
		// Below is procedural version
		// multiply these two matrices 
		// up to a[9]*b[9]
		int result = 0;
		for (int i=0; i<a.length; i++)
			result = result + a[i]*b[i];
		System.out.printf("Procedural Result: %d%n", result);
		
		// Below is concurrency version
		// create 10 Mult objects to run as threads
		Mult m1 = new Mult(a[0],b[0]);
		Mult m2 = new Mult(a[1],b[1]);
		Mult m3 = new Mult(a[2],b[2]);
		Mult m4 = new Mult(a[3],b[3]);
		Mult m5 = new Mult(a[4],b[4]);
		Mult m6 = new Mult(a[5],b[5]);
		Mult m7 = new Mult(a[6],b[6]);
		Mult m8 = new Mult(a[7],b[7]);
		Mult m9 = new Mult(a[8],b[8]);
		Mult m10 = new Mult(a[9],b[9]);
		
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
		
		
		executorService.shutdown();
		
		try {
			boolean calcDone = executorService.awaitTermination(10, TimeUnit.SECONDS);
		
			
			if (calcDone) {
				// when threads finish we have all the results
				// now add them together to produce the final answer
				int result2 = m1.getResult() + m2.getResult() + m3.getResult()+ m4.getResult()+ m5.getResult() 
				+ m6.getResult()+ m7.getResult()+ m8.getResult()+ m9.getResult()+ m10.getResult(); 
				executorService.shutdown();
				System.out.printf("Concurrency Result: %d%n", result2);
			}
		}
		catch (InterruptedException e) { }
		
		}

}