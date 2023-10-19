package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.Objects;

public class UserDialog extends BasicDialog {

//	private static final Pattern TEL_PATTERN = Pattern.compile("^(13\\d|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18\\d|19[0-35-9])\\d{8}$");

	public static final int MODIFY_MODE = 1, ADD_MODE = 2;

	private JTextField nameField;
	private JPasswordField passwordField;
	private JComboBox<String> sexComboBox;
	private JSpinner ageSpinner;
	private JTextField zipCodeField;
	private JTextField telField;
	private JTextField addressField;
	private User user;

	public UserDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加用户" : "修改用户");
	}

	@Override
	protected JComponent getCenterLayout() {
		JPanel inputPanel = new JPanel(new GridLayout(0, 2, 0, 10));
		JLabel label;

		label = new JLabel("用户名(必填)：");
		inputPanel.add(label);
		nameField = new JTextField(12);
		inputPanel.add(nameField);

		label = new JLabel("密码(必填)：");
		inputPanel.add(label);
		passwordField = new JPasswordField(12);
		inputPanel.add(passwordField);

		label = new JLabel("性别：");
		inputPanel.add(label);
		sexComboBox = new JComboBox<>(new String[]{"女", "男"});
		inputPanel.add(sexComboBox);

		label = new JLabel("年龄：");
		inputPanel.add(label);
		ageSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
		inputPanel.add(ageSpinner);

		label = new JLabel("邮政编码：");
		inputPanel.add(label);
		zipCodeField = new JTextField(12);
		inputPanel.add(zipCodeField);

		label = new JLabel("手机号(必填)：");
		inputPanel.add(label);
		telField = new JTextField(12);
		inputPanel.add(telField);

		label = new JLabel("地址(必填)：");
		inputPanel.add(label);
		addressField = new JTextField(12);
		inputPanel.add(addressField);

		return inputPanel;
	}

	public void setData(User user) {
		this.user = user;
		nameField.setText(user.getName());
		passwordField.setText(user.getPassword());
		sexComboBox.setSelectedIndex(user.getSex());
		ageSpinner.setValue(user.getAge());
		zipCodeField.setText(user.getZipCode());
		telField.setText(user.getTel());
		addressField.setText(user.getAddress());
	}

	public User getUser() {
		return user;
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (nameField.getText().equals("") &&
				passwordField.getPassword().length == 0 &&
				telField.getText().equals("") &&
				addressField.getText().equals("")
		) {
			JOptionPane.showMessageDialog(getParent(), "请输入必填项");
			return false;
		} else {
			// TODO：检验手机号，地址长度，密码长度
			User user = new User();
			user.setName(nameField.getText().trim());
			user.setPassword(new String(passwordField.getPassword()).trim());
			user.setSex((byte) sexComboBox.getSelectedIndex());
			if (ageSpinner.getValue() instanceof Integer n)
				user.setAge(n.shortValue());
			else
				user.setAge((Short) ageSpinner.getValue());
			user.setZipCode(zipCodeField.getText().equals("") ? null : zipCodeField.getText().trim());
			user.setTel(telField.getText().equals("") ? null : telField.getText().trim());
			user.setAddress(addressField.getText().equals("") ? null : addressField.getText().trim());
			UserService userService = ServiceUtil.getService(UserService.class);

			if (this.user != null &&
					user.getName().equals(this.user.getName()) &&
					user.getPassword().equals(this.user.getPassword()) &&
					user.getSex().equals(this.user.getSex()) &&
					user.getAge().equals(this.user.getAge()) &&
					user.getZipCode().equals(this.user.getZipCode()) &&
					user.getTel().equals(this.user.getTel()) &&
					user.getAddress().equals(this.user.getAddress())
			) {
				JOptionPane.showMessageDialog(getParent(), "信息未修改");
				return false;
			}
			if (Objects.nonNull(this.user)) {
				this.user.setName(user.getName());
				this.user.setPassword(user.getPassword());
				this.user.setSex(user.getSex());
				this.user.setAge(user.getAge());
				this.user.setZipCode(user.getZipCode());
				this.user.setTel(user.getTel());
				this.user.setAddress(user.getAddress());
				userService.updateUser(this.user);
				JOptionPane.showMessageDialog(getParent(), "修改成功");
				return super.onConfirmButtonClick();
			}
			String register = userService.register(user);
			if (this.user == null)
				this.user = user;
			JOptionPane.showMessageDialog(getParent(), "添加成功，新的用户账号为：" + register);
			return super.onConfirmButtonClick();
		}
	}
}
