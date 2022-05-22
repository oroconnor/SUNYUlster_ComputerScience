package passarray;

public class PassArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,2,3,4,5};
		
		for (int value : array)
			System.out.printf("%d ", value);
		System.out.println();
		
		modifyArray(array);
		
		for (int value : array)
			System.out.printf("%d ", value);
	}
	
	public static void modifyArray(int[] array2) {
		for (int counter = 0; counter < array2.length; counter++)  {
			array2[counter] *= 2;
		}
	}
	
}













