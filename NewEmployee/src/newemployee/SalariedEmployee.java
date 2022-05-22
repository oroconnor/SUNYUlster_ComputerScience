/* CSC 150 Assignment 7 - Owen O'Connor
 * Class for salaried employees
 */

package newemployee;

public class SalariedEmployee extends Employee {
	private double annualSalary;
	private String type = "Salaried Employee";
	
	//constructor
	public SalariedEmployee(String name, int employee_id, String title, double annualSalary) {
		super(name, employee_id, title);
		
		
		this.annualSalary = annualSalary;
	}
	
	
	//salary getter
	public double getAnnualSalary() {
		return annualSalary;
	}
	
	//salary setter
	public void setAnnualSalary(double newSalary) {
		this.annualSalary = newSalary;
	}
	
	@Override
	public double getCompensation() {
		return getAnnualSalary();
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	/* give employees a raise, input raise amount as a decimal: 10% raise would be .1 */
	@Override
	public void giveRaise(double raise) {
		double newSalary = this.getAnnualSalary() * (1 + raise);
		this.setAnnualSalary(newSalary);
	}
	
}
