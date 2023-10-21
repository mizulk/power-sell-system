package team.skadi.powersellsys.view.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.store.ManagerStore;
import team.skadi.powersellsys.store.UserStore;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.view.LoginView;

import javax.swing.*;
import java.awt.GridBagConstraints;

public class UserLoginView extends LoginView {

	private VerificationTextField<JTextField> accountTextField;
	private VerificationTextField<JPasswordField> passwordField;

	public UserLoginView(App app) {
		super(app);
	}

	@Override
	public void onShow() {
		System.out.println("UserLoginView.onShow");
	}

	@Override
	public void onHide() {
		System.out.println("UserLoginView.onHide");
	}

	@Override
	protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {
		gbc.gridy++;
		accountTextField = new VerificationTextField<>("账号：", new JTextField(20));
		accountTextField.setVerification(context -> {
			if (context.equals("")) {
				return "账号不能为空";
			} else if (context.length() != 8) {
				return "账号长度为8位";
			} else {
				return "";
			}
		});
		centerPanel.add(accountTextField, gbc);

		gbc.gridy++;
		passwordField = new VerificationTextField<>("密码：", new JPasswordField(20));
		passwordField.setVerification(context -> {
			if (context.equals("")) {
				return "密码不能为空";
			} else if (context.length() < 6) {
				return "密码长度最少为6位";
			} else if (context.length() >= 20) {
				return "密码长度最大为20位";
			} else {
				return "";
			}
		});
		centerPanel.add(passwordField, gbc);
	}

	@Override
	protected String getTitleString() {
		return "用户";
	}

	@Override
	protected void login() {
		if (accountTextField.isTextValid() && passwordField.isTextValid()) {
			UserService userService = ServiceUtil.getService(UserService.class);
			User user = userService.login(accountTextField.getText(),passwordField.getText());
			if (user != null) {
				app.useStore().userStore = new UserStore(user.getAccount());
				JOptionPane.showMessageDialog(app, "登录成功，欢迎使用你," + user.getName());
				userService.updateLoginTime(user.getAccount());
				app.useRouter().showView(ViewName.USER_LOGIN_VIEW, ViewName.USER_MAIN_VIEW);
				passwordField.reset();
			} else {
				JOptionPane.showMessageDialog(app, "账号或密码错误。");
			}
		} else {
			JOptionPane.showMessageDialog(app, "请检查你的账号或密码是否正确。");
		}
	}

	@Override
	protected void register() {
		app.useRouter().showView(ViewName.USER_LOGIN_VIEW, ViewName.USER_REGISTER_VIEW);
	}

	@Override
	protected void onReturnBtnClick() {
		super.onReturnBtnClick();
		accountTextField.reset();
		passwordField.reset();
	}
}
