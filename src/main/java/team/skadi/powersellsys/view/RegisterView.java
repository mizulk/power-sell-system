package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class RegisterView extends BasicView implements ActionListener {

	private JButton registerBtn;
	private JButton returnBtn;

	public RegisterView(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// (1~2 ,1) 0,0,34,0
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets.bottom = 34;
		JLabel label = new JLabel("欢迎注册", JLabel.CENTER);
		label.setFont(Main.TITLE_FONT);
		centerPanel.add(label, gbc);

		// (1, 2) 24,0,0,0
		// gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.insets.set(10, 0, 0, 0);
		buildTextField(centerPanel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		centerPanel.add(getBtnPanel(), gbc);

		add(centerPanel, BorderLayout.CENTER);
	}

	private JPanel getBtnPanel() {
		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// (1->1,1) 0,38,0,19
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.set(0, 38, 0, 19);
		registerBtn = new JButton("完成");
		btnPanel.add(registerBtn, gbc);

		// (2->2,1) 0,19,0,38
		gbc.insets.set(0, 19, 0, 38);
		returnBtn = new JButton("返回");
		btnPanel.add(returnBtn, gbc);
		return btnPanel;
	}

	/**
	 * 注册页面的输入框，需要包括一个标签（JLabel）和一个输入框（JTextField主要）
	 * <p>
	 * 方法1：(JLabel + JTextFiled)
	 * <pre><code>
	 * gbc.gridy++; // 表示为下一行，每行应该有两个元素
	 * JLabel label = new JLabel("账号", JLabel.LEFT);
	 * centerPanel.add(label, gbc);// 第一个元素是输入框的提示语句
	 * JTextField accountTextField = new JTextField(20);
	 * centerPanel.add(accountTextField, gbc);// 第二个元素是输入框
	 * </code></pre>
	 * </p>
	 * <p>
	 * 方法2：{@link team.skadi.powersellsys.components.VerificationTextField}
	 * <pre><code>
	 * gbc.gridy++;
	 * VerificationTextField<JTextField> accountField = new VerificationTextField<>("账号：", new JTextField(20));
	 * accountField.setVerification(context -> {
	 *     if (context.length() < 6) {
	 *         return "账号长度不能少于6位";
	 *     } else {
	 *         return "";
	 *     }
	 * });
	 * centerPanel.add(accountField, gbc);
	 * </code></pre>
	 * </p>
	 */
	abstract protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc);

	abstract protected void register();

	@Override
	protected void addListener() {
		registerBtn.addActionListener(this);
		returnBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == registerBtn) {
			register();
		} else {
			onReturnBtnClick();
		}
	}

	protected void onReturnBtnClick(){
		app.useRouter().showPreviousView();
	}
}
