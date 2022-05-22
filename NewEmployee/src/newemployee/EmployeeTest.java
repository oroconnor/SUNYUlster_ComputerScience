/* CSC 150 Assignment 7 - Owen O'Connor
 *Creates four employees, displays their info, gives them a raise, and then displays the info again.
 *
 *Fred Flintstone, Wilma Flintstone, Barney Rubble and Betty Rubble are from the Flintstones. Fred and Wilma's dinosaur is named "Dino." 
 *
 */
package newemployee;


public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create four employees and puts them in an array
		Employee fred = new HourlyEmployee("Fred",111, "Janitor",10);
		Employee wilma = new SalariedEmployee("Wilma",112, "Accountant",50000.0);
		Employee barney = new CommissionedEmployee("Barney",113,"Salesperson", .10, 500000);
		Employee betty = new CommissionedPlusBase("Betty",114,"Sales Manager", .05, 200000, 40000);
		
		Employee[] employees = {fred,wilma,barney,betty};
		
		//displays their info
		for (Employee employee : employees) {
			employee.display_info();
		}
		
		//gives them a 10% raise
		for (Employee employee : employees) {
			employee.giveRaise(.1);
			
		}
		System.out.printf("%nRaises have been given to all employees:%n%n");
		
		//displays their info again
		for (Employee employee : employees) {
		employee.display_info();
		}
	}
	
	
	
}

