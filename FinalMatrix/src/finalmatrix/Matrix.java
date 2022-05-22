//CSC180 - Final Exam - Owen O'Connor

package finalmatrix;

import java.util.concurrent.*;

/**
 * Program calculates multiplication of two matrices in two ways
 * @author owenoconnor
 * since 05/05/21
 * Code starter provided by Professor John Sheehan
 */
public class Matrix {
	/**
	 * Main program for matrix multiplication
	 * @param args
	 */
	public static void main(String[] args) {
		
		//number of rows and columns in the matrix
		int n = 2;
		
		int[][] a={{1,2},{3,4}}; 
		int[][] b={{5,6},{7,8}};
		int[][] c={{0,0},{0,0}};
		
		// Perform Matrix Multiplication Serially
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					c[i][j] = c[i][j] + a[i][k] * b[k][j];

		// display result
		for (int i=0; i < n; i++)
			for (int j=0; j < n; j++)
				System.out.printf("%d ",c[i][j]);
		System.out.println();

		// reset results
		c[0][0] = 0;
		c[0][1] = 0;
		c[1][0] = 0;
		c[1][1] = 0;
		
		// Below is doing the same operation with with concurrency
		
		// perform matrix multiplication for c using concurrency
		int product1 = 0;
		int product2 = 0;

		Multiply task1 = new Multiply(a[0][0],b[0][0],product1);
		Multiply task2 = new Multiply(a[0][1],b[1][0],product2);
		Multiply task3 = new Multiply(a[0][0],b[0][1],product1);
		Multiply task4 = new Multiply(a[0][1],b[1][1],product2);
		Multiply task5 = new Multiply(a[1][0],b[0][0],product1);
		Multiply task6 = new Multiply(a[1][1],b[1][0],product2);
		Multiply task7 = new Multiply(a[1][0],b[0][1],product1);
		Multiply task8 = new Multiply(a[1][1],b[1][1],product2);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future <Integer> producta  = executorService.submit(task1);
		Future <Integer> productb = executorService.submit(task2);
		Future <Integer> productc  = executorService.submit(task3);
		Future <Integer> productd = executorService.submit(task4);
		Future <Integer> producte  = executorService.submit(task5);
		Future <Integer> productf = executorService.submit(task6);
		Future <Integer> productg  = executorService.submit(task7);
		Future <Integer> producth = executorService.submit(task8);
		
		executorService.shutdown();
		
		// add together results computed concurrently
		try {
			boolean calcDone = executorService.awaitTermination(10, TimeUnit.SECONDS);
			if (calcDone) {	
				c[0][0] = producta.get() + productb.get();
				c[0][1] = productc.get() + productd.get();
				c[1][0] = producte.get() + productf.get();
				c[1][1] = productg.get() + producth.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		// Print out c matrix with the results of matrix multiplication
		System.out.printf("%d ",c[0][0]);
		System.out.printf("%d ",c[0][1]);
		System.out.printf("%d ",c[1][0]);
		System.out.printf("%d \n",c[1][1]);
		
	}
}