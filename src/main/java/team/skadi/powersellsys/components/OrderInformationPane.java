package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class OrderInformationPane extends BasicComponent {

	private Order order;
	private JLabel userNameLabel;
	private JLabel powerNameLabel;
	private JLabel sumLabel;
	private JLabel amountLabel;
	private JLabel createTimeLabel;

	public OrderInformationPane(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets.set(0, 0, 10, 10);
		ImageLabel imageLabel;
		gbc.gridy = 1;
		// (1,1)
		imageLabel = new ImageLabel("用户名: ", "/images/user.png");
		add(imageLabel, gbc);
		// (2,1)
		userNameLabel = new JLabel();
		add(userNameLabel, gbc);

		// (3,1)
		imageLabel = new ImageLabel("电源名称：", "/images/goods.png");
		add(imageLabel, gbc);
		// (4,1)
		powerNameLabel = new JLabel();
		add(powerNameLabel, gbc);

		gbc.gridy++;
		// (1,2)
		imageLabel = new ImageLabel("订购数量：", "/images/sum.png");
		add(imageLabel, gbc);
		// (2,2)
		sumLabel = new JLabel();
		add(sumLabel, gbc);

		// (3,2)
		imageLabel = new ImageLabel("金额：", "/images/balance.png");
		add(imageLabel, gbc);
		// (4,2)
		amountLabel = new JLabel();
		add(amountLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		// (1-2,3)
		imageLabel = new ImageLabel("创建时间：", "/images/time.png");
		add(imageLabel, gbc);
		// (2-4,3)
		createTimeLabel = new JLabel();
		add(createTimeLabel, gbc);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void showOrder(Order order) {
		this.order = order;
		String userName = ServiceUtil.getService(UserService.class).getUserNameById(order.getUserId());
		String powerName = ServiceUtil.getService(GoodsService.class).getGoodsNameById(order.getPowerId());
		userNameLabel.setText(userName);
		powerNameLabel.setText(powerName);
		sumLabel.setText(String.valueOf(order.getSum()));
		amountLabel.setText(String.valueOf(order.getAmount()));
		createTimeLabel.setText(DateUtil.replaceT(order.getCreateTime()));
	}

	public Order getOrder() {
		return order;
	}
}
