package team.skadi.powersellsys.components;

import team.skadi.powersellsys.Main;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 包含验证提示的输入框模块
 * <p>
 * 不同种类的使用输入框：
 * <pre><code>
 * // 带有提示文本的输入框 ({@link HintTextField})
 * VerificationTextField&lt;HintTextField&gt; accountField = new VerificationTextField&lt;&gt;("账号：", new HintTextField("请输入你的账号", 20));
 * // 密码输入框
 * VerificationTextField&lt;JPasswordField&gt; passwordField = new VerificationTextField&lt;&gt;("密码：", new JPasswordField(20));
 * // 普通的输入框
 * VerificationTextField&lt;JTextField&gt; addressField = new VerificationTextField&lt;&gt;("账号：", new JTextField(20));
 * </code></pre>
 * </p>
 * <p>
 * 添加验证模块：
 * <pre><code>
 * passwordField.setVerification(content -> {
 *     if (content.length() < 6) {
 *         return "密码不能少于6位"; // 输入内容长度小于6位，返回错误信息即可
 *     } else {
 *         return ""; // 如果没有错误返回一个空的字符串即可
 *     }
 * });
 * </code></pre>
 * </p>
 *
 * @param <T> 继承自JTextField的类即可
 */
public class VerificationTextField<T extends JTextField> extends JPanel implements FocusListener {

	private static final MatteBorder ERROR_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED);
	private static final MatteBorder DEFAULT_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
	private final T textField;
	private final String label;
	private JLabel errorLabel;
	private Verification verification;

	public VerificationTextField(String label, T textField) {
		this.textField = textField;
		this.label = label;
		buildLayout();
	}

	public void setVerification(Verification verification) {
		this.verification = verification;
	}

	private void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// (2,1)
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		errorLabel = new JLabel(" ");
		errorLabel.setFont(Main.TINY_FONT);
		errorLabel.setForeground(Color.RED);
		add(errorLabel, gbc);
		// (1,2)
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(new JLabel(label, JLabel.LEFT), gbc);
		// (2,2)
		gbc.gridx = 2;
		textField.addFocusListener(this);
		add(textField, gbc);
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		updateErrorLabel();
	}

	public interface Verification {
		/**
		 * 验证输入框内容验证，当输入框失去焦点时执行
		 *
		 * @param context 输入框内容
		 * @return 错误消息，如果没有错误就返回""（空串）
		 */
		String verify(String context);
	}

	private boolean updateErrorLabel() {
		if (verification == null) return true;
		String str = verification.verify(textField.getText());
		if (str.equals("")) {
			errorLabel.setText(" ");
			textField.setBorder(DEFAULT_BORDER);
			return true;
		} else {
			errorLabel.setText(str);
			textField.setBorder(ERROR_BORDER);
			return false;
		}
	}

	/**
	 * 验证是否通过
	 *
	 * @return false：验证失败，true：验证通过或无监听事件
	 */
	public boolean isTextValid() {
		return updateErrorLabel();
	}

	public T getTextField() {
		return textField;
	}

	/**
	 * @return 输入框的内容
	 */
	public String getText() {
		return textField.getText();
	}

	public void reset() {
		errorLabel.setText(" ");
		textField.setText("");
		textField.setBorder(DEFAULT_BORDER);
	}
}
