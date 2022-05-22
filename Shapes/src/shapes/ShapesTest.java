package shapes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShapesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = JOptionPane.showInputDialog(
				"Enter 1 to draw rectable\n" +
				"Enter 2 to draw Ovals");
		int choice = Integer.parseInt(input); //gets the input
		Shapes panel = new Shapes(choice);
		
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(300,300);
		application.setVisible(true);
				
	}

}
