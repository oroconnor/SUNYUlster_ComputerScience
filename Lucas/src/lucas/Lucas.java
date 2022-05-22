//CSC 180 - EXAM 2 - Owen O'Connor
//inspired in part by our textbook's example on Fibonacci Series

package lucas;

/** @author owenoconnor
 *  @since 04/06/21
 *  Java Program for generating a list of Lucas numbers
 *  */
public class Lucas {

	/** Calculates the term of the Lucas sequence for a given index
	 * @param i the integer index of the sequence
	 */
	public static int lucas(int i) {
		//first base case
		if (i == 0) {
			return 2;
		}
		//second base case
		else if (i == 1) {
			return 1;
		}
		//recursion step
		else {
		return lucas(i-1) + lucas (i-2);
		}
	}
	
	/** Generates a list of Lucas numbers
	 */
	public static void main(String[] args) {
		//displays the Lucas number series from 0-29, for a total of 30 numbers
		int nums = 30; //adjustable if you want to generate more or less numbers in the sequence
		System.out.printf("%s%d%s%n","Here are the first ",nums," numbers of the Lucas Sequence:");
		for (int i = 0; i < nums; i++) { 
			System.out.printf("%d%n", lucas(i));
		}

	}

}
