//Owen O'Connor
//CSC201
//Assignment 5

package doublearrayseq;

// test program to try out DoubleArraySeq and look for problems
public class DoubleArraySeqExample {

	public static void main(String [ ] args)
	{
		DoubleArraySeq sequenceA = new DoubleArraySeq(20);
		DoubleArraySeq sequenceB = new DoubleArraySeq();
		DoubleArraySeq sequenceC = new DoubleArraySeq();

		// Testing addAfter, addBefore, and start() on sequenceA.  
		sequenceA.addAfter(34.1);
		System.out.println(sequenceA);
		sequenceA.addAfter(2.6);
		System.out.println(sequenceA.toString());
		sequenceA.addAfter(13.5);
		System.out.println(sequenceA.toString());
		sequenceA.start();	// at this point it should be {34.1, 2.6, 13.5}
		System.out.println(sequenceA.toString());
		sequenceA.addAfter(7.9);
		System.out.println(sequenceA.toString());
		sequenceA.addAfter(1.0);
		System.out.println(sequenceA.toString());
		
		System.out.println(sequenceA.toString()); // should be {34.1, 7.9, 1.0, 2.6, 13.5}
		System.out.println(sequenceA.getCapacity()); // should be 20
		
		// create two sequences to combine into one.
		sequenceB.addAfter(0.5);
		sequenceB.addAfter(0.8);
		sequenceB.addAfter(1.5);
		sequenceB.addAfter(1.8);
		sequenceB.addAfter(2.5);
		sequenceB.addAfter(2.8);
		System.out.println(sequenceB.toString()); 
		System.out.println(sequenceB.getCapacity()); // Should be 10
		
		sequenceC.addAfter(0.1);
		sequenceC.addAfter(0.2);
		sequenceC.addAfter(0.3);
		sequenceC.addAfter(0.4);
		sequenceC.addAfter(0.5);
		sequenceC.addAfter(0.6);
		System.out.println(sequenceC.toString());
		
		//adds sequenceC to the end of sequenceB
		sequenceB.addAll(sequenceC); // should be {0.5, 0.8, 1.5, 1.8, 2.5, 2.8, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6}
		System.out.println(sequenceB.toString());
		System.out.println(sequenceB.getCapacity()); // Should be 24
		
		
		DoubleArraySeq combinedSeq = DoubleArraySeq.catenation(sequenceA, sequenceC);
		System.out.println(combinedSeq.toString()); // combines sequenceA with sequenceC above
		
		sequenceA.removeCurrent(); // sequenceA's currentIndex should be 2 at this point.  data[2] = 1.0
		System.out.println(sequenceA.toString()); // should be {34.1, 7.9, 2.6, 13.5}
		
		sequenceC.removeCurrent(); // sequenceC's currentIndex should be 5, which is the last element (0.6).
		System.out.println(sequenceC.toString()); // should be {0.1, 0.2, 0.3, 0.4, 0.5}
		
		sequenceB.addAfter(3.3);
		System.out.println(sequenceB.size()); // should be 13
		System.out.println(sequenceB.toString()); 
		System.out.println(sequenceB.getCapacity()); // should be 24
		sequenceB.trimToSize(); 
		System.out.println(sequenceB.getCapacity()); // Should be 13
		System.out.println(sequenceB.toString());
	}

}