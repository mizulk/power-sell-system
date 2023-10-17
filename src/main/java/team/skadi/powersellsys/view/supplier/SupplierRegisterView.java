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

    private VerificationTextField<JTextField> accountTextField;
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

	}

	@Override
    protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {
        gbc.gridy++; // 表示为下一行，每行应该有两个元素
//        JLabel label = new JLabel("姓名：", JLabel.LEFT);
//        centerPanel.add(label, gbc);// 第一个元素是输入框的提示语句
        accountTextField = new VerificationTextField<>("姓名：", new JTextField(20));
        centerPanel.add(accountTextField, gbc);// 第二个元素是输入框

        gbc.gridy++;
//        JLabel label1 = new JLabel("密码：", JLabel.LEFT);
//        centerPanel.add(label1, gbc);
        passwordField = new VerificationTextField<>("密码：", new JPasswordField(20));
        centerPanel.add(passwordField, gbc);

        gbc.gridy++;
//        JLabel label3 = new JLabel("电话号码：", JLabel.LEFT);
//        centerPanel.add(label3, gbc);
        telTextField = new VerificationTextField<>("电话：", new JTextField(20));
        centerPanel.add(telTextField, gbc);

        gbc.gridy++;
//        JLabel label4 = new JLabel("地址：", JLabel.LEFT);
//        centerPanel.add(label4, gbc);
        addressTextField = new VerificationTextField<>("地址：", new JTextField(20));
        centerPanel.add(addressTextField, gbc);

        gbc.gridy++;
//        JLabel label5 = new JLabel("邮政编码：", JLabel.LEFT);
//        centerPanel.add(label5, gbc);
        zipcodeTextField = new VerificationTextField<>("邮编：", new JTextField(20));
        centerPanel.add(zipcodeTextField, gbc);
    }

    @Override
    protected void register() {
        Supplier supplier = new Supplier();
//        boolean getTelexists = false;
        supplier.setName(accountTextField.getText());
        supplier.setPassword(String.valueOf(passwordField.getText()));
        supplier.setTel(telTextField.getText());
        supplier.setAddress(addressTextField.getText());
        supplier.setZipCode(zipcodeTextField.getText());
        if (supplier.getName().equals("")) {
            JOptionPane.showMessageDialog(app, "请输入姓名！");
        } else if (supplier.getPassword().equals("")) {
            JOptionPane.showMessageDialog(app, "请输入密码！");
        } else if (supplier.getTel().equals("")) {
            JOptionPane.showMessageDialog(app, "请输入电话号码！");
        } else if (supplier.getAddress().equals("")) {
            JOptionPane.showMessageDialog(app, "请输入地址！");
        } else if (supplier.getZipCode().equals("")) {
            JOptionPane.showMessageDialog(app, "请输入邮政编码！");
        } else if (telTextField.getText() != null) {
            SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
            if (supplierService.getTelexists(telTextField.getText())) {
                JOptionPane.showMessageDialog(app, "该用户已存在！");
            } else {
                supplierService.register(supplier);
//            supplierService.register(supplier);
                app.useRouter().showView(ViewName.SUPPLIER_REGISTER_VIEW, ViewName.SUPPLIER_LOGIN_VIEW);
            }
        }
    }
}
