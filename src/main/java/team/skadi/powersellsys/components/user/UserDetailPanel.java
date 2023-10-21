package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.FavoriteDialog;
import team.skadi.powersellsys.model.user.DetailTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserDetailPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel, ListSelectionListener{

	private Goods goods;
	private DetailTableModel detailTableModel;
	private PaginationPanel paginationPanel;
	private JTable detailTable;
	private JButton favoriteBtn;
	private JButton evaluationBtn;


	public UserDetailPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		detailTableModel = new DetailTableModel();
		goods = new Goods();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		detailTableModel = new DetailTableModel();
		detailTable = new JTable(detailTableModel);
		detailTable.setRowHeight(30);
		detailTable.getTableHeader().setReorderingAllowed(false);
		detailTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		detailTable.getSelectionModel().addListSelectionListener(this);
		add(new JScrollPane(detailTable), BorderLayout.CENTER);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weightx = 1;

		favoriteBtn = new ImageButton("收藏", "/images/collect.png");
		favoriteBtn.setEnabled(false);
		btnPanel.add(favoriteBtn, gbc);

		evaluationBtn = new ImageButton("评论", "/images/comment.png");
		evaluationBtn.setEnabled(false);
		btnPanel.add(evaluationBtn, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		btnPanel.add(paginationPanel, gbc);
		add(btnPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"名称", "型号", "容量"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(goodsPageBean);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void refreshData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	protected void addListener() {
		favoriteBtn.addActionListener(this);
		evaluationBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == favoriteBtn){
			addFavorite();
		}
	}

	public void addFavorite() {
		FavoriteDialog favoriteDialog = new FavoriteDialog(app);
		favoriteDialog.getData();
	}

	@Override
	public void firstPage(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		try {
			switch (optionIndex) {
				case 0 -> goods.setName(content);
				case 1 -> goods.setModel(content);
				case 2 -> goods.setCapacity(Integer.parseInt(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		detailTableModel.updateData(goodsPageBean.getData());
		paginationPanel.setPageBean(goodsPageBean);
		return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), null);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = detailTable.getSelectedRow() != -1;
		favoriteBtn.setEnabled(b);
		evaluationBtn.setEnabled(b);
	}
}
