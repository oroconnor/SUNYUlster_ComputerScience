// Owen O'Connor
// CSC 201
// Assignment 8 

package doublelinkedseq;

import doublelinkedseq.DoubleNode;

public class DoubleLinkedSeqTest {

	public static void main(String[] args) {
		DoubleLinkedSeq sequence1 = new DoubleLinkedSeq();
		sequence1.addAfter(5.5);
		sequence1.addAfter(10.2);
		sequence1.addBefore(7.9);
		
		
		printSequence(sequence1);
		
		System.out.println(sequence1.size());
		
		DoubleLinkedSeq sequence2 = new DoubleLinkedSeq();
		sequence2.addAfter(4.4);
		sequence2.addAfter(3.3);
		sequence2.addAfter(6.6);
		printSequence(sequence2);
		
		sequence1.addAll(sequence2);
		printSequence(sequence1);
		
		DoubleLinkedSeq sequence3 = new DoubleLinkedSeq();
		sequence3 = sequence3.catenation(sequence1,sequence2);
		printSequence(sequence3);
		
		sequence3.start();
		sequence3.advance();
		sequence3.removeCurrent();
		printSequence(sequence3);
		
	}



/**
 * prints the double linked sequence
 * @param node
 */
public static void printSequence(DoubleLinkedSeq sequence) {
// traverse list from head to tail
	
for (DoubleNode cursor = sequence.getHead();cursor!=null;cursor=cursor.getLink()) {
	System.out.printf("%.2f,  ", cursor.getData());
	}
System.out.println();
}
}