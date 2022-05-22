package randomintegers;

import java.security.SecureRandom;

public class RandomIntegers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecureRandom randomNumbers = new SecureRandom();
		
		int[][] results = new int [6][6]
		
				
		for (int counter = 1; counter <=600000;counter++) {
		
		//roll the dice
		int face = 1 + randomNumbers.nextInt(6);
		switch (face) {
			case 1: ones++; break;
			case 2: twos++; break;
			case 3: threes++; break;
			case 4: fours++; break;
			case 5: fives++; break;
			case 6: sixes++; break;
		}
		
		//System.out.printf("%d", face);
		}
		
		System.out.printf("%n1s = %d",ones);
		System.out.printf("%n2s = %d",twos);
		System.out.printf("%n3s = %d",threes);
		System.out.printf("%n4s = %d",fours);
		System.out.printf("%n5s = %d",fives);
		System.out.printf("%n6s = %d",sixes);
		
	}

}
