
package forexample;

public class ForExample {

	public static void main(String[] args) {
		
		
		double principal = 1000.0;
		double rate = 0.05;
		
		//adding a clean variable for the sake of my example
		double balance = 1000;
		
		System.out.printf("%s%20s%20s%n", "Year", "Amount from Class","Balance");
		
		for (int year = 1; year <= 45; year++) {	
			/*textbook calculation takes the original 1000 and uses Math.pow to 
			multiply itself by the (1+rate) for the number of years you want to calulate. 
			This won't work if the balance is changing because it generates each year's amount 
			by working off of the original principal and then going through the multiplication for all the years. 
			So this method overestimates the amount. 
			*/
			double amount = principal  * Math.pow(1.0 + rate, year);
			principal = principal + 1000; // deposit $1,000 more each year
			
			//adding in another operation that I believe models the compounding interest
			//this version makes the addition at the end of compounding period
			balance = balance * (1.0 + rate) + 1000;
			
			
			// display the year and amount
			System.out.printf("%4d%,20.2f%,20.2f%n", year, amount, balance);
		}
		
	}

}