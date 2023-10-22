package team.skadi.powersellsys.view.supplier;

import lombok.extern.slf4j.Slf4j;
import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.VerificationTextField;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.store.SupplierStore;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.view.LoginView;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;

import javax.swing.*;

@Slf4j
public class SupplierLoginView extends LoginView {

    private VerificationTextField<JTextField> accountTextField;
    private VerificationTextField<JPasswordField> passwordField;

    public SupplierLoginView(App app) {
        super(app);
    }

    @Override
    public void onShow() {
		log.info("供应商登录页面被展示");
		accountTextField.reset();
    }

    @Override
    public void onHide() {
		log.info("供应商登录页面被隐藏");
		passwordField.reset();
    }

    @Override
    protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {
        gbc.gridy++;
        accountTextField = new VerificationTextField<>("账号：", new JTextField(20));
        accountTextField.setVerification(context -> {
            if (context.equals("")) {
                return "账号不能为空";
            } else if (context.length() != 6) {
                return "账号长度为6位";
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
                return "密码长度至少为6位";
            } else {
                return "";
            }
        });
        centerPanel.add(passwordField, gbc);
    }

    @Override
    protected String getTitleString() {
        return "供应商";
    }


    @Override
    protected void login() {
        if (accountTextField.isTextValid() && passwordField.isTextValid()) {
            SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
            Supplier supplier = supplierService.login(accountTextField.getText(), passwordField.getText());
            if (supplier != null) {
                app.useStore().supplierStore = new SupplierStore(supplier.getAccount());
                JOptionPane.showMessageDialog(app, "欢迎回来，" + supplier.getName());
                app.useRouter().showView(ViewName.SUPPLIER_LOGIN_VIEW, ViewName.SUPPLIER_MAIN_VIEW);
            } else {
                JOptionPane.showMessageDialog(app, "账号或密码错误");
            }
        } else {
            JOptionPane.showMessageDialog(app, "登陆失败，请输入正确的账号或密码！");
        }
    }


    @Override
    protected void register() {
        app.useRouter().showView(ViewName.SUPPLIER_LOGIN_VIEW, ViewName.SUPPLIER_REGISTER_VIEW);
    }

}
