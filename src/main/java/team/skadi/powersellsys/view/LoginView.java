package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class LoginView extends BasicView implements ActionListener {

	JButton loginBtn;
	JButton registerBtn;
	JButton returnBtn;

	public LoginView(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// (1, 1) 0,0,40,0
		gbc.gridy = 1;
		gbc.insets.bottom = 40;
		JLabel label = new JLabel(getTitleString(), JLabel.CENTER);
		label.setFont(Main.TITLE_FONT);
		centerPanel.add(label, gbc);

		gbc.insets.bottom = 10;
		// (1,n) 0,0,10,0
		buildTextField(centerPanel, gbc);

		// (1, 1+n) 20,0,0,0
		gbc.gridy++;
		gbc.insets.top = 20;
		gbc.insets.bottom = 0;
		centerPanel.add(getBtnPanel(), gbc);
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * 使用方法和注册{@link RegisterView#buildTextField(JPanel, GridBagConstraints)}相同
	 */
	abstract protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc);

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
