package team.skadi.powersellsys.components.dialog.select;

import lombok.SneakyThrows;
import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.dialog.BasicDialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class SelectField extends BasicComponent {

	private final JTextField textField;
	private final Class<? extends SelectDialog<?>> dialog;
	private JButton selectBtn;

	public SelectField(App app, int columns, Class<? extends SelectDialog<?>> dialog) {
		super(app, false);
		textField = new JTextField(columns);
		this.dialog = dialog;
		init();
	}

	public boolean isInputted() {
		return textField.getText().equals("");
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public void setEditable(boolean editable) {
		textField.setEditable(editable);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());

		add(textField, BorderLayout.CENTER);

		selectBtn = new JButton("↗");
		add(selectBtn, BorderLayout.EAST);
	}

	@Override
	protected void addListener() {
		selectBtn.addActionListener(this);
	}

	@SneakyThrows
	@Override
	public void actionPerformed(ActionEvent e) {
		SelectDialog<?> selectDialog = dialog.getDeclaredConstructor(JFrame.class).newInstance(app);
		int option1 = selectDialog.getOption();
		if (option1 != BasicDialog.CANCEL_OPTION) {
			textField.setText(String.valueOf(option1));
		}
	}
}
