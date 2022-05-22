package classaverage;

import java.util.Scanner;

public class ClassAverage {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int total = 0; //sum of all the grades
		int counter = 1; //count of number of grades
		
		while (counter<=10) {	//repeats loop 10 times
			System.out.print("Enter a grade: ");;
			int grade = input.nextInt(); //user enters a grade
			total = total + grade; // keep a total of all grades
			counter = counter + 1; //add one to the counter
		}
		
		int average = total/10;
		System.out.printf("Average of all grades: %d%n", average);
		
	}

}
