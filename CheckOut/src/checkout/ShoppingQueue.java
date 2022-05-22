// Owen O'Connor
// CSC 201
// Assignment 13
// 10/29/21


package checkout;


import java.util.NoSuchElementException;

// This queue class is lifted from the textbook examples, and I'm leaving his javadoc comments. 
// I added a number of getter and setters that I needed to make my code work 
// and a print function. I also edited a couple of his functions.
// 

	/******************************************************************************
	* An <CODE>ArrayQueue&lt;E&gt;</CODE> is a queue of references to E objects.
	*
	* <b>Limitations:</b>
	*   
	*   (1) The capacity of one of these queues can change after it's created, but
	*   the maximum capacity is limited by the amount of free memory on the 
	*   machine. The constructor, <CODE>add</CODE>, <CODE>clone</CODE>, 
	*   and <CODE>union</CODE> will result in an 
	*   <CODE>OutOfMemoryError</CODE> when free memory is exhausted.
	*   
	*   (2) A queue's capacity cannot exceed the maximum integer 2,147,483,647
	*   (<CODE>Integer.MAX_VALUE</CODE>). Any attempt to create a larger capacity
	*   results in a failure due to an arithmetic overflow. 
	*
	* <b>Java Source Code for this class:</b>
	*   <A HREF="../../../../edu/colorado/collections/ArrayQueue.java">
	*   http://www.cs.colorado.edu/~main/edu/colorado/collections/ArrayQueue.java
	*   </A>
	*
	* @author Michael Main 
	*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
	*
	* @version Feb 10, 2016
	*
	* @see LinkedQueue
	******************************************************************************/
	public class ShoppingQueue<E> implements Cloneable
	{
	   // Invariant of the ArrayQueue<E> class:
	   //   1. The number of items in the queue is in the instance variable manyItems.
	   //   2. For a non-empty queue, the items are stored in a circular array
	   //      beginning at data[front] and continuing through data[rear].
	   //   3. For an empty queue, manyItems is zero and data is a reference to an
	   //      array, but we don't care about front and rear.
	   private Shopper[] data;
	  private int manyItems; 
	   private int front;
	   private int rear;
	   private int maxItems;

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
	   public ShoppingQueue( )
	   {
	      final int INITIAL_CAPACITY = 10;
	      setManyItems(0);
	      setData((Shopper[]) new Shopper[INITIAL_CAPACITY]);
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
	   public ShoppingQueue(int maxItems)
	   {
		   final int INITIAL_CAPACITY = 10;
	      if (maxItems < 0)
	         throw new IllegalArgumentException
	         ("maxItems is negative: " + maxItems);
	      setManyItems(0);
	      setData((Shopper[]) new Shopper[INITIAL_CAPACITY]);
	      setMaxItems(maxItems);
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
	   public ShoppingQueue<Shopper> clone( )       
	   {  // Clone an ArrayQueue.
		   ShoppingQueue<Shopper> answer;
	      
	      try
	      {
	         answer = (ShoppingQueue<Shopper>) super.clone( );
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
	      
	      answer.setData(getData().clone( ));
	      
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
	   public void add(Shopper item)
	   {
	      if (getManyItems() == getData().length)
	      {
	         // Double the capacity and add 1; this works even if manyItems is 0. However, in
	         // case that manyItems*2 + 1 is beyond Integer.MAX_VALUE, there will be an 
	         // arithmetic overflow and the bag will fail.
	         ensureCapacity(getManyItems()*2 + 1);
	      }
	      
	      if (getManyItems() == 0)
	      {
	         front = 0;
	         rear = 0;
	      }
	      else
	         rear = nextIndex(rear);
	         
	      data[rear] = item;
	      setManyItems(getManyItems() + 1);
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
		   Shopper[ ] biggerArray;
	      int n1, n2;
	      
	      if (getData().length >= minimumCapacity)
	         // No change needed.
	         return;
	      else if (getManyItems() == 0)
	         // Just increase the size of the array because the queue is empty.
	         setData((Shopper[]) new Object[minimumCapacity]);
	      else if (front <= rear)
	      {  // Create larger array and copy data[front]...data[rear] into it.
	         biggerArray = (Shopper[]) new Object[minimumCapacity];
	         System.arraycopy(getData(), front, biggerArray, front, getManyItems());
	         setData(biggerArray);
	      }
	      else
	      {  // Create a bigger array, but be careful about copying items into it. The queue items
	         // occur in two segments. The first segment goes from data[front] to the end of the 
	         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
	         // and n2 will be set to the number of items in these two segments. We will copy
	         // these segments to biggerArray[0...manyItems-1].
	         biggerArray = (Shopper[]) new Object[minimumCapacity];
	         n1 = getData().length - front;
	         n2 = rear + 1;
	         System.arraycopy(getData(), front, biggerArray, 0, n1);
	         System.arraycopy(getData(), 0, biggerArray, n1, n2);
	         front = 0;
	         rear = getManyItems()-1;  
	         setData(biggerArray);
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
	      return getData().length;
	   }


	   /**
	   * Determine whether this queue is empty.
	   * @return
	   *   <CODE>true</CODE> if this queue is empty;
	   *   <CODE>false</CODE> otherwise. 
	   **/
	   public boolean isEmpty( )
	   { 
	      if (getManyItems() == 0) {
	      return true;
	      }
	      else return false;
	   }


	   private int nextIndex(int i)
	   // Precondition: 0 <= i and i < data.length
	   // Postcondition: If i+1 is data.length, 
	   // then the return value is zero; otherwise
	   // the return value is i+1.
	   {
	      if (++i == getData().length)
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
	   public Shopper pop( )
	   {
		   Shopper answer;
	      
	      if (getManyItems() == 0)
	         throw new NoSuchElementException("Queue underflow");
	      answer = data[front];
	      front = nextIndex(front);
	      setManyItems(getManyItems() - 1);
	      return answer;
	   }
	   
	   
	   /**
	   * Accessor method to determine the number of items in this queue.
	   * @return
	   *   the number of items in this queue
	   **/ 
	   public int size( )   
	   {
	      return getManyItems();
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
		   Shopper[] trimmedArray;
	      int n1, n2;
	      
	      if (getData().length == getManyItems())
	         // No change needed.
	         return;
	      else if (getManyItems() == 0)
	         // Just change the size of the array to 0 because the queue is empty.
	         setData((Shopper[]) new Object[0]);
	      else if (front <= rear)
	      {  // Create trimmed array and copy data[front]...data[rear] into it.
	         trimmedArray = (Shopper[]) new Object[getManyItems()];
	         System.arraycopy(getData(), front, trimmedArray, front, getManyItems());
	         setData(trimmedArray);
	      }
	      else
	      {  // Create a trimmed array, but be careful about copying items into it. The queue items
	         // occur in two segments. The first segment goes from data[front] to the end of the 
	         // array, and the second segment goes from data[0] to data[rear]. The variables n1 
	         // and n2 will be set to the number of items in these two segments. We will copy
	         // these segments to trimmedArray[0...manyItems-1].
	         trimmedArray = (Shopper[]) new Object[getManyItems()];
	         n1 = getData().length - front;
	         n2 = rear + 1;
	         System.arraycopy(getData(), front, trimmedArray, 0, n1);
	         System.arraycopy(getData(), 0, trimmedArray, n1, n2);
	         front = 0;
	         rear = getManyItems()-1;  
	         setData(trimmedArray);
	      }
	   }


	public Shopper[] getData() {
		return data;
	}


	public void setData(Shopper[] data) {
		this.data = data;
	}


	public int getManyItems() {
		return manyItems;
	}


	public void setManyItems(int manyItems) {
		this.manyItems = manyItems;
	} 
	
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	} 
	
	public int getRear() {
		return rear;
	}
	
	public int getFront() {
		return front;
	}
	
	
	public void printQueue() {
		for (int i = 0; i < manyItems; i++) {
			System.out.println(data[i+getFront()].getName());
		}
	}
	
	
	}

