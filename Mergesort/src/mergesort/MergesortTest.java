// Owen O'Connor
// CSC 201 
// Assignment 21
// 11-23-21

// modifying example code given with assignment


package mergesort;

// import mergesort.Mergesort;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Date;

/**
 * Generates array for random numbers, sorts the array with a mergesort method, 
 * and displays the time in ms it took to complete. 
 * @author owenoconnor
 * @since 11-23-21
 */
public class MergesortTest {

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
      mergesort(data, 0, SIZE);
    
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
   } // End Main
   
   
   
   /**
   * Sort an array of integers from smallest to largest, using a merge sort
   * algorithm.
   * @param <CODE>data</CODE>
   *   the array to be sorted
   * @param <CODE>first</CODE> 
   *   the start index for the portion of the array that will be sorted
   * @param <CODE>n</CODE>
   *   the number of elements to sort
   * <dt><b>Precondition:</b><dd>
   *   <CODE>data[first]</CODE> through <CODE>data[first+n-1]</CODE> are valid
   *   parts of the array.
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>n</CODE> is zero or negative then no work is done. Otherwise, 
   *   the elements of </CODE>data</CODE> have been rearranged so that 
   *   <CODE>data[first] &lt= data[first+1] &lt= ... &lt= data[first+n-1]</CODE>.
   * @exception ArrayIndexOutOfBoundsException
   *   Indicates that <CODE>first+n-1</CODE> is an index beyond the end of the
   *   array.
   * */
   public static void mergesort(int[ ] data, int first, int n)
   {
      int n1; // Size of the first half of the array
      int n2; // Size of the second half of the array

      if (n > 1)
      {
         // Compute sizes of the two halves
         n1 = n / 2;
         n2 = n - n1;

         mergesort(data, first, n1);      // Sort data[first] through data[first+n1-1]
         mergesort(data, first + n1, n2); // Sort data[first+n1] to the end

         // Merge the two sorted halves.
         merge(data, first, n1, n2);
      }
   }  // End mergesort

   private static void merge(int[ ] data, int first, int n1, int n2)
   // Precondition: data has at least n1 + n2 components starting at data[first]. The first 
   // n1 elements (from data[first] to data[first + n1 � 1] are sorted from smallest 
   // to largest, and the last n2 (from data[first + n1] to data[first + n1 + n2 - 1]) are also
   // sorted from smallest to largest. 
   // Postcondition: Starting at data[first], n1 + n2 elements of data
   // have been rearranged to be sorted from smallest to largest.
   // Note: An OutOfMemoryError can be thrown if there is insufficient
   // memory for an array of n1+n2 ints.
   {
      int[ ] temp = new int[n1+n2]; // Allocate the temporary array
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
         data[first + i] = temp[i];
   } // End merge

   } // End Class

