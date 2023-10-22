package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.util.Objects;

public class UserDialog extends EditDialog<User> {

//	private static final Pattern TEL_PATTERN = Pattern.compile("^(13\\d|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18\\d|19[0-35-9])\\d{8}$");

	private JTextField nameField;
	private JPasswordField passwordField;
	private JComboBox<String> sexComboBox;
	private JSpinner ageSpinner;
	private JTextField zipCodeField;
	private JTextField telField;
	private JTextField addressField;

	public UserDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加用户" : "修改用户", mode);
	}

	@Override
	protected void buildInputLayout() {
		nameField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("用户名(必填)：", nameField);

		passwordField = new JPasswordField(TEXT_FIELD_COLUMNS);
		addField("密码(必填)：", passwordField);

		sexComboBox = new JComboBox<>(new String[]{"女", "男"});
		addField("性别：", sexComboBox);

		ageSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
		addField("年龄：", ageSpinner);

		zipCodeField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("邮政编码：", zipCodeField);

		telField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("手机号(必填)：", telField);

		addressField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("地址(必填)：", addressField);
	}

	public void setData(User data) {
		super.setData(data);
		nameField.setText(data.getName());
		passwordField.setText(data.getPassword());
		sexComboBox.setSelectedIndex(data.getSex());
		ageSpinner.setValue(data.getAge());
		zipCodeField.setText(data.getZipCode());
		telField.setText(data.getTel());
		addressField.setText(data.getAddress());
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (nameField.getText().equals("")
				&& passwordField.getPassword().length == 0
				&& telField.getText().equals("")
				&& addressField.getText().equals("")
		) {
			return error("请输入必填项");
		} else {
			// TODO：检验手机号，地址长度，密码长度
			User user = new User();
			user.setName(nameField.getText().trim());
			user.setPassword(new String(passwordField.getPassword()));
			user.setSex((byte) sexComboBox.getSelectedIndex());
			if (ageSpinner.getValue() instanceof Integer n)// 更改了年龄后传入的是Integer，不能强转为Short
				user.setAge(n.shortValue());
			else
				user.setAge((Short) ageSpinner.getValue());// 没有更改年龄传入的Short
			user.setZipCode(zipCodeField.getText().equals("") ? null : zipCodeField.getText().trim());
			user.setTel(telField.getText().equals("") ? null : telField.getText().trim());
			user.setAddress(addressField.getText().equals("") ? null : addressField.getText().trim());

			UserService userService = ServiceUtil.getService(UserService.class);

			if (data != null
					&& user.getName().equals(data.getName())
					&& user.getPassword().equals(data.getPassword())
					&& user.getSex().equals(data.getSex())
					&& user.getAge().equals(data.getAge())
					&& user.getZipCode().equals(data.getZipCode())
					&& user.getTel().equals(data.getTel())
					&& user.getAddress().equals(data.getAddress())
			) return error("信息未修改");

			if (Objects.nonNull(data)) {
				data.setName(user.getName());
				data.setPassword(user.getPassword());
				data.setSex(user.getSex());
				data.setAge(user.getAge());
				data.setZipCode(user.getZipCode());
				data.setTel(user.getTel());
				data.setAddress(user.getAddress());
				userService.updateUser(data);
				return successAndExit("修改成功");
			} else {
				data = user;
			}
			String register = userService.register(user);
			return successAndExit("添加成功，新的用户账号为：" + register);
		}
	}
}
