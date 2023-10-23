package team.skadi.powersellsys.view.user;

import lombok.extern.slf4j.Slf4j;
import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.view.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

@Slf4j
public class UserRegisterView extends RegisterView {

    private final Pattern telPattern;
    private VerificationTextField<JTextField> nameTextField;
    private VerificationTextField<JTextField> passwordTextField;
    private VerificationTextField<JTextField> telTextField;
    private VerificationTextField<JTextField> addressTextField;
	private VerificationTextField<JPasswordField> passwordField2;

	public UserRegisterView(App app) {
		super(app);
		telPattern = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");
	}

	@Override
	public void onShow() {
		log.info("用户注册页面被展示");
	}

	@Override
	public void onHide() {
		log.info("用户注册页面被隐藏");
	}

	@Override
    protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {

		gbc.gridy++;
		nameTextField = new VerificationTextField<>("昵称 ：", new JTextField(20));
		nameTextField.setVerification(context -> {
			if (context.equals("")) {
				return "请输入昵称";
			} else if (context.length() > 20) {
				return "名字不能超过20个字符";
			} else {
				return "";
			}
		});
        centerPanel.add(nameTextField, gbc);

		gbc.gridy++;
		passwordTextField = new VerificationTextField<>("密码1：", new JPasswordField(20));
		passwordTextField.setVerification(context -> {
			if (context.equals("")) {
				return "请输入密码";
			} else if (context.length() < 6) {
				return "密码长不小于6";
			} else if (context.length() > 20) {
				return "密码长度不大于20";
			} else {
				return "";
			}
		});
		centerPanel.add(passwordTextField, gbc);

		gbc.gridy++;
		passwordField2 = new VerificationTextField<>("密码2：", new JPasswordField(20));
		passwordField2.setVerification(context -> {
			if (context.equals("")) {
				return "密码不能为空";
			} else if (context.length() < 6) {
				return "密码长度至少为6位";
			} else if (!passwordTextField.getText().equals(context)) {
				return "两次输入的密码不相等";
			} else {
				return "";
			}
		});
		centerPanel.add(passwordField2, gbc);

		gbc.gridy++;
		telTextField = new VerificationTextField<>("电话 ：", new JTextField(20));
		telTextField.setVerification(context -> {
			if (context.equals("")) {
				return "请输入电话号码！";
			} else if (!telPattern.matcher(context).matches()) {
				return "请输入正确的电话号码";
			} else {
				return "";
			}
		});
        centerPanel.add(telTextField, gbc);

		gbc.gridy++;
		addressTextField = new VerificationTextField<>("地址 ：", new JTextField(20));
		addressTextField.setVerification(context -> context.equals("") ? "请输入地址" : "");
        centerPanel.add(addressTextField, gbc);

    }

    @Override
    protected void register() {
        if (nameTextField.isTextValid() && passwordTextField.isTextValid()
                && telTextField.isTextValid() && addressTextField.isTextValid()) {
            User user = new User();
            user.setName(nameTextField.getText());
            user.setPassword(passwordTextField.getText());
            user.setTel(telTextField.getText());
            user.setAddress(addressTextField.getText());
            UserService service = ServiceUtil.getService(UserService.class);
            String register = service.register(user);
			JOptionPane.showMessageDialog(app, "注册完成你的账号为：" + register);
			app.useRouter().showPreviousView();
			log.info("{}", user);
        } else {
			JOptionPane.showMessageDialog(app, "请完善个人信息");
        }
    }

    @Override
    protected void onReturnBtnClick() {
        super.onReturnBtnClick();
        nameTextField.reset();
        passwordTextField.reset();
        telTextField.reset();
		addressTextField.reset();
		passwordField2.reset();
    }
}
