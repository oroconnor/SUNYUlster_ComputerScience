// Owen O'Connor
// CSC 201
// Assignment 12 

// Based off of:
// File: ArrayQueue.java from the package edu.colorado.collections
// Complete documentation is available from the ArrayQueue link in:
//   http://www.cs.colorado.edu/~main/docs/

package queuedemo;
import java.util.NoSuchElementException;

/** 
 * Queue class that takes generic objects
 * @author Michael Main primary
 * @author owenoconnor edits for assignement
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Cloneable
{
   // Invariant of the ArrayQueue<E> class:
   //   1. The number of items in the queue is in the instance variable manyItems.
   //   2. For a non-empty queue, the items are stored in a circular array
   //      beginning at data[front] and continuing through data[rear].
   //   3. For an empty queue, manyItems is zero and data is a reference to an
   //      array, but we don't care about front and rear.
   private E[ ] data;
   private int manyItems; 
   private int front;
   private int rear;

   /**
   * Initialize an empty queue with an initial capacity of 10.  Note that the
   * <CODE>add</CODE> method works efficiently (without needing more
   * memory) until this capacity is reached.
   * <b>Postcondition:</b>
   *   This queue is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   <CODE>new Object[10]</CODE>.
   **/   
   public ArrayQueue( )
   {
      final int INITIAL_CAPACITY = 3; // I set initial capacity to 3
      manyItems = 0;
      data = (E[]) new Object[INITIAL_CAPACITY];
      // We don't care about front and rear for an empty queue.
   }

   
   /**
   * Initialize an empty queue with a specified initial capacity. Note that the
   * <CODE>add</CODE> method works efficiently (without needing more
   * memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this queue
   * <b>Precondition:</b>
   *   <CODE>initialCapacity</CODE> is non-negative.
   * <b>Postcondition:</b>
   *   This queue is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for:
   *   <CODE>new Object[initialCapacity]</CODE>.
   **/   
   public ArrayQueue(int initialCapacity)
   {
      if (initialCapacity < 0)
         throw new IllegalArgumentException
         ("initialCapacity is negative: " + initialCapacity);
      manyItems = 0;
      data = (E[]) new Object[initialCapacity];
      // We don't care about front and rear for an empty queue.
   }

   
   /**
   * Generate a copy of this queue.
   * @return
   *   The return value is a copy of this queue. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>ArrayQueue</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public ArrayQueue<E> clone( )       
   {  // Clone an ArrayQueue.
      ArrayQueue<E> answer;
      
      try
      {
         answer = (ArrayQueue<E>) super.clone( );
      }
      catch (CloneNotSupportedException e)
      { 
         // This exception should not occur. But if it does, it would probably indicate a
         // programming error that made super.clone unavailable. The most comon error
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
     }
      
      answer.data = data.clone( );
      
      return answer;
   }        

     
   /**
   * Insert a new item in this queue.  If the addition
   * would take this queue beyond its current capacity, then the capacity is 
   * increased before adding the new item. The new item may be the null
   * reference.
   * @param item
   *   the item to be pushed onto this queue 
   * <b>Postcondition:</b>
   *   The item has been pushed onto this queue.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the queue's capacity.
   * <b>Note:</b>
   *   An attempt to increase the capacity beyond
   *   <CODE>Integer.MAX_VALUE</CODE> will cause the queue to fail with an
   *   arithmetic overflow.
   **/    
   public void push(E item)
   {
      if (manyItems == data.length)
      {
         // Double the capacity and add 1; this works even if manyItems is 0. However, in
         // case that manyItems*2 + 1 is beyond Integer.MAX_VALUE, there will be an 
         // arithmetic overflow and the bag will fail.
        // ensureCapacity(manyItems*2 + 1); 
    	System.out.println("Overflow!");   // handling overflow condition
    	  
      }
      else {
	      if (manyItems == 0)
	      {
	         front = 0;
	         rear = 0;
	      }
	      else
	         rear = nextIndex(rear);
	         
	      data[rear] = item;
	      manyItems++;
	      System.out.printf("Element %d is pushed to Queue!%n", item);
	      printq(); // displays queue contents
      }
   }
              
   
   /**
   * Change the current capacity of this queue.
   * @param minimumCapacity
   *   the new capacity for this queue
   * <b>Postcondition:</b>
   *   This queue's capacity has been changed to at least <CODE>minimumCapacity</CODE>.
   *   If the capacity was already at or greater than <CODE>minimumCapacity</CODE>,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: <CODE>new Object[minimumCapacity]</CODE>. 
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      E[ ] biggerArray;
      int n1, n2;
      
      if (data.length >= minimumCapacity)
         // No change needed.
         return;
      else if (manyItems == 0)
         // Just increase the size of the array because the queue is empty.
         data = (E[]) new Object[minimumCapacity];
      else if (front <= rear)
      {  // Create larger array and copy data[front]...data[rear] into it.
         biggerArray = (E[]) new Object[minimumCapacity];
         System.arraycopy(data, front, biggerArray, front, manyItems);
         data = biggerArray;
      }
      else
      {  // Create a bigger array, but be careful about copying items into it. The queue items
         // occur in two segments. The first segment goes from data[front] to the end of the 
         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
         // and n2 will be set to the number of items in these two segments. We will copy
         // these segments to biggerArray[0...manyItems-1].
         biggerArray = (E[]) new Object[minimumCapacity];
         n1 = data.length - front;
         n2 = rear + 1;
         System.arraycopy(data, front, biggerArray, 0, n1);
         System.arraycopy(data, 0, biggerArray, n1, n2);
         front = 0;
         rear = manyItems-1;  
         data = biggerArray;
      }
   }


   /**
   * Accessor method to get the current capacity of this queue. 
   * The <CODE>add</CODE> method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @return
   *   the current capacity of this queue
   **/
   public int getCapacity( )   
   {
      return data.length;
   }


   /**
   * Determine whether this queue is empty.
   * @return
   *   <CODE>true</CODE> if this queue is empty;
   *   <CODE>false</CODE> otherwise. 
   **/
   public boolean isEmpty( )
   {
      return (manyItems == 0);
   }


   private int nextIndex(int i)
   // Precondition: 0 <= i and i < data.length
   // Postcondition: If i+1 is data.length, 
   // then the return value is zero; otherwise
   // the return value is i+1.
   {
      if (++i == data.length)
         return 0;
      else
         return i;
   }

       
   /**
   * Get the front item, removing it from this queue.
   * <b>Precondition:</b>
   *   This queue is not empty.
   * @return
   *   The return value is the front item of this queue, and the item has
   *   been removed.
   * @exception NoSuchElementException
   *   Indicates that this queue is empty.
   **/    
   public E pop() {
      E answer = null;
      
      if (manyItems == 0) {
         System.out.println("Underflow"); // handling underflow condition
      }
      else {
	      answer = data[front];
	      front = nextIndex(front);
	      manyItems--;
	      System.out.println("Pop operation done!");
	      printq(); // displays queue contents
      }
      return answer;
   }
   
   
   /**
   * Accessor method to determine the number of items in this queue.
   * @return
   *   the number of items in this queue
   **/ 
   public int size( )   
   {
      return manyItems;
   }

   
   /**
   * Reduce the current capacity of this queue to its actual size (i.e., the
   * number of items it contains).
   * <b>Postcondition:</b>
   *   This queue's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/   
   public void trimToSize( )
   {
      E[] trimmedArray;
      int n1, n2;
      
      if (data.length == manyItems)
         // No change needed.
         return;
      else if (manyItems == 0)
         // Just change the size of the array to 0 because the queue is empty.
         data = (E[]) new Object[0];
      else if (front <= rear)
      {  // Create trimmed array and copy data[front]...data[rear] into it.
         trimmedArray = (E[]) new Object[manyItems];
         System.arraycopy(data, front, trimmedArray, front, manyItems);
         data = trimmedArray;
      }
      else
      {  // Create a trimmed array, but be careful about copying items into it. The queue items
         // occur in two segments. The first segment goes from data[front] to the end of the 
         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
         // and n2 will be set to the number of items in these two segments. We will copy
         // these segments to trimmedArray[0...manyItems-1].
         trimmedArray = (E[]) new Object[manyItems];
         n1 = data.length - front;
         n2 = rear + 1;
         System.arraycopy(data, front, trimmedArray, 0, n1);
         System.arraycopy(data, 0, trimmedArray, n1, n2);
         front = 0;
         rear = manyItems-1;  
         data = trimmedArray;
      }
   } // End trimToSize()
   
   // I added print method
   public void printq() { 
	   System.out.println("Elements in Queue:");
	   for (int i = front; i < (front+manyItems); i++) {
		   System.out.println(data[i]);
	   }
   }
   
   
}

