/* programming assignment #3 - Owen O'Connor */

package employee;

public class Employee {
	private String name;
	private int employee_id;
	private double salary;
	
	/* constructor */
	public Employee(String name, int employee_id, double salary) {
		this.name = name;
		this.employee_id = employee_id;
		this.salary = salary;
	}
	/*name setter */
	public void setName (String name) {
		this.name = name;
	}
	
	/*name getter */
	public String getName() {
		return name;
	}

	/*id setter */
	public void setId (int employee_id) {
		this.employee_id = employee_id;
	}
	
	/*id getter */
	public int getId() {
		return employee_id;
	}
	
	/*salary setter */
	public void setSalary (double salary) {
		this.salary = salary;
	}
	
	/*salary getter */
	public double getSalary() {
		return salary;
	}
	
	/* display employee info */
	public void display_info() {
	System.out.printf("Name: %s%n",  this.getName());
	System.out.printf("Employee Id: %d%n", this.getId());
	System.out.printf("Salary: $%,.2f%n%n",  this.getSalary());
	}
	
	/* give employees a raise, input raise amount as a decimal: 10% raise would be .1 */
	public void give_raise(double raise) {
		double newsal = this.getSalary() * (1 + raise);
		this.setSalary(newsal);
	}	
		
}


