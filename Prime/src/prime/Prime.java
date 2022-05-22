//CSC 150 - Programming Assignment 5 - Owen O'Connor

package prime;

import java.util.Scanner;

public class Prime {

	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		//just menu offering which version of the prime program the user wants to use
		while (true) {
		System.out.printf("%nDo you want to 1) test one potential prime number at a time? or 2) print all the prime numbers up to certain number?"
				+ "%nInput 1 or 2 for your choice or -1 to quit:"
				);
		int c = input.nextInt();  
		
	
		if (c == 1) 
			optionOne();
		else if ( c== 2)
			optionTwo();
		else if (c == -1)
			break;
		else
			System.out.print("Sorry, you did not enter a valid choice.%n");
		}
	}
	
	
	public static void optionOne() {
		//prompt user for input
		System.out.printf("%nEnter a positive integer and this program will tell you whether it is a prime number"
			+ "%nor enter -1 to return to menu: ");
		int n = input.nextInt();  
		
		//test for sentinel variable
		while (n!= -1) {	
			if (testPrime(n))
				System.out.printf("%d is prime!%n%n", n);
			else
				System.out.printf("%d is not prime!%n%n", n);
	
			System.out.print("Enter a positive integer or enter -1 to return to menu: ");
			n = input.nextInt();  
			}
		
		}

		public static void optionTwo() {
			//prompt user for input
			System.out.printf("%nEnter a positive integer and this program will print all the prime numbers from 2 up to and including that number"
				+ "%nor enter -1 to return to menu: ");
			int n = input.nextInt();  
			
			//test for sentinel variable
			while (n!= -1) {	
				for (int i = 2; i <= n; i++) {
					if (testPrime(i))
						System.out.printf("%d ", i);
				}
		
				System.out.printf("%nEnter a positive integer or enter -1 to return to menu: ");
				n = input.nextInt();  
				}	
		}
		
	public static boolean testPrime(int n) {		
		//tests if n is a prime number
		double sqroot = Math.sqrt(n);
		for (int i = 2; i <= sqroot; i++) {
			if (n % i == 0) {
				return false;
				}
		}
		return true;	
		

		}
}
