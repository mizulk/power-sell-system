package team.skadi.powersellsys.view.manager;

import lombok.extern.slf4j.Slf4j;
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
import team.skadi.powersellsys.service.ManagerService;
import team.skadi.powersellsys.utils.ServiceUtil;
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

@Slf4j
public class ManagerMainView extends BasicView implements ActionListener, ChangeListener {

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
		tabbedPane.add("用户(Users)", new ManageUserPanel(app));
		tabbedPane.add("供应商(Suppliers)", new ManageSupplierPanel(app));
		tabbedPane.add("商品(Goods)", new ManageGoodsPanel(app));
		tabbedPane.add("订单(Orders)", new ManageOrderPanel(app));
		tabbedPane.add("供应订单(Supplies)", new ManageSupplyPanel(app));
		tabbedPane.add("评论(Comments)", new ManageCommentPanel(app));
		return tabbedPane;
	}

	@Override
	protected void addListener() {
		logoutBtn.addActionListener(this);
		tabbedPane.addChangeListener(this);
	}

	@Override
	public void onShow() {
		log.info("管理员主页面被展示");
		ManagerService managerService = ServiceUtil.getService(ManagerService.class);
		Manager manager = managerService.queryManager(app.useStore().managerStore.jobNumber());
		jobNumberLabel.setText("工号：" + manager.getJobNumber());
		nameLabel.setText("姓名：" + manager.getName());
		((ManagePanel) tabbedPane.getComponentAt(0)).initData();
	}

	@Override
	public void onHide() {
		log.info("管理员主页面被隐藏");
		app.useStore().managerStore = null;// 管理员登出，清空保存的记录
		tabbedPane.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		app.useRouter().showPreviousView(); // 登出按钮只会触发放回上一个页面
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		ManagePanel managePanel = (ManagePanel) tabbedPane.getSelectedComponent();
		managePanel.initData();
	}
}
