package textfieldframe;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class TextFieldFrame extends JFrame {
	private JTextField textField1;
	
	public TextFieldFrame()
	{
		super("Example Event Program");
		textField1 = new JTextField(10);
		add(textField1);;
	
	
	TextFieldHandler handler = new TextFieldHandler();
	textField1.addActionListener(handler);
	
	}
	
	private class TextFieldHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == textField1)
				JOptionPane.showMessageDialog(null, "Event Triggered");
		}
	}	
	
}
