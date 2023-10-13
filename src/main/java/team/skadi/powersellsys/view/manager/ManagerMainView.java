package team.skadi.powersellsys.view.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.manager.ManageCommentPanel;
import team.skadi.powersellsys.components.manager.ManageGoodsPanel;
import team.skadi.powersellsys.components.manager.ManageOrderPanel;
import team.skadi.powersellsys.components.manager.ManageSupplierPanel;
import team.skadi.powersellsys.components.manager.ManageSupplyPanel;
import team.skadi.powersellsys.components.manager.ManageUserPanel;
import team.skadi.powersellsys.pojo.Manager;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainView extends BasicView implements ActionListener {

	private Manager manager;
	private JLabel jobNumberLabel;
	private JLabel nameLabel;
	private ImageButton logoutBtn;

	public ManagerMainView(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		add(getNorthPanel(), BorderLayout.NORTH);
		add(getCenter(), BorderLayout.CENTER);
	}

	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(Box.createHorizontalStrut(25));

		jobNumberLabel = new JLabel("工号：");
		panel.add(jobNumberLabel);
		panel.add(Box.createHorizontalGlue());

		nameLabel = new JLabel("姓名：");
		panel.add(nameLabel);
		panel.add(Box.createHorizontalGlue());

		logoutBtn = new ImageButton("logout", "/images/logout.png");
		logoutBtn.setTextPosition(ImageButton.RIGHT, ImageButton.CENTER);
		panel.add(logoutBtn);

		panel.add(Box.createHorizontalStrut(25));
		return panel;
	}

	private JTabbedPane getCenter() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(BorderFactory.createLoweredBevelBorder());
		tabbedPane.add("user", new ManageUserPanel(app));
		tabbedPane.add("supplier", new ManageSupplierPanel(app));
		tabbedPane.add("goods", new ManageGoodsPanel(app));
		tabbedPane.add("order", new ManageOrderPanel(app));
		tabbedPane.add("supply", new ManageSupplyPanel(app));
		tabbedPane.add("comment", new ManageCommentPanel(app));
		return tabbedPane;
	}

	protected void login(Manager manager) {
		this.manager = manager;
		jobNumberLabel.setText("工号：" + manager.getJobNumber());
		nameLabel.setText("姓名：" + manager.getName());
	}

	@Override
	protected void addListener() {
		logoutBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		app.useRouter().showPreviousView();
	}
}
