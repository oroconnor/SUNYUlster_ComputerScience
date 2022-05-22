// Owen O'Connor
// CSC 205
// Assignment #16


package binarysearchtree;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Searches the binary search tree for a value, and confirms if it is present. If not present, 
 * shows the two closest integers. 
 * @author owenoconnor
 * @since 11/12/21
 */
public class BinarySearchTreeTest {

	static int nextmin = Integer.MIN_VALUE;
	static int nextmax = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		BTNode<Integer> root = beginningTree();
		BTNode<Integer> temp;
		
		Scanner input = new Scanner(System.in);
		try { 
			System.out.println("Enter an integer to search for:");
			int num = input.nextInt();
			
			temp = searchTree(num, root);
			
			if (temp != null) {
				System.out.printf("Integer %d was found!%n", num);
				}
			else {
				findNext(root, num);
				System.out.printf("Integer %d was not found!%n", num);
				System.out.printf("next lowest: ");
				if (nextmin == Integer.MIN_VALUE) {
					System.out.printf(" none%n");
				} else {
					System.out.printf(" %d%n", nextmin);
				}
				System.out.printf("next highest: ");
				if (nextmax== Integer.MAX_VALUE) {
					System.out.printf(" none%n");
				} else {
					System.out.printf(" %d%n", nextmax);
				}
				
				
				}
			}
		catch (InputMismatchException e) {
			System.out.println("OOPS! You should have entered an integer!");
		}
		
	 } // End Main


/**
 * Builds a binary search tree
 * @return the binary search tree root
 */
public static BTNode<Integer> beginningTree( )   {	
   //Building the tree from the bottom
   
   // Level 3
   BTNode<Integer> a = new BTNode<Integer>(4, null, null);
   BTNode<Integer> b = new BTNode<Integer>(7, null, null);
   BTNode<Integer> c = new BTNode<Integer>(13, null, null);
   
   // Level 2
   BTNode<Integer> d = new BTNode<Integer>(1, null, null);
   BTNode<Integer> e = new BTNode<Integer>(6, a, b);
   BTNode<Integer> f = new BTNode<Integer>(14, c, null);

   // Level 1
   BTNode<Integer> g = new BTNode<Integer>(3, d, e);
   BTNode<Integer> h = new BTNode<Integer>(10, null, f);
   
   // Level 0
   BTNode<Integer> root = new BTNode<Integer>(8, g, h);

   return root;
}

/**
 * Searches the binary search tree
 * @param i value to search for
 * @param r root of the tree to search
 * @return a BTNode, which will be null if the value was not found. 
 */
public static BTNode<Integer> searchTree(int i, BTNode<Integer> r)   
{	
	BTNode<Integer> n = new BTNode(null, null, null);
	
	if (r == null) {
		return r;
	}
		
	if (r.getData() == i) {
		return r;
	}
	if (i > r.getData()) {
		return searchTree(i, r.getRight());
		
	}
	if (i < r.getData()) {
		return searchTree(i, r.getLeft());
		}
		
	return n;
	
	}

/**
 * Steps through the tree and adjusts the values of the "nextmin" and "nextmax" values.
 * Based of an InOrder traversal example.
 * @param root root of tree to search
 * @param n the value that we are looking for the closest values for
 */
public static void findNext(BTNode<Integer>  root, int n ) {
	int num = n;
	
    if (root == null) 
        return; 
    // traverse left subtree 
    findNext(root.getLeft(), num); 
    
    if ((root.getData() < n) && ((root.getData() > nextmin) || ((Integer) nextmin == null))) {
    	nextmin = root.getData();
    	}
    if ((root.getData() > n) && ((root.getData() < nextmax) || ((Integer) nextmin == null))) {
    	nextmax = root.getData();
    	}

    // traverse right subtree 
    findNext(root.getRight(), num); 
	}



 
} // End class