/* CSC 150 Assignment 7 - Owen O'Connor
 * Class for hourly employees
 */

package newemployee;

public class HourlyEmployee extends Employee {
	private double hourlyWage;
	private String type = "Hourly Employee";
	
	//constructor
	public HourlyEmployee(String name, int employee_id, String title, double hourlyWage) {
		super(name, employee_id, title);
	
		this.hourlyWage = hourlyWage;
		}
		
		//wage getter
		public double getHourlyWage() {
			return hourlyWage;
		}
		
		//wage setter
		public void setHourlyWage(double newWage) {
			this.hourlyWage = newWage;
		}
		
		@Override
		public double getCompensation() {
			double annualCompensation = hourlyWage * 40 * 52;
			return (annualCompensation);
		}
		
		@Override
		public String getType() {
			return type;
		}
		
		/* give employees a raise, input raise amount as a decimal: 10% raise would be .1 */
		@Override
		public void giveRaise(double raise) {
			double newHourlyWage = this.getHourlyWage() * (1 + raise);
			this.setHourlyWage(newHourlyWage);
		}
		
	}