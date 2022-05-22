package twodimarray;

public class TwoDimArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] array1 = {{1,2,3},{4,5,6}};
		
		for (int row = 0; row<array1.length; row++) {
			for(int col = 0; col<3; col++) {
					System.out.printf("%d ", array1[row][col]);
			}		
		System.out.println();
		}
	}

}
