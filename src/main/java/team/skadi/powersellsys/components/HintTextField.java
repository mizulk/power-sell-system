package team.skadi.powersellsys.components;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 带有提示信息的JTextField
 */
public class HintTextField extends JTextField implements FocusListener {

	private String hint;

	public HintTextField(String hint) {
		this(hint, 0);
	}

	public HintTextField(String hint, int column) {
		super(column);
		this.hint = hint;
		init();
	}

	protected void init() {
		setText(hint);
		setForeground(Color.GRAY);
		addFocusListener(this);
	}

	public void setHint(String hint) {
		if (getText().equals("")) setText(hint);
		this.hint = hint;
	}

	public String getHint() {
		return hint;
	}

	@Override
	public String getText() {
		String text = super.getText();
		return text.equals(hint) ? "" : text;
	}

	@Override
	public void setText(String t) {
		if (super.getText().equals(hint)) return;
		super.setText(t);
	}

	/**
	 * 输入的内容是否为空
	 *
	 * @return true，当前输入框没有内容，否则false
	 */
	public boolean isEmpty() {
		return getText().equals(hint);
	}

	@Override
	public void focusGained(FocusEvent e) {
		String text = super.getText();
		if (text.equals(hint)) {
			super.setText("");
			setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		String text = super.getText();
		if (text.equals("")) {
			setForeground(Color.GRAY);
			setText(hint);
		}
	}
}
