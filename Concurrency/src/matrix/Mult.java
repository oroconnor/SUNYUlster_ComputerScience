//CSC 180 Assignment 11 - Owen O'Connor

package matrix;

import java.lang.Runnable;
/**
 * Multiplies its arguments, can be run concurrently 
 * @author owenoconnor
 * really by Professor John Sheehan... code unchanged
 * @since 04/25/21
 */
public class Mult implements Runnable {

	
	private int a; 
	private int b;
	private int result;
	
	public Mult(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public void run() {
		Multiply();
	}
	public void Multiply() {
		result = a*b;
	}
	public int getResult() {
		return result;
	}
}