package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.CustomTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserCustomPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel {

	private Goods goods;
	private CustomTableModel customTableModel;
	private PaginationPanel paginationPanel;
	private JTable customTable;

	public UserCustomPanel(App app) {
		super(app);
	}

	protected void init() {
		customTableModel = new CustomTableModel();
		goods = new Goods();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		customTableModel = new CustomTableModel();
		JTable table = new JTable(customTableModel);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);

		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"容量", "型号"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!customTableModel.hasData()) {
			GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
			PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(goodsPageBean);
			customTableModel.updateData(goodsPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		customTableModel.updateData(goodsPageBean.getData());
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
		customTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		customTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		customTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		customTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		try {
			switch (optionIndex) {
				case 0 -> goods.setCapacity(Integer.parseInt(content));
				case 1 -> goods.setModel(content);
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		customTableModel.updateData(goodsPageBean.getData());
		paginationPanel.setPageBean(goodsPageBean);
		return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;

	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), null);
		customTableModel.updateData(goodsPageBean.getData());
	}
}
