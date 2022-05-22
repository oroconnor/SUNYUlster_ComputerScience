//Owen O'Connor - CSC180

package stringsearch;

import java.util.Scanner;

/** @author owenoconnor
 *  @since 02/18/21 
 *  Java Program search the words of a sentence for a user inputted letter */
public class StringSearch {

	public static void main(String[] args) {
		// get user input for sentence and letter
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a sentence:");
		String sentence = input.nextLine();
		System.out.println("Please enter a letter:");
		String letter = input.nextLine();
		
		//split the sentence into array of words
		String [] tokens = sentence.split(" ");
		
		
		System.out.printf("Number of occurences of the letter %s in each word:%n",letter);
		
		int totalLetter = 0;
		for (String token : tokens)  {
			int counter = 0;
			for (int j = 0; j < token.length(); j++) {
				//checks each letter to see if its the same as the user's letter
				//and keeps count
				if (token.charAt(j) == letter.charAt(0)) {
					counter++;
				}
			}
			//maintains total count of letter for all the words
			totalLetter += counter;
			System.out.printf("%-20s--->%4d%n",token,counter);	
		}
		
		System.out.printf("Total number of words: %d%n",tokens.length);
		System.out.printf("Total number of occurences of the letter %s: %d",letter,totalLetter);
			
	}
		
}


