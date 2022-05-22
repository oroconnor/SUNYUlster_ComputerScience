// Owen O'Connor
// CSC 201
// Assignment 8 


package doublelinkedseq;

// Based on: DoubleLinkedSeq.java from the package edu.colorado.collections

/******************************************************************************
* This class is a homework assignment;
* A <CODE>DoubleLinkedSeq</CODE> is a collection of <CODE>double</CODE> numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the sequence class 
* (start, getCurrent, advance and isCurrent).
*
* <b>Limitations:</b>
*   Beyond <CODE>Int.MAX_VALUE</CODE> elements, the <CODE>size</CODE> method
*   does not work.
*
* <b>Note:</b>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version Feb 10, 2016
******************************************************************************/
public class DoubleLinkedSeq implements Cloneable
{
private DoubleNode head;
private DoubleNode tail;
private DoubleNode precursor;
private DoubleNode cursor;
private int manyNodes;   


/**
* Initialize an empty sequence. 
* <b>Postcondition:</b>
*   This sequence is empty.
**/   
public DoubleLinkedSeq( )
{
   head = null;
   tail = null;
   cursor = null;
   precursor = null;
   manyNodes = 0;
}
 

/**
* Add a new element to this sequence, after the current element. 
* @param element
*   the new element that is being added
* <b>Postcondition:</b>
*   A new copy of the element has been added to this sequence. If there was
*   a current element, then the new element is placed after the current
*   element. If there was no current element, then the new element is placed
*   at the end of the sequence. In all cases, the new element becomes the
*   new current element of this sequence. 
* @exception OutOfMemoryError
*   Indicates insufficient memory for a new node.
**/
public void addAfter(double element)
{
   // scenario 1 - no current element
   if (cursor == null) {
	   if (manyNodes == 0) { // empty sequence
		   head = new DoubleNode(element, null); 
		   cursor = head;
		   tail = head;
		   manyNodes++;
	   }
	   else { //  there could be no current element, but still nonempty Sequence
		   DoubleNode node = new DoubleNode(element, null);
		   precursor = tail;
		   tail.setLink(node);
		   tail = node;
		   cursor = tail;
		   manyNodes++;
	   }
   		}
   else /* list is not empty and there is a current value */
   	   {
	      DoubleNode node = new DoubleNode(element, cursor.getLink());
	      precursor = cursor;
	      cursor.setLink(node);
	      cursor = node;
	      if (node.getLink()==null)
	    	 tail = node;
	      manyNodes++;
       }
   
}

/**
* Add a new element to this sequence, before the current element. 
* @param element
*   the new element that is being added
* <b>Postcondition:</b>
*   A new copy of the element has been added to this sequence. If there was
*   a current element, then the new element is placed before the current
*   element. If there was no current element, then the new element is placed
*   at the start of the sequence. In all cases, the new element becomes the
*   new current element of this sequence. 
* @exception OutOfMemoryError
*   Indicates insufficient memory for a new node.
**/
public void addBefore(double element)
{
	if (!isCurrent()) {
		if (manyNodes == 0) { // no current and empty sequence
			 DoubleNode node = new DoubleNode(element, null);
			 head = node;
			 tail = head;
			 cursor = head;
			 manyNodes++;
		}
		else { // no current but sequence is not empty
			DoubleNode node = new DoubleNode(element, head);
			head = node;
			cursor = node;
			precursor = null;
			manyNodes++;
		}
	} 
	else {
			// there is current 
			if (cursor == head) { 
				DoubleNode node = new DoubleNode(element, head);	
				head = node;
				cursor = node;
				manyNodes++;
			} 
			else { 
				DoubleNode node = new DoubleNode(element, cursor);
				precursor.setLink(node);
				cursor = node;
				manyNodes++;
			}
	}
} // End addBefore()


/**
* Place the contents of another sequence at the end of this sequence.
* @param addend
*   a sequence whose contents will be placed at the end of this sequence
* <b>Precondition:</b>
*   The parameter, <CODE>addend</CODE>, is not null. 
* <b>Postcondition:</b>
*   The elements from <CODE>addend</CODE> have been placed at the end of 
*   this sequence. The current element of this sequence remains where it 
*   was, and the <CODE>addend</CODE> is also unchanged.
* @exception NullPointerException
*   Indicates that <CODE>addend</CODE> is null. 
* @exception OutOfMemoryError
*   Indicates insufficient memory to increase the size of this sequence.
**/
public void addAll(DoubleLinkedSeq addend)
{
	DoubleNode marker = addend.head;
   while (marker != null) {
	   tail.addNodeAfter(marker.getData());
	   marker = marker.getLink();
	   tail = tail.getLink();
   }
}   // End addAll


/**
* Move forward, so that the current element is now the next element in
* this sequence.
* <b>Precondition:</b>
*   <CODE>isCurrent()</CODE> returns true. 
* <b>Postcondition:</b>
*   If the current element was already the end element of this sequence 
*   (with nothing after it), then there is no longer any current element. 
*   Otherwise, the new element is the element immediately after the 
*   original current element.
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   <CODE>advance</CODE> may not be called.
**/
public void advance( )
{
   if (isCurrent()) {
	  precursor = cursor; 
      cursor = cursor.getLink();
      } 
   else {
	   throw(new IllegalStateException());
   }
} // End advance()


/**
* Generate a copy of this sequence.
* @return
*   The return value is a copy of this sequence. Subsequent changes to the
*   copy will not affect the original, nor vice versa. Note that the return
*   value must be type cast to a <CODE>DoubleLinkedSeq</CODE> before it can be used.
* @exception OutOfMemoryError
*   Indicates insufficient memory for creating the clone.
**/ 
public static Object clone(DoubleLinkedSeq template)
{  // Clone a DoubleLinkedSeq object.
	DoubleLinkedSeq copy;

    try
    {
    	copy = (DoubleLinkedSeq) template.clone( );
    }
    catch (CloneNotSupportedException e) {
       throw new RuntimeException
       ("This class does not implement Cloneable");
    }
    return copy;
} 


/**
* Create a new sequence that contains all the elements from one sequence
* followed by another.
* @param s1
*   the first of two sequences
* @param s2
*   the second of two sequences
* <b>Precondition:</b>
*   Neither s1 nor s2 is null.
* @return
*   a new sequence that has the elements of <CODE>s1</CODE> followed by the
*   elements of <CODE>s2</CODE> (with no current element)
* @exception NullPointerException
*   Indicates that one of the arguments is null.
* @exception OutOfMemoryError
*   Indicates insufficient memory for the new sequence.
**/   
public DoubleLinkedSeq catenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2)
{
	DoubleLinkedSeq cat = new DoubleLinkedSeq();
	cat = (DoubleLinkedSeq) clone(s1);
	cat.addAll(s2);
	return cat;
}


/**
* Accessor method to get the current element of this sequence. 
* <b>Precondition:</b>
*   <CODE>isCurrent()</CODE> returns true.
* @return
*   the current capacity of this sequence
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   <CODE>getCurrent</CODE> may not be called.
**/
public double getCurrent( )
{
    if (cursor!=null)
	   return cursor.getData();
    else
       throw(new IllegalStateException());
}


/**
* Accessor method to determine whether this sequence has a specified 
* current element that can be retrieved with the 
* <CODE>getCurrent</CODE> method. 
* @return
*   true (there is a current element) or false (there is no current element at the moment)
**/
public boolean isCurrent( )
{
   if (cursor != null) {
   return true;
   }
   else return false;
}
           
/**
* Remove the current element from this sequence.
* <b>Precondition:</b>
*   <CODE>isCurrent()</CODE> returns true.
* <b>Postcondition:</b>
*   The current element has been removed from this sequence, and the 
*   following element (if there is one) is now the new current element. 
*   If there was no following element, then there is now no current 
*   element.
* @exception IllegalStateException
*   Indicates that there is no current element, so 
*   <CODE>removeCurrent</CODE> may not be called. 
**/
public void removeCurrent( )
{
  if (isCurrent()) {
	  if (cursor.getLink() != null) {
		  precursor.setLink(cursor.getLink());
		  cursor = cursor.getLink();
		  manyNodes--;
	  	}
	  else {
		  precursor.setLink(null);
		  cursor = null;
		  manyNodes--;
	  }
  }
} // End removeCurrent
              

/**
* Determine the number of elements in this sequence.
* @return
*   the number of elements in this sequence
**/ 
public int size( )
{
   return manyNodes;
}


/**
* Set the current element at the front of this sequence.
* <b>Postcondition:</b>
*   The front element of this sequence is now the current element (but 
*   if this sequence has no elements at all, then there is no current 
*   element).
**/ 
public void start( )
{
    if (head!=null)
	   cursor = head;	
}


public DoubleNode getHead() {
	return head;
}
}
        