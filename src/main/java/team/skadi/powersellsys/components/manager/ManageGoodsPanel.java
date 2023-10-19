package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.manager.GoodsTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageGoodsPanel extends ManagePanel {

	private Goods goods;
	private GoodsTableModel goodsTableModel;
	private JTable goodsTable;
	private JButton onShelfBtn;
	private JButton offShelfBtn;
	private JButton modifyGoodsBtn;
	private JButton showGoodsBtn;
	private JButton refreshBtn;

	public ManageGoodsPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		goodsTableModel = new GoodsTableModel();
		goods = new Goods();
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{""});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		goodsTableModel = new GoodsTableModel();
		goodsTable = new JTable(goodsTableModel);
		goodsTable.setRowHeight(30);
		goodsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		goodsTable.getSelectionModel().addListSelectionListener(this);
		return goodsTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel goodsBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.insets.set(0, 20, 30, 20);

		onShelfBtn = new JButton("下架商品");
		goodsBtnPanel.add(onShelfBtn, gbc);

		offShelfBtn = new JButton("上架商品");
		offShelfBtn.setEnabled(false);
		goodsBtnPanel.add(offShelfBtn, gbc);

		modifyGoodsBtn = new JButton("修正商品");
		modifyGoodsBtn.setEnabled(false);
		goodsBtnPanel.add(modifyGoodsBtn, gbc);

		showGoodsBtn = new JButton("查看商品");
		showGoodsBtn.setEnabled(false);
		goodsBtnPanel.add(showGoodsBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		goodsBtnPanel.add(refreshBtn, gbc);
		return goodsBtnPanel;
	}

	@Override
	public void initData() {
		if (!goodsTableModel.hasData()) {
			GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
			PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(goodsPageBean);
			goodsTableModel.updateData(goodsPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		goodsTableModel.updateData(goodsPageBean.getData());
		JOptionPane.showMessageDialog(app, "刷新成功！");
	}

	@Override
	protected void addListener() {
		onShelfBtn.addActionListener(this);
		offShelfBtn.addActionListener(this);
		modifyGoodsBtn.addActionListener(this);
		showGoodsBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == onShelfBtn) {
			onShelf();
		} else if (source == offShelfBtn) {
			offShelf();
		} else if (source == modifyGoodsBtn) {
			modifyGoods();
		} else if (source == showGoodsBtn) {
			showGoods();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void onShelf() {

	}

	private void offShelf() {

	}

	private void modifyGoods() {

	}

	private void showGoods() {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void firstPage(int pageSize) {

	}

	@Override
	public void nextPage(int curPage, int pageSize) {

	}

	@Override
	public void previousPage(int curPage, int pageSize) {

	}

	@Override
	public void jumpTo(int page, int pageSize) {

	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		return null;
	}

	@Override
	public void onCloseButtonCLick() {

	}
}
