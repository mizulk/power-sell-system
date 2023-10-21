package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.OrderInformationPane;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.EditDialog;
import team.skadi.powersellsys.components.dialog.OrderDialog;
import team.skadi.powersellsys.model.manager.OrderTableModel;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageOrderPanel extends ManagePanel {

	private Order order;
	private OrderTableModel orderTableModel;
	private JTable orderTable;
	private JButton addOrderBtn;
	private JButton showOrderBtn;
	private JButton modifyOrderBtn;
	private JButton refreshBtn;

	public ManageOrderPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		orderTableModel = new OrderTableModel();
		order = new Order();
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户id", "电源id", "订购数量", "订购金额"});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		orderTable = new JTable(orderTableModel);
		orderTable.setRowHeight(30);
		orderTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderTable.getSelectionModel().addListSelectionListener(this);
		return orderTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel orderBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets.set(0, 20, 30, 20);
		gbc.gridx = 1;

		addOrderBtn = new JButton("添加订单");
		orderBtnPanel.add(addOrderBtn, gbc);

		showOrderBtn = new JButton("查看订单");
		showOrderBtn.setEnabled(false);
		orderBtnPanel.add(showOrderBtn, gbc);

		modifyOrderBtn = new JButton("修正订单");
		modifyOrderBtn.setEnabled(false);
		orderBtnPanel.add(modifyOrderBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		orderBtnPanel.add(refreshBtn, gbc);

		return orderBtnPanel;
	}

	@Override
	public void initData() {
		if (!orderTableModel.hasData()) {
			OrderService orderService = ServiceUtil.getService(OrderService.class);
			PageBean<Order> orderPageBean = orderService.queryOrder(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(orderPageBean);
			orderTableModel.updateData(orderPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(paginationPanel.getCurPage(), paginationPanel.getPageSize(), order);
		paginationPanel.setPageBean(orderPageBean);
		orderTableModel.updateData(orderPageBean.getData());
	}

	@Override
	protected void addListener() {
		addOrderBtn.addActionListener(this);
		showOrderBtn.addActionListener(this);
		modifyOrderBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addOrderBtn) {
			addOrder();
		} else if (source == showOrderBtn) {
			showOrder();
		} else if (source == modifyOrderBtn) {
			modifyOrder();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void showOrder() {
		BasicDialog basicDialog = new BasicDialog(app, "商品详细信息") {
			@Override
			protected JComponent getCenterLayout() {
				OrderInformationPane orderInformationPane = new OrderInformationPane(app);
				orderInformationPane.showOrder(orderTableModel.getRow(orderTable.getSelectedRow()));
				return orderInformationPane;
			}
		};
		basicDialog.getOption();
	}

	private void modifyOrder() {
		OrderDialog orderDialog = new OrderDialog(app, EditDialog.MODIFY_MODE);
		orderDialog.setData(orderTableModel.getRow(orderTable.getSelectedRow()));
		if (orderDialog.getOption() == BasicDialog.CONFIRM_OPTION)
			orderTableModel.modifyRow(orderTable.getSelectedRow(), orderDialog.getData());
	}

	private void addOrder() {
		OrderDialog orderDialog = new OrderDialog(app, EditDialog.ADD_MODE);
		if (orderDialog.getOption() == BasicDialog.CONFIRM_OPTION
				&& paginationPanel.isLastPage()
				&& paginationPanel.getLeftRecord() < paginationPanel.getPageSize()) {
			orderTableModel.addRow(orderDialog.getData());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = orderTable.getSelectedRow() != -1;
		showOrderBtn.setEnabled(b);
		modifyOrderBtn.setEnabled(b);
	}

	@Override
	public void firstPage(int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(1, pageSize, order);
		orderTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(curPage, pageSize, order);
		orderTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(curPage, pageSize, order);
		orderTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(page, pageSize, order);
		orderTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		order = new Order();
		try {
			switch (optionIndex) {
				case 1 -> order.setUserId(Integer.parseInt(content));
				case 2 -> order.setPowerId(Integer.parseInt(content));
				case 3 -> order.setSum(Integer.parseInt(content));
				case 4 -> order.setAmount(Float.parseFloat(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Order> orderPageBean = ServiceUtil.getService(OrderService.class).queryOrder(1, paginationPanel.getPageSize(), order);
		orderTableModel.updateData(orderPageBean.getData());
		paginationPanel.setPageBean(orderPageBean);
		return orderPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(1, paginationPanel.getPageSize(), null);
		orderTableModel.updateData(orderPageBean.getData());
	}
}
