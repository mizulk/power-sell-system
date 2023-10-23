package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.CustomTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserCustomPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel {

	private Order order;
	private CustomTableModel customTableModel;
	private PaginationPanel paginationPanel;
	private JTable customTable;

	public UserCustomPanel(App app) {
		super(app);
	}

	protected void init() {
		customTableModel = new CustomTableModel();
		order = new Order();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		customTableModel = new CustomTableModel();
		customTable = new JTable(customTableModel);
		customTable.setRowHeight(30);
		customTable.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(customTable), BorderLayout.CENTER);

		paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"电源id"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!customTableModel.hasData()) {
			OrderService orderService = ServiceUtil.getService(OrderService.class);
			PageBean<Order> orderPageBean = orderService.queryOrder(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(orderPageBean);
			customTableModel.updateData(orderPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(paginationPanel.getCurPage(), paginationPanel.getPageSize(), order);
		paginationPanel.setPageBean(orderPageBean);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	protected void addListener() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void firstPage(int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(1,pageSize, order);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(curPage,pageSize, order);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(curPage,pageSize, order);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(page,pageSize, order);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		OrderService orderService = ServiceUtil.getService(OrderService.class);
		PageBean<Order> orderPageBean = orderService.queryOrder(1,pageSize, order);
		customTableModel.updateData(orderPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		order = new Order();
		try {
			if (optionIndex == 0) {
				order.setPowerId(Integer.parseInt(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Order> orderPageBean = ServiceUtil.getService(OrderService.class).queryOrder(1, paginationPanel.getPageSize(), order);
		customTableModel.updateData(orderPageBean.getData());
		paginationPanel.setPageBean(orderPageBean);
		return orderPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;

	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Order> orderPageBean = ServiceUtil.getService(OrderService.class).queryOrder(1, paginationPanel.getPageSize(), order);
		customTableModel.updateData(orderPageBean.getData());
	}
}
