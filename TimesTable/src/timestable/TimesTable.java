/* CSC 150 - Exam 2 - Owen O'Connor
 * Programming Portion
 */

package timestable;

public class TimesTable {
//creates a multiplication table
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Constants, could be updated to change size of table
		final int ROWS = 9;
		final int COLUMNS = 9;
		
		int[][]table = new int[ROWS][COLUMNS];
		
		//populates the values of the timestable
		for (int row = 0; row < table.length; row++) {
			for (int column = 0; column < table[row].length; column++) {
				table[row][column] = (row + 1)  * (column + 1);
			}
			
		}
		
		//prints header
		System.out.print("   ");
		for (int num : table[0]) {
			System.out.printf("%5d",  table[0][num-1]);
			}
		System.out.println();

		//prints dividing line, which adjusts to size of the table array
		System.out.print("---");
		for (int num : table[0]) {
			System.out.printf("%s", "-----");
			}
		System.out.println();
		
		//prints the times table
		for (int row = 0; row < table.length; row++) {
			System.out.printf("%d |",table[row][0]);
			for (int column = 0; column < table[row].length; column++) {
				System.out.printf("%5d",  table[row][column]);	
			}
			System.out.println();
		}

	}
}
