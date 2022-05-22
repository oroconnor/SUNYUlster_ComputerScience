package additionnew;

import javax.swing.JOptionPane;

public class AdditionNew {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String firstNumber =
				JOptionPane.showInputDialog("Enter First Integer");
		String secondNumber =
				JOptionPane.showInputDialog("Enter Second Integer");	
		int number1 = Integer.parseInt(firstNumber);
		int number2 = Integer.parseInt(secondNumber);
		int sum = number1 + number2;
		JOptionPane.showMessageDialog(null, "The Sum is:" + sum, "Sum of Two Integers", JOptionPane.PLAIN_MESSAGE);
		
		
	}

}
