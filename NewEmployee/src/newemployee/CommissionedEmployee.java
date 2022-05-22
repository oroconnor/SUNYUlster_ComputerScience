/* CSC 150 Assignment 7 - Owen O'Connor
 * Class for pure commissioned employees
 */

package newemployee;

public class CommissionedEmployee extends Employee {
	private double commissionRate;
	private double sales;
	private String type = "Commissioned Employee";
	
	//constructor
	public CommissionedEmployee(String name, int employee_id, String title, double commissionRate, double sales) {
		super(name, employee_id, title);

		this.commissionRate = commissionRate;
		this.sales =sales;
		}
		
		//commission rate getter
		public double getCommissionRate() {
			return commissionRate;
		}
		
		//commission rate setter
		public void setCommissionRate(double newRate) {
			this.commissionRate = newRate;
		}
		
		@Override
		public double getCompensation() {
			double annualCompensation = sales * commissionRate;
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
		}
		
	}