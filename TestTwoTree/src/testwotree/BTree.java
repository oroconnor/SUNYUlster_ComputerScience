// Owen O'Connor
// CSC 205
// Exam #2 program

package testwotree;

import java.util.InputMismatchException;
import java.util.Scanner;

import testwotree.BTNode;

/**
 * Builds a binary search tree and then prints an inorder transversal to verify the structure of the tree.
 * Then allows user to search for an integer and prints the level at which the integer is found (if found)
 * @author owenoconnor
 * @since 11/15/21
 */
public class BTree {
	
	static int level = -1;

	public static void main(String[] args) {
		//Build left subtree
		BTNode<Integer> bottomleftChild = new BTNode<Integer>(1, null, null);
		BTNode<Integer> leftChild = new BTNode<Integer>(2, bottomleftChild, null);
		BTNode<Integer> rightChild = new BTNode<Integer>(4, null, null);
		BTNode<Integer> leftParent = new BTNode<Integer>(3, leftChild, rightChild);		
		
		//Build right subtree
		bottomleftChild = new BTNode<Integer>(21, null, null);	
		BTNode<Integer> bottomrightChild =  new BTNode<Integer>(34, null, null);	
		leftChild = new BTNode<Integer>(11, null, null);	
		rightChild =  new BTNode<Integer>(22, bottomleftChild, bottomrightChild);	
		BTNode<Integer> rightParent = new BTNode<Integer>(13, leftChild, rightChild);
		
		//Attach both subtrees to root
		BTNode<Integer>root = new BTNode<Integer>(5, leftParent, rightParent);
		
		//Print Binary Search Tree
		System.out.println("Printing contents of binary search tree using in-order traversal:");
		root.inorderPrint(); 
		System.out.println();
		System.out.println();
		
		// Searching Extra Credit

		BTNode<Integer> temp;
		
		
		Scanner input = new Scanner(System.in);
		try { 
			System.out.println("Enter an integer to search for:");
			int num = input.nextInt();
			
			temp = searchTree(num, root);
			
			if (temp != null) {
				System.out.printf("Integer %d was found!%n", num);
				System.out.printf("It was at level: %d", level);
				}
			else {

				System.out.printf("Integer %d was not found!%n", num);
				}
			}
		catch (InputMismatchException e) {
			System.out.println("OOPS! You should have entered an integer!");
		}
		
		
	} // End Main
	
	

	/**
	 * Searches the binary search tree
	 * @param i value to search for
	 * @param r root of the tree to search
	 * @return a BTNode, which will be null if the value was not found. 
	 */
	public static BTNode<Integer> searchTree(int i, BTNode<Integer> r)   
	{	
		level++;
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
	
	
}