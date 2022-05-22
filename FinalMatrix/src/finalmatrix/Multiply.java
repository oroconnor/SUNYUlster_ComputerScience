//CSC180 - Final Exam - Owen O'Connor

package finalmatrix;

import java.util.concurrent.Callable;

/**
 * Thread to calculate the product of two integers
 * @author owenoconnor
 * since 05/05/21
 * Code provided by Professor John Sheehan
 */
public class Multiply implements Callable<Integer>{

	private final int a;
	private final int b;
	
	public Multiply(int a, int b, int result)
	{
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Integer call() throws Exception {
		return a*b;
	}
	
}