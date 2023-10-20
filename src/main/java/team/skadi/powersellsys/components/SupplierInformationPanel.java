package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.Supplier;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class SupplierInformationPanel extends BasicComponent {

	private Supplier supplier;
	private JLabel nameLabel;
	private JLabel accountLabel;
	private JLabel zipCodeLabel;
	private ImageButton visibleBtn;
	private JLabel telLabel;
	private JLabel addressLabel;
	private JLabel passwordLabel;
	private boolean isPwdVisible;

	public SupplierInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.insets.set(0, 0, 10, 10);
		ImageLabel imageLabel;

		gbc.gridy = 1;
		// (1,1)
		imageLabel = new ImageLabel("供应商名: ", "/images/user.png");
		add(imageLabel, gbc);
		// (2-4,1)
		gbc.gridwidth = 3;
		nameLabel = new JLabel();
		add(nameLabel, gbc);

		gbc.gridy++;
		// (1,2)
		gbc.gridwidth = 1;
		imageLabel = new ImageLabel("账号：", "/images/account.png");
		add(imageLabel, gbc);
		// (2,2)
		accountLabel = new JLabel();
		add(accountLabel, gbc);
		// (3,2)
		imageLabel = new ImageLabel("邮编：", "/images/email.png");
		add(imageLabel, gbc);
		// (4,2)
		zipCodeLabel = new JLabel();
		add(zipCodeLabel, gbc);

		gbc.gridy++;
		// (1,3)
		imageLabel = new ImageLabel("密码：", "/images/password.png");
		add(imageLabel, gbc);
		// (2,3)
		visibleBtn = new ImageButton("/images/visible.png");
		add(visibleBtn, gbc);
		// (3-4,3)
		gbc.gridwidth = 2;
		passwordLabel = new JLabel();
		add(passwordLabel, gbc);

		gbc.gridy++;
		// (1-2,4)
		imageLabel = new ImageLabel("电话：", "/images/tel.png");
		add(imageLabel, gbc);
		// (2-4,4)
		telLabel = new JLabel();
		add(telLabel, gbc);

		gbc.gridy++;
		// (1-2,5)
		imageLabel = new ImageLabel("地址：", "/images/address.png");
		add(imageLabel, gbc);
		// (2-4,5)
		addressLabel = new JLabel();
		add(addressLabel, gbc);
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
			passwordLabel.setText(supplier.getPassword());
			isPwdVisible = true;
		}
	}

	public void showSupplier(Supplier supplier) {
		this.supplier = supplier;
		nameLabel.setText(supplier.getName());
		accountLabel.setText(supplier.getAccount());
		zipCodeLabel.setText(supplier.getZipCode());
		telLabel.setText(supplier.getTel());
		addressLabel.setText(supplier.getAddress());
		passwordLabel.setText("******");
	}

	public Supplier getSupplier() {
		return supplier;
	}
}
