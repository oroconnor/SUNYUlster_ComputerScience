/* CSC 150 Assignment 7 - Owen O'Connor
 * Class for employees with a commission plus a base salary
 */

package newemployee;

public class CommissionedPlusBase extends CommissionedEmployee {
	private String type = "Commissioned Plus Base Employee";
	private double baseSalary; 
	
	//constructor
	public CommissionedPlusBase(String name, int employee_id, String title, double commissionRate, 
		double sales, double baseSalary) {
		super(name, employee_id, title, commissionRate, sales);

		this.setBaseSalary(baseSalary);		
		}

	//base getter	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	//base setter
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	@Override
	public double getCompensation() {
		double annualCompensation = super.getCompensation() + getBaseSalary();
		return (annualCompensation);
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	/* give employees a raise, input raise amount as a decimal: 10% raise would be .1 */
	@Override
	public void giveRaise(double raise) {
		double newCommissionRate = this.getCommissionRate() * (1 + raise);
		this.setCommissionRate(newCommissionRate);
		double newBaseSalary = this.getBaseSalary() * (1 + raise);
		this.setBaseSalary(newBaseSalary);
	}
	
	
	
}
