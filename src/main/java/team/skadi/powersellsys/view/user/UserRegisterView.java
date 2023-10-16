package team.skadi.powersellsys.view.user;

import lombok.extern.slf4j.Slf4j;
import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.pojo.User;
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

    public UserRegisterView(App app) {
        super(app);
        telPattern = Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");
    }

    @Override
    protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {

        gbc.gridy++;
        nameTextField = new VerificationTextField<>("姓名：", new JTextField(20));
        nameTextField.setVerification(context -> {
            if (context.equals("")) {
                return "请输入姓名";
            } else if (context.length() > 20) {
                return "名字不能超过20个字符";
            } else {
                return "";
            }
        });
        centerPanel.add(nameTextField, gbc);

        gbc.gridy++;
        passwordTextField = new VerificationTextField<>("密码：", new JPasswordField(20));
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
        telTextField = new VerificationTextField<>("手机：", new JTextField(20));
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
        addressTextField = new VerificationTextField<>("地址：", new JTextField(20));
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
            JOptionPane.showMessageDialog(app, "一眼顶真，鉴定为玩原神玩的");
            log.info("{}", user);
        }
    }

    @Override
    protected void onReturnBtnClick() {
        super.onReturnBtnClick();
        nameTextField.reset();
        passwordTextField.reset();
        telTextField.reset();
        addressTextField.reset();
    }
}
