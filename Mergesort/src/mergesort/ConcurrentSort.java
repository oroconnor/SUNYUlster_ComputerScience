// Owen O'Connor
// CSC 201 
// Assignment 21
// 11-23-21

// modifying example code given with assignment


package mergesort;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Random;

/**
 * Generates array for random numbers, sorts the array with a concurrent sort method, 
 * and displays the time in ms it took to complete. 
 * @author owenoconnor
 * @since 11-23-21
 */
public class ConcurrentSort
{
   public static void main(String[ ] args)
   {
   
	 // get current time in ms
	 long StartDateTime = new Date().getTime();
	// System.out.println("Start Time in milliseconds: " + StartDateTime);
		   
		   
	   
      final int SIZE = 1000000;   // Size of the array to sort
      int[] data = new int[SIZE]; // The array
      int i;                       // Array index
      boolean isSorted;            // To check that array is sorted
      Random rng = new Random( );

      // Fill the array with random numbers
      for (i = 0; i < SIZE; i++)
      {
	  data[i] = rng.nextInt(SIZE);
      }

      // Sort the array
      SortWorker.sortWithNThreads(data, 0, SIZE, 4);
    
      // Check that it's sorted:
      isSorted = true;
      for (i = 1; i < SIZE; i++)
      {
          if (data[i-1] > data[i])
	  {
	     System.out.println(i);
             isSorted = false;
          }
      }
      System.out.println("Result of isSorted: " + isSorted);
      
   // compute time when finished in ms
 	 long EndDateTime = new Date().getTime();
 	// System.out.println("End Time in milliseconds: " + EndDateTime); 
  
 	 System.out.println("Time taken: " + (EndDateTime-StartDateTime) + "ms");
      
   } // End main

}

class SortWorker implements Callable<Boolean>
{
   int[] data;
   int fromIndex, toIndex;
   int manyThreads;

   public SortWorker(int[] data, int fromIndex, int toIndex, int manyThreads)
   {
       this.data = data;
       this.fromIndex = fromIndex;
       this.toIndex = toIndex;
       this.manyThreads = manyThreads;
   }

   public static boolean sortWithNThreads(int[] data, int fromIndex, int toIndex, int n)
   {
      SortWorker worker = new SortWorker(data, fromIndex, toIndex, n);
      return worker.call( );
   }

   public Boolean call( )
   {
      int size, i0, i1, i2;
      ExecutorService executor;
      LinkedList<SortWorker> workers;

      if (manyThreads >= 2)
      {
         size = (toIndex - fromIndex);
         i0 = fromIndex;
         i1 = i0 + size/2;
         i2 = toIndex;
         executor = Executors.newFixedThreadPool(2);
         workers = new LinkedList<SortWorker>();

         workers.add(new SortWorker(data, i0, i1, manyThreads/2));
         workers.add(new SortWorker(data, i1, i2, manyThreads-manyThreads/2));
         try
         {
            executor.invokeAll(workers);
         }
         catch (InterruptedException e)
         {   // Someone has requested an early shutdown
             // 1. Stop the running threads:
             executor.shutdownNow( );
             // 2. If anyone calls isInterrupted for this thread,
             // this will ensure that they get a true answer:
	     Thread.currentThread( ).interrupt( );
             // 3. End this function:
             return false;
         }
         executor.shutdown( );

         merge(data, i0, i1-i0, i2-i1);           
      }
      else
      {
         Arrays.sort(data, fromIndex, toIndex);
      }
      return true;
   }

   public static void merge(int[] data, int first, int n1, int n2)
   {
      double[] temp = new double[n1+n2]; // Allocate the temporary array
      int copied  = 0; // Number of elements copied from data to temp
      int copied1 = 0; // Number copied from the first half of data
      int copied2 = 0; // Number copied from the second half of data
      int i;           // Array index to copy from temp back into data

      // Merge elements, copying from two halves of data to the temporary array.
      while ((copied1 < n1) && (copied2 < n2))
      {
         if (data[first + copied1] < data[first + n1 + copied2])
            temp[copied++] = data[first + (copied1++)];
         else
            temp[copied++] = data[first + n1 + (copied2++)];
      }

      // Copy any remaining entries in the left and right subarrays.
      while (copied1 < n1)
         temp[copied++] = data[first + (copied1++)];
      while (copied2 < n2)
         temp[copied++] = data[first + n1 + (copied2++)];

      // Copy from temp back to the data array.
      for (i = 0; i < n1+n2; i++)
         data[first + i] = (int) temp[i];
   }
}