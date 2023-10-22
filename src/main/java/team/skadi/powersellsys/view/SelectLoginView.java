package team.skadi.powersellsys.view;

import lombok.extern.slf4j.Slf4j;
import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.router.ViewName;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class SelectLoginView extends BasicView implements ActionListener {

	private ImageButton manager;
	private ImageButton supplier;
	private ImageButton user;
	private ImageButton helpBtn;

	public SelectLoginView(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());

		// 将构建中心部分的内容抽象成一个方法有利于代码简洁，方便维护
		add(getCenterPanel(), BorderLayout.CENTER);

		add(getSouthPanel(), BorderLayout.SOUTH);
	}

	private JPanel getSouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		JLabel label = new JLabel("2023.09.18-2023");
		southPanel.add(label);

		southPanel.add(Box.createHorizontalGlue());

		helpBtn = new ImageButton("帮助", "/images/help.png");
		helpBtn.setTextPosition(ImageButton.RIGHT, ImageButton.CENTER);
		southPanel.add(helpBtn);
		return southPanel;
	}

	private JPanel getCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// (1~3, 1)
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.insets.set(0, 0, 20, 0);
		JLabel title = new JLabel("欢迎使用琨坤电力");
		title.setFont(Main.TITLE_FONT);
		centerPanel.add(title, gbc);

		//(1~3, 2)
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel subTitle = new JLabel("请选择您的登录身份：");
		centerPanel.add(subTitle, gbc);


		Dimension preferredSize = new Dimension(120, 120);

		//(1, 3)
		gbc.gridy++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets.set(0, 0, 0, 80);//0,0,0,50
		user = new ImageButton("用户", "/images/user.png");
		user.setTextPosition(ImageButton.CENTER, ImageButton.BOTTOM);
		user.setPreferredSize(preferredSize);
		centerPanel.add(user, gbc);

		//(2, 3)
		gbc.gridx++;
		gbc.insets.right = 0;//0,0,0,0
		supplier = new ImageButton("供应商", "/images/supplier.png");
		supplier.setTextPosition(ImageButton.CENTER, ImageButton.BOTTOM);
		supplier.setPreferredSize(preferredSize);
		centerPanel.add(supplier, gbc);

		//(3, 3)
		gbc.gridx++;
		gbc.insets.left = 80;
		manager = new ImageButton("管理员", "/images/manager.png");
		manager.setTextPosition(ImageButton.CENTER, ImageButton.BOTTOM);
		manager.setPreferredSize(preferredSize);
		centerPanel.add(manager, gbc);
		return centerPanel;
	}

	@Override
	protected void addListener() {
		user.addActionListener(this);
		supplier.addActionListener(this);
		manager.addActionListener(this);
		helpBtn.addActionListener(this);
	}

	@Override
	public void onShow() {
		log.info("选择登录页面被展示");
	}

	@Override
	public void onHide() {
		log.info("选择登录页面被隐藏");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == user) {
			app.useRouter().showView(ViewName.SELECT_LOGIN_VIEW, ViewName.USER_LOGIN_VIEW);
		} else if (source == supplier) {
			app.useRouter().showView(ViewName.SELECT_LOGIN_VIEW, ViewName.SUPPLIER_LOGIN_VIEW);
		} else if (source == manager) {
			app.useRouter().showView(ViewName.SELECT_LOGIN_VIEW, ViewName.MANAGER_LOGIN_VIEW);
		} else if (source == helpBtn) {
			app.useRouter().showView(ViewName.SELECT_LOGIN_VIEW, ViewName.HELP_VIEW);
		}
	}
}
