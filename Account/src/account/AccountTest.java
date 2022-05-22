package account;

import java.util.Scanner;

public class AccountTest {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter Account Holder Name");
		String theName = input.nextLine();

		//Create a Bank Account Object
		Account myAccount = new Account();
	
		//Set owner's name
		myAccount.setName(theName);
		
		//Display Owner's Name
		System.out.printf("Account Created for %s%n" ,  myAccount.getName());
	}

}
