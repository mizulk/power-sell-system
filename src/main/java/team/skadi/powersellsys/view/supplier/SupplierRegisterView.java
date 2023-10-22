package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.view.RegisterView;

import javax.swing.*;
import java.awt.*;

public class SupplierRegisterView extends RegisterView {

    private VerificationTextField<JTextField> nameTextField;
    private VerificationTextField<JPasswordField> passwordField;
    private VerificationTextField<JTextField> telTextField;
    private VerificationTextField<JTextField> addressTextField;
    private VerificationTextField<JTextField> zipcodeTextField;


    public SupplierRegisterView(App app) {
        super(app);
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {
        nameTextField.reset();
        passwordField.reset();
        telTextField.reset();
        addressTextField.reset();
        zipcodeTextField.reset();
    }

    @Override
    protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {
        gbc.gridy++; // 表示为下一行，每行应该有两个元素
        nameTextField = new VerificationTextField<>("姓名：", new JTextField(20));
        centerPanel.add(nameTextField, gbc);// 第二个元素是输入框
        nameTextField.setVerification(context -> {
            if (context.equals("")) {
                return "姓名不能为空";
            } else if (context.length() > 50) {
                return "姓名不能超过50个字符";
            } else {
                return "";
            }
        });

        gbc.gridy++;
        passwordField = new VerificationTextField<>("密码：", new JPasswordField(20));
        centerPanel.add(passwordField, gbc);
        passwordField.setVerification(context -> {
            if (context.equals("")) {
                return "密码不能为空";
            } else if (context.length() < 6) {
                return "密码长度至少为6位";
            } else {
                return "";
            }
        });

        gbc.gridy++;
        telTextField = new VerificationTextField<>("电话：", new JTextField(20));
        centerPanel.add(telTextField, gbc);
        telTextField.setVerification(context -> {
            if (context.equals("")) {
                return "电话不能为空";
            } else if (context.length() != 11) {
                return "电话长度为11位";
            } else {
                return "";
            }
        });

        gbc.gridy++;
        addressTextField = new VerificationTextField<>("地址：", new JTextField(20));
        centerPanel.add(addressTextField, gbc);
        addressTextField.setVerification(context -> {
            if (context.equals("")) {
                return "地址不能为空";
            } else if (context.length() < 8) {
                return "地址长度至少为8位";
            } else {
                return "";
            }
        });

        gbc.gridy++;
        zipcodeTextField = new VerificationTextField<>("邮编：", new JTextField(20));
        centerPanel.add(zipcodeTextField, gbc);
        zipcodeTextField.setVerification(context -> {
            if (context.equals("")) {
                return "邮编不能为空";
            } else if (context.length() != 6) {
                return "邮编长度为6位";
            } else {
                return "";
            }
        });
    }

    @Override
    protected void register() {
        SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
        if (nameTextField.isTextValid() && passwordField.isTextValid() && telTextField.isTextValid()) {
            Supplier supplier = new Supplier();
            supplier.setName(nameTextField.getText());
            supplier.setPassword(String.valueOf(passwordField.getText()));
            supplier.setTel(telTextField.getText());
            supplier.setAddress(addressTextField.getText());
            supplier.setZipCode(zipcodeTextField.getText());
            if (supplierService.getTelExists(telTextField.getText())) {
				String register = supplierService.register(supplier);
				JOptionPane.showMessageDialog(app, "注册成功你的账号是：" + register);
				app.useRouter().showView(ViewName.SUPPLIER_REGISTER_VIEW, ViewName.SUPPLIER_LOGIN_VIEW);
			} else {
				JOptionPane.showMessageDialog(app, "该电话号码已被注册，请更换");
			}
        } else {
            JOptionPane.showMessageDialog(app, "请检查你的信息是否正确");
        }
    }
}

