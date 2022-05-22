package rainbow;

import javax.swing.JFrame;

public class RainbowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rainbow panel = new Rainbow();
		JFrame application = new JFrame();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(400,250);
		application.setVisible(true);

	}

}