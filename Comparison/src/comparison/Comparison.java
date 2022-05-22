package comparison;
// programming assignment #2 CSC150 - Owen O'Connor

import java.util.Scanner;

public class Comparison {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//take in three inputs from user
		System.out.printf("Enter first Integer:");
		int number1 = input.nextInt();
		System.out.printf("Enter second Integer:");
		int number2 = input.nextInt();
		System.out.printf("Enter third Integer:");
		int number3 = input.nextInt();
		
		//evaluate for largest integer
		int maxnum = number1;
		if (number2 > maxnum)
			maxnum = number2;
		if (number3 > maxnum)
			maxnum = number3;
		
		System.out.printf("%d was the largest integer entered!\n",maxnum);
		
		//evaluate for whether any integers were entered multiple times
		if (number1 == number2 && number2 == number3)
			System.out.printf("%d was entered more than once.\n%d was entered three times!",number1,number1);
		else if (number1 == number2 || number1 == number3)
				System.out.printf("%d was entered more than once.\n%d was entered two times!",number1,number1);
		else if (number2 == number3)
				System.out.printf("%d was entered more than once.\n%d was entered two times!",number2,number2);

		input.close();
	
	}

}
