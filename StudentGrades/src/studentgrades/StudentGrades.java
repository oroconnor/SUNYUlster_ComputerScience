// programming assignment 4 - Owen O'Connor

package studentgrades;

import java.util.Scanner;

public class StudentGrades {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);

		int total = 0; //will keep a running total of all the grades inputted
		int counter = 0; //will keep track of the number of grades inputted
		
		System.out.println("Welcome to the Gradebook for CSC150 Intro to Computer Science");
		//using some code from class example "Class Average" to create a sentinel controlled loop
		System.out.print("Enter a grade or -1 to quit: ");
		int grade = input.nextInt();  
		
		while (grade != -1) {            
			total = total + grade;        
			counter++;                    
			System.out.print("Enter a grade or -1 to quit: ");
			grade = input.nextInt();
		}
			
		double average = (double) total / counter; //computes grade average
		
		System.out.printf("Average Grade is:%.2f%n",average);
		
		//determining the letter grade based on the grade average
		String lettergrade; 
		if (average > 92.45) {
			lettergrade = "A";
			}
		else if (average > 89.45) {
				lettergrade = "A-";
			}
		else if (average > 86.45) {			
				lettergrade = "B+";			
			}			
		else if (average > 82.45) {
				lettergrade = "B";			
				}					
		else if (average > 79.45) {
				lettergrade = "B-";
				}
		else if (average > 76.45) {			
				lettergrade = "C+";			
				}			
		else if (average > 72.45) {			
				lettergrade = "C";			
				}					
		else if (average > 69.45) {			
				lettergrade = "C-";			
				}					
		else if (average > 66.45) {
				lettergrade = "D+";
				}
		else if (average > 62.45) {			
				lettergrade = "D";			
				}			
		else if (average > 59.45) {			
				lettergrade = "D-";			
				}				
		else { 
			lettergrade = "F";
			}
						
		System.out.printf("Letter Grade is: %s", lettergrade);							
			
			
			
	}

		
}

