package team.skadi.powersellsys.components.dialog;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * 基础编辑对话框，打横显示字段-编辑框
 *
 * @param <T> pojo里面的数据
 */
public abstract class EditDialog<T> extends BasicDialog {

	public static final int TEXT_FIELD_COLUMNS = 12;

	public static final int MODIFY_MODE = 1;
	public static final int ADD_MODE = 2;

	protected T data;

	public EditDialog(JFrame frame, String title) {
		super(frame, title);
	}

	@Override
	protected JComponent getCenterLayout() {
		JPanel inputPanel = new JPanel(new GridLayout(0, 2, 0, 10));
		buildInputLayout(inputPanel);
		return inputPanel;
	}

	abstract protected void buildInputLayout(JPanel inputPanel);

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
}
