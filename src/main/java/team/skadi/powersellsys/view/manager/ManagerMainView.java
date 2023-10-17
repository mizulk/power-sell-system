package team.skadi.powersellsys.view.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.manager.ManageCommentPanel;
import team.skadi.powersellsys.components.manager.ManageGoodsPanel;
import team.skadi.powersellsys.components.manager.ManageOrderPanel;
import team.skadi.powersellsys.components.manager.ManagePanel;
import team.skadi.powersellsys.components.manager.ManageSupplierPanel;
import team.skadi.powersellsys.components.manager.ManageSupplyPanel;
import team.skadi.powersellsys.components.manager.ManageUserPanel;
import team.skadi.powersellsys.pojo.Manager;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainView extends BasicView implements ActionListener, ChangeListener {

	private Manager manager;
	private JLabel jobNumberLabel;
	private JLabel nameLabel;
	private ImageButton logoutBtn;
	private JTabbedPane tabbedPane;

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
		tabbedPane = new JTabbedPane();
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
		((ManagePanel) tabbedPane.getComponentAt(0)).initData();
	}

	@Override
	protected void addListener() {
		logoutBtn.addActionListener(this);
		tabbedPane.addChangeListener(this);
	}

	@Override
	public void onShow() {
		System.out.println("ManagerMainView.onShow");
	}

	@Override
	public void onHide() {
		System.out.println("ManagerMainView.onHide");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		app.useRouter().showPreviousView();
		tabbedPane.setSelectedIndex(0);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		ManagePanel managePanel = (ManagePanel) tabbedPane.getSelectedComponent();
		managePanel.initData();
	}
}
