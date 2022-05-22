/* CSC 150 Assignment 7 - Owen O'Connor
 * Abstract superclass for employee system
 */

package newemployee;

public abstract class Employee { 
	private final String name;
	private final int employee_id;
	private String title;

	
	//constructor
	public Employee(String name, int employee_id, String title) {
		this.name = name;
		this.employee_id = employee_id;
		this.setTitle(title);
	}

	//name getter 
	public String getName() {
		return name;
	}
	
	//id getter 
	public int getId() {
		return employee_id;
	}

	//title getter	
	public String getTitle() {
		return title;
	}

	//title setter
	public void setTitle(String title) {
		this.title = title;
	}

	// display employee info 
	public void display_info() {
	System.out.printf("Name: %s%n",  this.getName());
	System.out.printf("Employee Id: %d%n", this.getId());
	System.out.printf("Employee Type: %s%n",  this.getType());
	System.out.printf("Title: %s%n",  this.getTitle());
	System.out.printf("Compensation: $%,.2f%n%n",  this.getCompensation());
	}
	
	//abstract methods to be overridden by subclassses:
	public abstract double getCompensation(); 
	
	public abstract String getType();

	/* give employees a raise, input raise amount as a decimal: 10% raise would be .1 */
	public abstract void giveRaise(double raise);
	










}
