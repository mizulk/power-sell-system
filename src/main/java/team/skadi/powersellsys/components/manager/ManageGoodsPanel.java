package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.information.GoodsInformationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.GoodsDialog;
import team.skadi.powersellsys.model.manager.GoodsTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
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
	private JButton addGoodsBtn;

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
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"商品名称", "面向市场", "型号", "容量", "库存", "价格"});
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

		addGoodsBtn = new JButton("添加商品");
		goodsBtnPanel.add(addGoodsBtn, gbc);

		onShelfBtn = new JButton("上架商品");
		onShelfBtn.setEnabled(false);
		goodsBtnPanel.add(onShelfBtn, gbc);

		offShelfBtn = new JButton("下架商品");
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
		addGoodsBtn.addActionListener(this);
		onShelfBtn.addActionListener(this);
		offShelfBtn.addActionListener(this);
		modifyGoodsBtn.addActionListener(this);
		showGoodsBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addGoodsBtn) {
			addGoods();
		} else if (source == onShelfBtn) {
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

	private void addGoods() {
		GoodsDialog goodsDialog = new GoodsDialog(app, EditDialog.ADD_MODE);
		if (goodsDialog.getOption() == BasicDialog.CONFIRM_OPTION && paginationPanel.isLastPage() && paginationPanel.getLeftRecord() < paginationPanel.getPageSize()) {
			goodsTableModel.addRow(goodsDialog.getData());
		}
	}

	private void onShelf() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		int selectedRow = goodsTable.getSelectedRow();
		Goods selectedGoods = goodsTableModel.getRow(selectedRow);
		goodsService.putOnShelf(selectedGoods);
		goodsTableModel.modifyRow(selectedRow, selectedGoods);
		JOptionPane.showMessageDialog(app, selectedGoods.getName() + " 上架成功");
	}

	private void offShelf() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		int selectedRow = goodsTable.getSelectedRow();
		Goods selectedGoods = goodsTableModel.getRow(selectedRow);
		goodsService.putOffShelf(selectedGoods);
		goodsTableModel.modifyRow(selectedRow, selectedGoods);
		JOptionPane.showMessageDialog(app, selectedGoods.getName() + " 下架成功");
	}

	private void modifyGoods() {
		GoodsDialog goodsDialog = new GoodsDialog(app, EditDialog.MODIFY_MODE);
		goodsDialog.setData(goodsTableModel.getRow(goodsTable.getSelectedRow()));
		if (goodsDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			goodsTableModel.modifyRow(goodsTable.getSelectedRow(), goodsDialog.getData());
		}
	}

	private void showGoods() {
		BasicDialog basicDialog = new BasicDialog(app, "商品详细信息") {
			@Override
			protected JComponent getCenterLayout() {
				GoodsInformationPanel goodsInformationPanel = new GoodsInformationPanel(app);
				goodsInformationPanel.showGoods(goodsTableModel.getRow(goodsTable.getSelectedRow()));
				return goodsInformationPanel;
			}
		};
		basicDialog.getOption();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = goodsTable.getSelectedRow() != -1;
		onShelfBtn.setEnabled(b && goodsTableModel.getRow(goodsTable.getSelectedRow()).getStatus() == 0);
		offShelfBtn.setEnabled(b && goodsTableModel.getRow(goodsTable.getSelectedRow()).getStatus() == 1);
		modifyGoodsBtn.setEnabled(b);
		showGoodsBtn.setEnabled(b);
	}

	@Override
	public void firstPage(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		goodsTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		goodsTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		goodsTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(page, pageSize, goods);
		goodsTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		goodsTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		try {
			switch (optionIndex) {
				case 0 -> goods.setName(content);
				case 1 -> goods.setType(content);
				case 2 -> goods.setModel(content);
				case 3 -> goods.setCapacity(Integer.parseInt(content));
				case 4 -> goods.setStock(Integer.parseInt(content));
				case 5 -> goods.setPrice(Float.parseFloat(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		goodsTableModel.updateData(goodsPageBean.getData());
		paginationPanel.setPageBean(goodsPageBean);
		return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
		goodsTableModel.updateData(goodsPageBean.getData());
	}
}
