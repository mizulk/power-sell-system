package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.User;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class UserInformationPanel extends BasicComponent {

	private boolean isPwdVisible;
	private User user;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel sexLabel;
	private JLabel zipCodeLabel;
	private JLabel telLabel;
	private JLabel addressLabel;
	private JLabel passwordLabel;
	private JLabel createTimeLabel;
	private JLabel updateTimeLabel;
	private JLabel loginTimeLabel;
	private ImageButton visibleBtn;

	public UserInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		user = new User();
		user.setPassword("123456");
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		ImageLabel imageLabel;
		// (1,1)
		gbc.gridy = 1;
		gbc.insets.set(0, 0, 10, 10);
		imageLabel = new ImageLabel("用户名: ", "/images/user.png");
		add(imageLabel, gbc);
		// (2,1)
		gbc.weightx = 1;
		gbc.insets.set(0, 0, 10, 50);
		nameLabel = new JLabel();
		add(nameLabel, gbc);

		// (3,1)
		gbc.weightx = 0;
		gbc.insets.set(0, 0, 10, 10);
		imageLabel = new ImageLabel("年龄：", "/images/age.png");
		add(imageLabel, gbc);
		// (4,1)
		gbc.weightx = 1;
		ageLabel = new JLabel();
		add(ageLabel, gbc);

		gbc.gridy++;
		// (1,2)
		imageLabel = new ImageLabel("性别：", "/images/gender.png");
		add(imageLabel, gbc);
		// (2,2)
		sexLabel = new JLabel();
		add(sexLabel, gbc);

		// (3,2)
		imageLabel = new ImageLabel("邮编：", "/images/email.png");
		add(imageLabel, gbc);
		// (4,2)
		zipCodeLabel = new JLabel();
		add(zipCodeLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		// (1-2,3)
		gbc.anchor = GridBagConstraints.CENTER;
		imageLabel = new ImageLabel("电话：", "/images/tel.png");
		add(imageLabel, gbc);
		// (2-4,3)
		gbc.anchor = GridBagConstraints.WEST;
		telLabel = new JLabel();
		add(telLabel, gbc);

		gbc.gridy++;
		// (1-2,4)
		gbc.anchor = GridBagConstraints.CENTER;
		imageLabel = new ImageLabel("地址：", "/images/address.png");
		add(imageLabel, gbc);
		// (2-4,4)
		gbc.anchor = GridBagConstraints.WEST;
		addressLabel = new JLabel();
		add(addressLabel, gbc);

		gbc.gridy++;
		// (1,5)
		gbc.gridwidth = 1;
		imageLabel = new ImageLabel("密码：", "/images/password.png");
		add(imageLabel, gbc);
		// (2,5)
		visibleBtn = new ImageButton("/images/visible.png");
		add(visibleBtn, gbc);
		// (3-4,5)
		gbc.gridwidth = 2;
		passwordLabel = new JLabel();
		add(passwordLabel, gbc);

		gbc.gridy++;
		// (1-2,6)
		imageLabel = new ImageLabel("注册时间：", "/images/time.png");
		add(imageLabel, gbc);
		// (3-4,6)
		createTimeLabel = new JLabel();
		add(createTimeLabel, gbc);

		gbc.gridy++;
		// (1-2,7)
		imageLabel = new ImageLabel("修改时间：", "/images/update-time.png");
		add(imageLabel, gbc);
		// (2-4,7)
		updateTimeLabel = new JLabel();
		add(updateTimeLabel, gbc);

		gbc.gridy++;
		// (1-2,8)
		imageLabel = new ImageLabel("最近登录：", "/images/login-time.png");
		add(imageLabel, gbc);
		// (2-4,8)
		loginTimeLabel = new JLabel();
		add(loginTimeLabel, gbc);
	}

	public void showUser(User user) {
		this.user = user;
		nameLabel.setText(user.getName());
		ageLabel.setText(String.valueOf(user.getAge()));
		sexLabel.setText(user.getSex() == 0 ? "女" : "男");
		zipCodeLabel.setText(user.getZipCode() == null ? "未填写" : user.getZipCode());
		telLabel.setText(user.getTel());
		addressLabel.setText(user.getAddress());
		passwordLabel.setText("******");
		createTimeLabel.setText(user.getCreateTime().toString().replace('T', ' '));
		updateTimeLabel.setText(user.getUpdateTime().toString().replace('T', ' '));
		if (user.getLoginTime() != null)
			loginTimeLabel.setText(user.getLoginTime().toString().replace('T', ' '));
		else
			loginTimeLabel.setText("尚未登录");
	}

	public User getUser() {
		return user;
	}

	@Override
	protected void addListener() {
		visibleBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isPwdVisible) {
			visibleBtn.setImage("/images/visible.png");
			passwordLabel.setText("******");
			isPwdVisible = false;
		} else {
			visibleBtn.setImage("/images/invisible.png");
			passwordLabel.setText(user.getPassword());
			isPwdVisible = true;
		}
	}
}
