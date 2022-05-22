//CSC180 - Final Exam Extra Credit - Owen O'Connor 

package finalmatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import java.security.SecureRandom;
/** Final Exam Extra Credit
 * Program that generates two matrices of size (n x n) and multiplies them. 
 * @author owenoconnor
 * since 05/05/21
 */
public class NMatrix {
//program built for multiplying matrices of (n x n) size, with both matrices having equal size
/**
 * Main program
 * @param args
 */
	public static void main(String[] args) {
		
		// Number of rows and columns in the matrix
		int n = 10; // Can be adjusted to see program calculate different sizes
	
		int[][] a = new int[n][n];
		int[][] b = new int[n][n];
		int[][] c = new int[n][n]; // Results matrix
		
		// Populate the two matrices that are to be multiplied
		SecureRandom randomNumbers = new SecureRandom();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = randomNumbers.nextInt(10);
				b[i][j] = randomNumbers.nextInt(10);
			}
		}
		
		List<Multiply> mList = new ArrayList<Multiply>();
		int product1 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					Multiply task = new Multiply(a[i][k],b[k][j],product1);
					mList.add(task);
					}
				}
			}
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
	
		ArrayList<Future<Integer>> products = new ArrayList<Future<Integer>>();
	
		for (int i = 0; i < mList.size(); i++) {
			Future<Integer> p = executorService.submit(mList.get(i));
			products.add(p);
		}
		executorService.shutdown();
		
		/* Once all multiplication threads have finished, add the results to get
		the matrix multiplication results */ 
		try {
			boolean calcDone = executorService.awaitTermination(10, TimeUnit.SECONDS);
			if (calcDone) {	
				int index = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						int sumprod = 0;
						for (int k = 0; k < n; k++) {
							sumprod += products.get(index).get();
							index++;
						}
						c[i][j] = sumprod;
					}
				
			}
			} // End calcDone loop
		} // End try block
		catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		//prints out matrix a
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			System.out.printf("%d ", a[i][j]);
			} 
			System.out.println();
			}
		System.out.println();
		
		//prints out matrix b
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			System.out.printf("%d ", b[i][j]);
			} 
			System.out.println();
			}
		System.out.println();

		//prints out array c - the matrix product
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
			System.out.printf("%d ", c[i][j]);
			} 
			System.out.println();
			}
		
} //End main
} // End class
