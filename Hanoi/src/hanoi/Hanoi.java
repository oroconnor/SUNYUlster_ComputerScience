package hanoi;

import java.util.InputMismatchException;
import java.util.Scanner;

import hanoi.ObjectStack;

public class Hanoi {

	// create three peg stacks
	static ObjectStack a = new ObjectStack(100);
	static ObjectStack b = new ObjectStack(100);
	static ObjectStack c = new ObjectStack(100);
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try { 
		System.out.println("Enter an integer for the number of disks in Tower of Hanoi Simulation:");
		int n = input.nextInt();
		if (n > 0) {
		for (int i = n; i > 0; i--) { //add n number of disks to peg a
			a.push(i);				// in this case the "disks" are just integers
		}

		
		printStacks(a,b,c);
		
		solveHanoi(n,a,c,b);

		printStacks(a,b,c);
		}
		else {
			System.out.println("OOPS! You should have entered a POSITIVE integer!");
		}
		}
		catch (InputMismatchException e) {
			System.out.println("OOPS! You should have entered an integer!");
		}
		
		

		
		
		} // End main
	

	
		
		public static void solveHanoi (int n, ObjectStack source, ObjectStack target, ObjectStack temp) {
			//goal is to move disks from peg A to peg C without stack larger on top of smaller
			
			if (n == 1) { // base case
				target.push(source.pop());
				return;
			}
			
			//move (n -1) disks from peg A to peg B using peg C
			solveHanoi(n - 1, source, temp, target);
			printStacks(a,b,c);
			
			// move last disk from source peg to destination peg
			target.push(source.pop());
			
			printStacks(a,b,c);
			
			
//			// move (n-1) disks from peg B to peg C
			solveHanoi(n - 1, temp, target, source);
			//printStacks(a,b,c);
		
			
		} // End solveHanoi
		
	
		public static void printStacks (ObjectStack a, ObjectStack b, ObjectStack c) {
			// create three temp stacks
			ObjectStack d = new ObjectStack(100);
			ObjectStack e = new ObjectStack(100);
			ObjectStack f = new ObjectStack(100);
			
			System.out.println();
			int numrows = Math.max(a.size(), Math.max(b.size(),c.size()));
			System.out.println("peg A   peg B   peg C");
			System.out.println();
			
			int asize = a.size();
			int bsize = b.size();
			int csize = c.size();
			
			int acounter = a.size() -1;
			int bcounter = b.size() -1;
			int ccounter = c.size() -1;
			
			for (int i = numrows; i > 0; i--) {
				if (asize >= i) {
					System.out.printf("***%d    ", a.getData(acounter));
					acounter--;
				} else {
					System.out.print("        ");
				}	

				if (bsize >= i) {
					System.out.printf("***%d    ", b.getData(bcounter));
					bcounter--;
				} else {
					System.out.print("        ");
				}	
		
				if (c.size() >= i) {
					System.out.printf("***%d    ", c.getData(ccounter));
					ccounter--;
				} else {
					System.out.print("        ");
				}	
				
				System.out.println();
				
				}
			

			
		} // End printStacks
		
	
	
} // End Hanoi class





