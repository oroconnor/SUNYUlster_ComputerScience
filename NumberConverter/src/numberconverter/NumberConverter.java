//Owen O'Connor - CSC180

package numberconverter;

import java.util.InputMismatchException;
import java.util.Scanner;


/** @author sheehanj 
 *  adapted by @author owenoconnor
 *  Java Program to Convert Numbers from Binary, Hex, and Octal */
public class NumberConverter {

	/** Convert from Binary to Decimal 
	 *  @param BinaryNumber a String representing ones and zeros 
	 *  @throws InvalidNumberException Number is not Binary */
	public static int BinaryToDecimal(String BinaryNumber) {
		int result = 0;
		int power = BinaryNumber.length() - 1;
		int index = 0;
		while (index < BinaryNumber.length())
		   {
		   if (BinaryNumber.charAt(index)=='1')
		      result = result + 1 * (int) (Math.pow(2, power));
		   index++; // look at the next digit
		   power--; // change the power of 2 to next digit
		   }
		
		return result;
	}
	
	
	/** Convert from Decimal to Binary 
	 *  @param DecimalNumber an integer we wish to convert 
	 *  @throws InvalidNumberException Number is not an int */
	public static String DecimalToBinary (int DecimalNumber) {
		String result = "";
		
		// Calculate Largest Positional Value
		int position = 10;
		while (DecimalNumber / Math.pow(2, position) < 1) {
			position--;
		}
		
		while (position>=0)
			{
			if ( DecimalNumber / (int) Math.pow(2, position)==1)
			   result = result + "1";
			else
			   result = result + "0";
			DecimalNumber = DecimalNumber % (int) Math.pow(2, position);
			position--;
			}
		return result;
	}
	
	/** Convert from Decimal to Hex 
	 *  @param DecimalNumber an integer we wish to convert 
	 *  @throws InvalidNumberException Number is not an int */
	public static String DecimalToHex (int DecimalNumber) {
		String result = "";
		
		// Calculate Largest Positional Value
		int position = 10;
		while (DecimalNumber / Math.pow(16, position) < 1) {
			position--;
		}
		
		while (position>=0)
			{
			int quotient = DecimalNumber / (int) Math.pow(16, position);
			
			String q = "";
			switch (quotient) {
				case 10: q = "A"; break;
				case 11: q = "B"; break;
				case 12: q = "C"; break;
				case 13: q = "D"; break;
				case 14: q = "E"; break;
				case 15: q = "F"; break;
				default: q = Integer.toString(quotient);
			}
			result = result + q;
			DecimalNumber = DecimalNumber % (int) Math.pow(16, position);
			position--;
			
			}
		return result;
	}
	
	
	/** Convert from Decimal to Octal 
	 *  @param DecimalNumber an integer we wish to convert 
	 *  @throws InvalidNumberException Number is not an int */
	public static String DecimalToOctal (int DecimalNumber) {
		String result = "";
		
		// Calculate Largest Positional Value
		int position = 10;
		while (DecimalNumber / Math.pow(8, position) < 1) {
			position--;
		}
		
		while (position>=0)
			{
			int quotient = DecimalNumber / (int) Math.pow(8, position);
			result = result + Integer.toString(quotient);
			DecimalNumber = DecimalNumber % (int) Math.pow(8, position);
			position--;
			
			}
		return result;
	}
	
	/** Convert from Octal to Decimal 
	 *  @param OctalNumber a String representing a number with digits from 0 to 7 
	 *  @throws InvalidNumberException Number is not Octal */
	public static int OctalToDecimal(String OctalNumber) {
		int result = 0;
		int power = OctalNumber.length() - 1;
		int index = 0;
		while (index < OctalNumber.length())
		   {
		   switch (OctalNumber.charAt(index))
		   {
		      case '1': result = result + 1 * (int) (Math.pow(8, power)); break;
		      case '2': result = result + 2 * (int) (Math.pow(8, power)); break;
		      case '3': result = result + 3 * (int) (Math.pow(8, power)); break;
		      case '4': result = result + 4 * (int) (Math.pow(8, power)); break;
		      case '5': result = result + 5 * (int) (Math.pow(8, power)); break;
		      case '6': result = result + 6 * (int) (Math.pow(8, power)); break;
		      case '7': result = result + 7 * (int) (Math.pow(8, power)); break;
		   }
		   index++; // look at the next digit
		   power--; // change the power of 8 to next digit
		   }
		
		return result;
	}
	
	/** Convert from Hex to Decimal 
	 *  @param HexNumber a String representing a number with digits from 0-9 and A-F 
	 *  @throws InvalidNumberException Number is not Hex */
	public static int HexToDecimal(String HexNumber) {
		int result = 0;
		int power = HexNumber.length() - 1;
		int index = 0;
		HexNumber.toUpperCase();
		
		while (index < HexNumber.length())
		   {
		   switch (HexNumber.charAt(index))
		   {
		      case '1': result = result + 1 * (int) (Math.pow(16, power)); break;
		      case '2': result = result + 2 * (int) (Math.pow(16, power)); break;
		      case '3': result = result + 3 * (int) (Math.pow(16, power)); break;
		      case '4': result = result + 4 * (int) (Math.pow(16, power)); break;
		      case '5': result = result + 5 * (int) (Math.pow(16, power)); break;
		      case '6': result = result + 6 * (int) (Math.pow(16, power)); break;
		      case '7': result = result + 7 * (int) (Math.pow(16, power)); break;
		      case '8': result = result + 8 * (int) (Math.pow(16, power)); break;
		      case '9': result = result + 9 * (int) (Math.pow(16, power)); break;
		      
		      case 'A':
		      case 'a': result = result + 10 * (int) (Math.pow(16, power)); break;
		      case 'B':
		      case 'b': result = result + 11 * (int) (Math.pow(16, power)); break;
		      case 'C':
		      case 'c': result = result + 12 * (int) (Math.pow(16, power)); break;
		      case 'D':
		      case 'd': result = result + 13 * (int) (Math.pow(16, power)); break;
		      case 'E':
		      case 'e': result = result + 14 * (int) (Math.pow(16, power)); break;
		      case 'F':
		      case 'f': result = result + 15 * (int) (Math.pow(16, power)); break;
		   }
		   index++; // look at the next digit
		   power--; // change the power of ? to next digit
		   }
		
		return result;
	}
	
	/** Calls the conversion methods and prints out the results for Binary Input
	 *  @param BinaryNumber an integer inputted by the user to convert to the other number types */
	public static void BinaryConvert(String BinaryNumber) { 
		int decimal = BinaryToDecimal(BinaryNumber);
		String octal = DecimalToOctal(decimal);
		String hex = DecimalToHex(decimal);
		System.out.printf("Binary number %s converted is:%n", BinaryNumber);
		System.out.printf("%s Octal%n", octal);
		System.out.printf("%d Decimal%n", decimal);
		System.out.printf("%s Hex%n", hex);
	}
	
	/** Calls the conversion methods and prints out the results for Hex Input
	 *  @param HexNumber an integer inputted by the user to convert to the other number types */
	public static void HexConvert(String HexNumber) { 
		int decimal = HexToDecimal(HexNumber);
		String octal = DecimalToOctal(decimal);
		String binary = DecimalToBinary(decimal);
		System.out.printf("Hex number %s converted is:%n", HexNumber);
		System.out.printf("%s Octal%n", octal);
		System.out.printf("%d Decimal%n", decimal);
		System.out.printf("%s Binary%n", binary);
	}
	
	/** Calls the conversion methods and prints out the results for Octal Input
	 *  @param OCtalNumber an integer inputted by the user to convert to the other number types */
	public static void OctalConvert(String OctalNumber) { 
		int decimal = OctalToDecimal(OctalNumber);
		String hex = DecimalToHex(decimal);
		String binary = DecimalToBinary(decimal);
		System.out.printf("Octal number %s converted is:%n", OctalNumber);
		System.out.printf("%s Hex%n", hex);
		System.out.printf("%d Decimal%n", decimal);
		System.out.printf("%s Binary%n", binary);
	}
	
	/** Calls the conversion methods and prints out the results for Decimal Input
	 *  @param DecimalNumber an integer inputted by the user to convert to the other number types */
	public static void DecimalConvert(String DecimalNumber) { 
		int decimal = Integer.parseInt(DecimalNumber);
		String binary = DecimalToBinary(decimal);
		String octal = DecimalToOctal(decimal);
		String hex = DecimalToHex(decimal);
		System.out.printf("Decimal number %s converted is:%n", DecimalNumber);
		System.out.printf("%s Binary%n", binary);
		System.out.printf("%s Octal%n", octal);
		System.out.printf("%s Hex%n", hex);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Gives the user input options and validates input
		//and then initiates the conversions and prints results
		
		boolean contSelectionLoop = true;
		int response = 0;
		do {
			try 
			{ 	//Menu option for which type of number to start with 
				Scanner input = new Scanner(System.in);
				System.out.println("What type of number would you like convert?:");
				System.out.printf("%n1)Binary\n2)Hex\n3)Octal\n4)Decimal\n");
				response = input.nextInt();
				if (response < 1 || response > 4) throw new NumberNotInRange();
				contSelectionLoop = false;
				
				
			}
			catch (InputMismatchException inputMismatchException) {
				//System.err.printf("%nException: %s%n", inputMismatchException);
				System.out.printf("You must enter integers.  Please try again.%n%n");
			}

			catch (NumberNotInRange e) {
				//System.err.printf("%nException: %s%n", NumberNotFound);
				System.out.printf("Your selection was not found.  Please try again.%n%n");
			}

			
		} while (contSelectionLoop);
		
		
		String num = null;
		int number = 0;
		
		boolean contNumberLoop = true;
		do {
			try 
			{ 	//inputting the actual number to convert to the other number types
				Scanner input = new Scanner(System.in);
				if (response == 2) { //by passes integer and positive validation for hex inputs
					System.out.println("Great. Please enter a hex integer that you wish to convert:");
					num = input.next();
					contNumberLoop = false;
					break;
				}
				//validates for postive integers
				System.out.println("Please enter an integer that you wish to convert:");
				number = input.nextInt();
				if ((t < 0 ||  t > 24)) throw new NumberNotInRange();
				
				contNumberLoop = false;
			}
			catch (InputMismatchException inputMismatchException) {
				//System.err.printf("%nException: %s%n", inputMismatchException);
				System.out.printf("You must enter integers.  Please try again.%n%n");
			}

			catch (NumberNotInRange e) {
				//System.err.printf("%nException: %s%n", NumberNotFound);
				System.out.printf("\"Input must an integer between 0-23. Please enter the Military Time hour as an integer from 0 to 23:%n%n");
			}

			
		} while (contNumberLoop);
		
		
		//selects which version of the calculations to run based on number type chosen
		switch (response)	{
			case 1: BinaryConvert(Integer.toString(number)); break;
			case 2: HexConvert(num); break;
			case 3: OctalConvert(Integer.toString(number)); break;
			case 4: DecimalConvert(Integer.toString(number)); break;
			
		}
			
		
	}

}


