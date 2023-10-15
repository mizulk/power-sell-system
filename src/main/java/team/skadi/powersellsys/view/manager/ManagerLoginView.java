package team.skadi.powersellsys.view.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.view.LoginView;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;

public class ManagerLoginView extends LoginView {

	private VerificationTextField<JTextField> jobNumberField;
	private VerificationTextField<JPasswordField> passwordField;

	public ManagerLoginView(App app) {
		super(app);
	}

	@Override
	protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {
		gbc.gridy++;
		jobNumberField = new VerificationTextField<>("工号：", new JTextField(20));
		jobNumberField.setVerification(context -> {
			if (context.equals("")) {
				return "工号不能为空";
			} else if (context.length() != 4) {
				return "工号长度有4位";
			} else {
				return "";
			}
		});
		centerPanel.add(jobNumberField, gbc);

		gbc.gridy++;
		passwordField = new VerificationTextField<>("密码：", new JPasswordField(20));
		passwordField.setVerification(context -> {
			if (context.equals("")) {
				return "密码不能为空";
			} else if (context.length() < 6) {
				return "密码长度不能少于6";
			} else if (context.length() > 20) {
				return "密码长度不能大于20";
			} else {
				return "";
			}
		});
		centerPanel.add(passwordField, gbc);
	}

	@Override
	protected String getTitleString() {
		return "管理员";
	}

	@Override
	protected void login() {
		// 全部验证一遍不过在提醒用户
		if (jobNumberField.isTextValid() && passwordField.isTextValid()) {
			app.useRouter().showView(ViewName.MANAGER_LOGIN_VIEW, ViewName.MANAGER_MAIN_VIEW);
		} else {
			JOptionPane.showMessageDialog(app, "请检查你的信息是否正确。");
		}
	}

	@Override
	protected void register() {

	}

	@Override
	protected void onReturnBtnClick() {
		super.onReturnBtnClick();
		jobNumberField.reset();
		passwordField.reset();
	}
}
