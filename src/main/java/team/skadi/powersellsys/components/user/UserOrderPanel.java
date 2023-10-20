package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.OrderTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserOrderPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel {

	private Goods goods;
	private OrderTableModel orderTableModel;
	private PaginationPanel paginationPanel;
	private JTable orderTable;

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
		JTable table = new JTable(orderTableModel);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);

		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		orderTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		double num ;
		try{
			num = Double.parseDouble(content);
		}catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		switch (optionIndex) {
			case 0 -> goods.setName(content);
			case 1 -> goods.setModel(content);
			case 2 -> goods.setPrice(num);
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
}
