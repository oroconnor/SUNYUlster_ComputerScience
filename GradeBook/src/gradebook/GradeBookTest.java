package gradebook;

public class GradeBookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] gradesArray = {{87,96,70},
								{68,87,90},
								{94,100,90}};
		
	
		GradeBook myGradeBook = new GradeBook("CSC 150", gradesArray);
		System.out.printf("Lowest Grade in the Class: %d%n", myGradeBook.getMinimum());	
	
	}
}

