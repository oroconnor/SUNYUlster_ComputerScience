/* programming assignment #3 - Owen O'Connor */

package employee;

public class EmployeeTest {
		
	public static void main(String[] args) {
		
		/* creating instances of employees */
		Employee sue = new Employee("Sue",111,70000.0);
		Employee mel = new Employee("Mel",112,76000.0);
		
		/* showing the employee's info */
		sue.display_info();
		mel.display_info();

		/* giving a 10% raise to each employee */
		sue.give_raise(.1);
		mel.give_raise(.1);

		/* showing the employee's updated info */	
		sue.display_info();
		mel.display_info();
	}

}
