package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.UserOrderDialog;
import team.skadi.powersellsys.model.user.OrderTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserOrderPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel, ListSelectionListener {

	private Goods goods;
	private OrderTableModel orderTableModel;
	private PaginationPanel paginationPanel;
	private JTable orderTable;
	private JButton orderBtn;

	public UserOrderPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		orderTableModel = new OrderTableModel();
		goods = new Goods();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		orderTableModel = new OrderTableModel();
		orderTable = new JTable(orderTableModel);
		orderTable.setRowHeight(30);
		orderTable.getTableHeader().setReorderingAllowed(false);
		orderTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderTable.getSelectionModel().addListSelectionListener(this);
		add(new JScrollPane(orderTable), BorderLayout.CENTER);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weightx = 1;

		orderBtn = new ImageButton("下单", "/images/order.png");
		orderBtn.setEnabled(false);
		btnPanel.add(orderBtn, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
		btnPanel.add(paginationPanel, gbc);
		add(btnPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"名称", "型号", "单价"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!orderTableModel.hasData()) {
			GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
			PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(goodsPageBean);
			orderTableModel.updateData(goodsPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	protected void addListener() {
		orderBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == orderBtn) {
			addOrder();
		}
	}

	public void addOrder() {
		UserOrderDialog userOrderDialog = new UserOrderDialog(app,"下单",UserOrderDialog.ADD_MODE);
		Goods row = orderTableModel.getRow(orderTable.getSelectedRow());
		Order order = new Order();
		order.setPowerId(row.getId());
		order.setUserId(app.useStore().userStore.id());
		order.setAmount(row.getPrice());
		order.setSum(1);
		userOrderDialog.setData(order);
		if (userOrderDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			JOptionPane.showMessageDialog(app,"下单成功");
		} else {
			JOptionPane.showMessageDialog(app,"已取消");
		}
	}

	@Override
	public void firstPage(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(page, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		try {
			switch (optionIndex) {
				case 0 -> goods.setName(content);
				case 1 -> goods.setModel(content);
				case 2 -> goods.setPrice(Float.parseFloat(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		orderTableModel.updateData(goodsPageBean.getData());
		paginationPanel.setPageBean(goodsPageBean);
		return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), null);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = orderTable.getSelectedRow() != -1;
		orderBtn.setEnabled(b);
	}
}
