package team.skadi.powersellsys.components.dialog;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	protected int mode;
	private JPanel inputPanel;

	public EditDialog(JFrame frame, String title, int mode) {
		super(frame, title);
		this.mode = mode;
	}

	@Override
	protected JComponent getCenterLayout() {
		inputPanel = new JPanel(new GridLayout(0, 2, 0, 10));
		buildInputLayout();
		return inputPanel;
	}

	/**
	 * 构建编辑字段，请使用{@link #addField(String, JComponent)}向对话框添加字段
	 */
	abstract protected void buildInputLayout();

	/**
	 * 向对话框添加横向的字段名-编辑器字段
	 * <p>
	 * 使用方法：
	 * <pre><code>
	 * nameField = new JTextField(TEXT_FIELD_COLUMNS);
	 * addField("用户名：", nameField);
	 * </code></pre>
	 * </p>
	 *
	 * @param fieldName 字段名
	 * @param editor    编辑器
	 */
	protected void addField(String fieldName, JComponent editor) {
		JLabel label = new JLabel(fieldName);
		inputPanel.add(label);
		inputPanel.add(editor);
	}

	/**
	 * 设置需要展示的数据，需要被重写来初始化编辑器中的值
	 *
	 * @param data 需要展示的数据
	 */
	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	protected boolean error(String msg) {
		JOptionPane.showMessageDialog(getParent(), msg);
		return false;
	}

	protected boolean successAndExit(String msg) {
		JOptionPane.showMessageDialog(getParent(), msg);
		return super.onConfirmButtonClick();
	}
}
