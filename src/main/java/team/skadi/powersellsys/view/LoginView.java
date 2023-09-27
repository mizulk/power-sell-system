package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LoginView extends BasicView implements ActionListener {

	JTextField accountTextField;
	JPasswordField passwordField;
	JButton loginBtn;
	JButton registerBtn;
	JButton returnBtn;

	public LoginView(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel label;

		gbc.gridy = 1;// (1~2, 1)
		gbc.gridwidth = 2;
		gbc.insets.bottom = 50;
		label = new JLabel(getTitleString(), JLabel.CENTER);
		label.setFont(Main.TITLE_FONT);
		add(label, gbc);

		gbc.gridy++;// (1, 2)
		gbc.gridwidth = 1;
		gbc.insets.right = 10;
		gbc.insets.bottom = 30;

		gbc.gridy++;
		gbc.gridx = -1;
		label = new JLabel("账户：", JLabel.CENTER);
		add(label, gbc);
		gbc.insets.right = 0;// (2, 2)
		accountTextField = new JTextField(20);
		add(accountTextField, gbc);

		gbc.gridy++;//(1, 3)
		gbc.insets.right = 10;
		gbc.insets.bottom = 40;
		label = new JLabel("密码：", JLabel.CENTER);
		add(label, gbc);
		gbc.insets.right = 0;// (2, 3)
		passwordField = new JPasswordField(20);
		add(passwordField, gbc);

		gbc.gridy++;//(1~2, 4)
		gbc.gridwidth = 2;
		gbc.insets.bottom = 0;
		add(getBtnPanel(), gbc);
	}

	@Override
	protected void addListener() {
		loginBtn.addActionListener(this);
		returnBtn.addActionListener(this);

	}

	protected JPanel getBtnPanel() {
		JPanel btnPanel = new JPanel(new GridLayout(1, 2, 50, 50));
		loginBtn = new JButton("登录");
		btnPanel.add(loginBtn);
		if (!getTitleString().equals("管理员")) {
			registerBtn = new JButton("注册");
			registerBtn.addActionListener(this);
			btnPanel.add(registerBtn);
		}
		returnBtn = new JButton("返回");
		btnPanel.add(returnBtn);
		return btnPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == loginBtn) {
			login();
		} else if (source == registerBtn) {
			register();
		} else if (source == returnBtn) {
			onReturnBtnClick();
		}
	}

	abstract protected String getTitleString();

	abstract protected void login();

	abstract protected void register();

	protected void onReturnBtnClick() {
		app.useRouter().showPreviousView();
	}

}
