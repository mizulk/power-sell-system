package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.Objects;

public class SupplierDialog extends EditDialog<Supplier> {

	private JTextField nameField;
	private JPasswordField passwordField;
	private JTextField telField;
	private JTextField addressField;
	private JTextField zipCodeField;

	public SupplierDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加供应商" : "编辑供应商");
	}

	@Override
	protected void buildInputLayout(JPanel inputPanel) {
		JLabel label;

		label = new JLabel("姓名(必填)：");
		inputPanel.add(label);
		nameField = new JTextField(TEXT_FIELD_COLUMNS);
		inputPanel.add(nameField);

		label = new JLabel("密码(必填)：");
		inputPanel.add(label);
		passwordField = new JPasswordField(TEXT_FIELD_COLUMNS);
		inputPanel.add(passwordField);

		label = new JLabel("电话(必填)：");
		inputPanel.add(label);
		telField = new JTextField(TEXT_FIELD_COLUMNS);
		inputPanel.add(telField);

		label = new JLabel("地址(必填)：");
		inputPanel.add(label);
		addressField = new JTextField(TEXT_FIELD_COLUMNS);
		inputPanel.add(addressField);

		label = new JLabel("邮编(必填)：");
		inputPanel.add(label);
		zipCodeField = new JTextField(TEXT_FIELD_COLUMNS);
		inputPanel.add(zipCodeField);
	}

	@Override
	public void setData(Supplier data) {
		super.setData(data);
		nameField.setText(data.getName());
		passwordField.setText(data.getPassword());
		telField.setText(data.getTel());
		addressField.setText(data.getAddress());
		zipCodeField.setText(data.getZipCode());
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (nameField.getText().equals("")
				&& passwordField.getPassword().length == 0
				&& telField.getText().equals("")
				&& addressField.getText().equals("")
				&& zipCodeField.getText().equals("")
		) {
			JOptionPane.showMessageDialog(getParent(), "请输入必填项");
			return false;
		} else {
			Supplier supplier = new Supplier();
			supplier.setName(nameField.getText().trim());
			supplier.setPassword(String.valueOf(passwordField.getPassword()));
			supplier.setTel(telField.getText().trim());
			supplier.setAddress(addressField.getText().trim());
			supplier.setZipCode(zipCodeField.getText().trim());

			SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
			if (data != null
					&& supplier.getName().equals(data.getName())
					&& supplier.getPassword().equals(data.getPassword())
					&& supplier.getTel().equals(data.getTel())
					&& supplier.getAddress().equals(data.getAddress())
					&& supplier.getZipCode().equals(data.getZipCode())
			) {
				JOptionPane.showMessageDialog(getParent(), "信息未修改");
				return false;
			}
			if (Objects.nonNull(data)) {
				data.setName(supplier.getName());
				data.setPassword(supplier.getPassword());
				data.setTel(supplier.getTel());
				data.setAddress(supplier.getAddress());
				data.setZipCode(supplier.getZipCode());
				supplierService.updateSupplier(data);
				JOptionPane.showMessageDialog(getParent(), "修改成功");
				return super.onConfirmButtonClick();
			}
			String register = supplierService.register(supplier);
			if (Objects.isNull(data)) data = supplier;
			JOptionPane.showMessageDialog(getParent(), "添加成功，新的供应商账号为：" + register);
			return super.onConfirmButtonClick();
		}
	}
}
