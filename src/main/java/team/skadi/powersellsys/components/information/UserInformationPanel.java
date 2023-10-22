package team.skadi.powersellsys.components.information;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.ImageLabel;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.StringUtil;

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
	private JLabel balanceLabel;
	private JLabel addressLabel;
	private JLabel passwordLabel;
	private JLabel createTimeLabel;
	private JLabel updateTimeLabel;
	private JLabel loginTimeLabel;
	private ImageButton visibleBtn;
	private JLabel accountLabel;

	public UserInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		ImageLabel imageLabel;
		// (1,1)
		gbc.gridy = 1;
		gbc.insets.set(0, 0, 10, 10);
		imageLabel = new ImageLabel("用户名: ", "/images/user.png");
		add(imageLabel, gbc);
		// (2-4,1)
		gbc.gridwidth = 3;
		nameLabel = new JLabel();
		add(nameLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		// (1,2)
		imageLabel = new ImageLabel("年龄：", "/images/age.png");
		add(imageLabel, gbc);
		// (2,2)
		ageLabel = new JLabel();
		add(ageLabel, gbc);

		// (3,2)
		imageLabel = new ImageLabel("性别：", "/images/gender.png");
		add(imageLabel, gbc);
		// (4,2)
		sexLabel = new JLabel();
		add(sexLabel, gbc);

		gbc.gridy++;
		// (1,3)
		imageLabel = new ImageLabel("邮编：", "/images/email.png");
		add(imageLabel, gbc);
		// (2,3)
		zipCodeLabel = new JLabel();
		add(zipCodeLabel, gbc);

		// (3,3)
		imageLabel = new ImageLabel("余额：", "/images/balance.png");
		add(imageLabel, gbc);
		// (4,3)
		balanceLabel = new JLabel();
		add(balanceLabel, gbc);

		gbc.gridy++;
		// (1,4)
		imageLabel = new ImageLabel("电话：", "/images/tel.png");
		add(imageLabel, gbc);
		// (2,4)
		telLabel = new JLabel();
		add(telLabel, gbc);

		// (3,4)
		imageLabel = new ImageLabel("账号：", "/images/account.png");
		add(imageLabel, gbc);
		// (4,4)
		accountLabel = new JLabel();
		add(accountLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		// (1-2,5)
		imageLabel = new ImageLabel("地址：", "/images/address.png");
		add(imageLabel, gbc);
		// (2-4,5)
		addressLabel = new JLabel();
		add(addressLabel, gbc);

		gbc.gridy++;
		// (1,6)
		gbc.gridwidth = 1;
		imageLabel = new ImageLabel("密码：", "/images/password.png");
		add(imageLabel, gbc);
		// (2,6)
		visibleBtn = new ImageButton("/images/visible.png");
		add(visibleBtn, gbc);
		// (3-4,6)
		gbc.gridwidth = 2;
		passwordLabel = new JLabel();
		add(passwordLabel, gbc);

		gbc.gridy++;
		// (1-2,7)
		imageLabel = new ImageLabel("注册时间：", "/images/time.png");
		add(imageLabel, gbc);
		// (3-4,7)
		createTimeLabel = new JLabel();
		add(createTimeLabel, gbc);

		gbc.gridy++;
		// (1-2,8)
		imageLabel = new ImageLabel("修改时间：", "/images/update-time.png");
		add(imageLabel, gbc);
		// (2-4,8)
		updateTimeLabel = new JLabel();
		add(updateTimeLabel, gbc);

		gbc.gridy++;
		// (1-2,9)
		imageLabel = new ImageLabel("最近登录：", "/images/login-time.png");
		add(imageLabel, gbc);
		// (2-4,9)
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
		accountLabel.setText(user.getAccount());
		balanceLabel.setText(StringUtil.FLOAT_FORMAT.format(user.getBalance()));
		addressLabel.setText(user.getAddress());
		passwordLabel.setText("******");
		createTimeLabel.setText(DateUtil.replaceT(user.getCreateTime()));
		updateTimeLabel.setText(DateUtil.replaceT(user.getUpdateTime()));
		if (user.getLoginTime() != null)
			loginTimeLabel.setText(DateUtil.replaceT(user.getLoginTime()));
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
