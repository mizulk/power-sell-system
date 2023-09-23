package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.components.ImageButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLoginView extends BasicView implements ActionListener {

	private ImageButton manager;
	private ImageButton supplier;
	private ImageButton user;

	public SelectLoginView(App app) {
		super(app);
	}

	@Override
	void buildLayout() {
		setLayout(new BorderLayout());

		add(getCenterPanel(), BorderLayout.CENTER);

		add(getSouthPanel(), BorderLayout.SOUTH);
	}

	private JPanel getSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("2023-2023");
		southPanel.add(label);
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
		JLabel title = new JLabel("欢迎使用");
		title.setFont(Main.TITLE_FONT);
		centerPanel.add(title, gbc);

		//(2~3, 2)
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel subTitle = new JLabel("请选择：");
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
	void addListener() {
		user.addActionListener(this);
		supplier.addActionListener(this);
		manager.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}