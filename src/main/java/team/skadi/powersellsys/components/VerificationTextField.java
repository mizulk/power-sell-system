package team.skadi.powersellsys.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;


public class VerificationTextField extends JPanel {

	private JTextField textField;
	private JLabel label;

	public VerificationTextField(JTextField textField) {
		this.textField = textField;
	}

	private void buildLayout() {
		setLayout(new GridLayout(2, 1));
		textField = new JTextField();
		label = new JLabel();
		label.setForeground(Color.RED);
		add(label);
	}
}
