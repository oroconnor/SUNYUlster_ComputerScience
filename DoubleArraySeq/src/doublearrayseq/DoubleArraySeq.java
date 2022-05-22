//Owen O'Connor
//CSC201
//Assignment 5

package doublearrayseq;


// Based on: DoubleArraySeq.java from the package edu.colorado.collections
// Javadoc provided in example code


/******************************************************************************
* A DoubleArraySeq is a collection of double numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the sequence class 
* (start, getCurrent, advance and isCurrent).
*
* @note
*   (1) The capacity of one a sequence can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the 
*   machine. The constructor, addAfter, 
*   addBefore, clone, 
*   and concatenation will result in an
*   OutOfMemoryError when free memory is exhausted.
*   <p>
*   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow. 
*
* @note
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @see
*   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
*   </A>
*
* @version Feb 10, 2016
******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
// Invariant of the DoubleArraySeq class:
//   1. The number of elements in the seqeunces is in the instance variable 
//      manyItems.
//   2. For an empty sequence (with no elements), we do not care what is 
//      stored in any of data; for a non-empty sequence, the elements of the
//      sequence are stored in data[0] through data[manyItems-1], and we
//      don�t care what�s in the rest of data.
//   3. If there is a current element, then it lies in data[currentIndex];
//      if there is no current element, then currentIndex equals manyItems. 
private double[ ] data;
private int manyItems;
private int currentIndex; 

/**
* Initialize an empty sequence with an initial capacity of 10.  Note that
* the addAfter and addBefore methods work
* efficiently (without needing more memory) until this capacity is reached.
* @postcondition
*   This sequence is empty and has an initial capacity of 10.
* @exception OutOfMemoryError
*   Indicates insufficient memory for: 
*   new double[10].
**/   
public DoubleArraySeq( ){
   final int INITIAL_CAPACITY = 10; // Set default capacity
   data = new double[INITIAL_CAPACITY];
   manyItems = 0; // Sequence starts empty
   currentIndex = -1; 
}
  

/**
* Initialize an empty sequence with a specified initial capacity. Note that
* the addAfter and addBefore methods work
* efficiently (without needing more memory) until this capacity is reached.
* @param initialCapacity
*   the initial capacity of this sequence
* @precondition
*   initialCapacity is non-negative.
* @postcondition
*   This sequence is empty and has the given initial capacity.
* @exception IllegalArgumentException
*   Indicates that initialCapacity is negative.
* @exception OutOfMemoryError
*   Indicates insufficient memory for: 
*   new double[initialCapacity].
**/   
public DoubleArraySeq(int initialCapacity) {
	 data = new double[initialCapacity]; // Set initial capacity to integer argument
	 manyItems = 0; // Sequence starts empty
	 currentIndex = -1;
}
     

/**
* Add a new element to this sequence, after the current element. 
* If the new element would take this sequence beyond its current capacity,
* then the capacity is increased before adding the new element.
* @param element
*   the new element that is being added
* @postcondition
*   A new copy of the element has been added to this sequence. If there was
*   a current element, then the new element is placed after the current
*   element. If there was no current element, then the new element is placed
*   at the end of the sequence. In all cases, the new element becomes the
*   new current element of this sequence. 
* @exception OutOfMemoryError
*   Indicates insufficient memory for increasing the sequence's capacity.
* @note
*   An attempt to increase the capacity beyond
*   Integer.MAX_VALUE will cause the sequence to fail with an
*   arithmetic overflow.
**/
public void addAfter(double element) {
	if (data.length == manyItems) {
	ensureCapacity((manyItems+1)*2); // Make sure you have enough room to add 
	}
   // Scenario 1: currentIndex is the last item
   if (currentIndex == manyItems-1) {
	   currentIndex++;
	   data[currentIndex] = element;
	   manyItems++;
   }
   
   // scenario 2 sequence is empty
   else if (manyItems == 0) {
	   data[0] = element;
	   manyItems++;
	   currentIndex = 0;
   }
   
   // Scenario 3: Sequence is not empty, and currentIndex is the not the last item
   else {
	   for (int i = (manyItems - 1); i > currentIndex;i--) {
		   data[i+1] = data[i];
	   }	   
	   currentIndex++;
	   data[currentIndex] = element;
	   manyItems++;
   }
} // End addAfter


/**
* Add a new element to this sequence, before the current element. 
* If the new element would take this sequence beyond its current capacity,
* then the capacity is increased before adding the new element.
* @param element
*   the new element that is being added
* @postcondition
*   A new copy of the element has been added to this sequence. If there was
*   a current element, then the new element is placed before the current
*   element. If there was no current element, then the new element is placed
*   at the start of the sequence. In all cases, the new element becomes the
*   new current element of this sequence. 
* @exception OutOfMemoryError
*   Indicates insufficient memory for increasing the sequence's capacity.
* @note
*   An attempt to increase the capacity beyond
*   Integer.MAX_VALUE will cause the sequence to fail with an
*   arithmetic overflow.
**/
public void addBefore(int element) {
	if (data.length == manyItems) {
	ensureCapacity((manyItems+1)*2); // Make sure you have enough room to add 
	}
	 // Scenario 1: currentIndex is the last item
	   if (currentIndex == manyItems-1) {
		   data[manyItems] = data[currentIndex];
		   data[currentIndex] = element;
		   manyItems++;
		   // Don't need to change currentIndex
	   }
	   
	   // scenario 2 sequence is empty
	   else if (manyItems == 0) {
		   data[0] = element;
		   manyItems++;
		   currentIndex = 0;
	   }
	   
	   // Scenario 3: Sequence is not empty, and currentIndex is the not the last item
	   else {
		   for (int i = (manyItems - 1); i >= currentIndex;i--) {
			   data[i+1] = data[i];
		   }	   
		   // Don't need to change currentIndex
		   data[currentIndex] = element;
		   manyItems++;
	   }
} // End addBefore


/**
* Place the contents of another sequence at the end of this sequence.
* @param addend
*   a sequence whose contents will be placed at the end of this sequence
* @precondition
*   The parameter, addend, is not null. 
* @postcondition
*   The elements from addend have been placed at the end of 
*   this sequence. The current element of this sequence remains where it 
*   was, and the addend is also unchanged.
* @exception NullPointerException
*   Indicates that addend is null. 
* @exception OutOfMemoryError
*   Indicates insufficient memory to increase the size of this sequence.
* @note
*   An attempt to increase the capacity beyond
*   Integer.MAX_VALUE will cause an arithmetic overflow
*   that will cause the sequence to fail.
**/
public void addAll(DoubleArraySeq addend) {
	if (data.length <= (manyItems+addend.manyItems)) {
	ensureCapacity((manyItems+addend.manyItems)*2); // Make sure you have enough room to add 
	}
	for (int i = 0; i < addend.manyItems ; i++) {
		data[manyItems] = addend.data[i];
		manyItems++;
	}
   
}   


/**
* Move forward, so that the current element is now the next element in
* this sequence.
* @precondition
*   isCurrent() returns true. 
* @postcondition
*   If the current element was already the end element of this sequence 
*   (with nothing after it), then there is no longer any current element. 
*   Otherwise, the new element is the element immediately after the 
*   original current element.
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   advance may not be called.
**/
public void advance( ) {
	if (currentIndex == -1) { 
		throw (new IllegalStateException());
	}
	else if (currentIndex < manyItems-1) { // Unless the currentIndex is on the last item
	  currentIndex++;	// Move index to the next position
	}
	else { 
	   currentIndex = -1; // If currentIndex is on the last item, then make it so there is no current element
   }
}


/**
* Generate a copy of this sequence.
* @return
*   The return value is a copy of this sequence. Subsequent changes to the
*   copy will not affect the original, nor vice versa.
* @exception OutOfMemoryError
*   Indicates insufficient memory for creating the clone.
**/ 
public DoubleArraySeq clone( )
{  // Clone a DoubleArraySeq object.
   DoubleArraySeq answer;
   
   try
   {
      answer = (DoubleArraySeq) super.clone( );
   }
   catch (CloneNotSupportedException e)
   {  // This exception should not occur. But if it does, it would probably
      // indicate a programming error that made super.clone unavailable.
      // The most common error would be forgetting the "Implements Cloneable"
      // clause at the start of this class.
      throw new RuntimeException
      ("This class does not implement Cloneable");
   }
   
   answer.data = data.clone( );
   
   return answer;
}


/**
* Create a new sequence that contains all the elements from one sequence
* followed by another.
* @param s1
*   the first of two sequences
* @param s2
*   the second of two sequences
* @precondition
*   Neither s1 nor s2 is null.
* @return
*   a new sequence that has the elements of s1 followed by the
*   elements of s2 (with no current element)
* @exception NullPointerException
*   Indicates that one of the arguments is null.
* @exception OutOfMemoryError
*   Indicates insufficient memory for the new sequence.
* @note
*   An attempt to create a sequence with a capacity beyond
*   Integer.MAX_VALUE will cause an arithmetic overflow
*   that will cause the sequence to fail.
**/   
public static DoubleArraySeq catenation(DoubleArraySeq s1, DoubleArraySeq s2)
{
	 // If either s1 or s2 is null, then a NullPointerException is thrown. 
	 // In the case that the total number of items is beyond
	 // Integer.MAX_VALUE, there will be an arithmetic overflow and
	 // the bag will fail.   
	DoubleArraySeq answer = new DoubleArraySeq(s1.getCapacity( ) + s2.getCapacity( ));
	 
	 System.arraycopy(s1.data, 0, answer.data, 0, s1.manyItems);
	 System.arraycopy(s2.data, 0, answer.data, s1.manyItems, s2.manyItems);
	 answer.manyItems = s1.manyItems + s2.manyItems;
	 
	 return answer;
}


/**
* Change the current capacity of this sequence.
* @param minimumCapacity
*   the new capacity for this sequence
* @postcondition
*   This sequence's capacity has been changed to at least minimumCapacity.
*   If the capacity was already at or greater than minimumCapacity,
*   then the capacity is left unchanged.
* @exception OutOfMemoryError
*   Indicates insufficient memory for: new int[minimumCapacity].
**/
public void ensureCapacity(int minimumCapacity) {
	  double[ ] biggerArray;
	   
	   if (data.length < minimumCapacity)
	   {
	      biggerArray = new double[minimumCapacity];
	      System.arraycopy(data, 0, biggerArray, 0, manyItems);
	      data = biggerArray;
	   }
}

/**
* Accessor method to get the current capacity of this sequence. 
* The add method works efficiently (without needing
* more memory) until this capacity is reached.
* @return
*   the current capacity of this sequence
**/
public int getCapacity( )
{
	return data.length;
}


/**
* Accessor method to get the current element of this sequence. 
* @precondition
*   isCurrent() returns true.
* @return
*   the current element of this sequence
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   getCurrent may not be called.
**/
public double getCurrent( ) {
   double value = 0;
	
   if (isCurrent())
      value = data[currentIndex];
   else
	  throw (new IllegalStateException());
   return value;
}


/**
* Accessor method to determine whether this sequence has a specified 
* current element that can be retrieved with the 
* getCurrent method. 
* @return
*   true (there is a current element) or false (there is no current element at the moment)
**/
public boolean isCurrent( ) {
   if (currentIndex == -1)
	  return false;
   else
	  return true;
}
           
/**
* Remove the current element from this sequence.
* @precondition
*   isCurrent() returns true.
* @postcondition
*   The current element has been removed from this sequence, and the 
*   following element (if there is one) is now the new current element. 
*   If there was no following element, then there is now no current 
*   element.
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   removeCurrent may not be called. 
**/
public void removeCurrent( ) {
	if (currentIndex == -1) { 
		throw (new IllegalStateException());
	}
 // Scenario 1: currentIndex is the last item
   if (currentIndex == manyItems-1) { 
	   currentIndex = -1; // Per postconditions
	   manyItems--; 
	      }
   
   // Scenario 2: Empty sequence. Not allowed by preconditions.
   
   // Scenario 3: Sequence is not empty, and currentIndex is the not the last item
   else {
	   for (int i = currentIndex; i <= (manyItems - 1);i++) {
		   data[i] = data[i+1]; //shift data over one space, eliminating the current value
	   } 
	   manyItems--; // Don't need to change currentIndex
   }
} // End removeCurrent
              

/**
* Determine the number of elements in this sequence.
* @return
*   the number of elements in this sequence
**/ 
public int size( ) {
	return manyItems;
}


/**
* Set the current element at the front of this sequence.
* @postcondition
*   The front element of this sequence is now the current element (but 
*   if this sequence has no elements at all, then there is no current 
*   element).
**/ 
public void start( ) {
   if (manyItems>0) {
      currentIndex = 0;
   }
   else {
	   currentIndex = -1;
   }
}


/**
* Reduce the current capacity of this sequence to its actual size (i.e., the
* number of elements it contains).
* @postcondition
*   This sequence's capacity has been changed to its current size.
* @exception OutOfMemoryError
*   Indicates insufficient memory for altering the capacity. 
**/
public void trimToSize( )
{  
   double[ ] trimmedArray;
   
   if (data.length != manyItems)
   {
      trimmedArray = new double[manyItems];
      System.arraycopy(data, 0, trimmedArray, 0, manyItems);
      data = trimmedArray;
   }
} // End trimToSize
   
public String toString() {
	String s = "";
	for (int i=0; i<manyItems; i++) {
	    s = s + String.valueOf(data[i]) + ", ";
	}
	return s;
}


} // End class