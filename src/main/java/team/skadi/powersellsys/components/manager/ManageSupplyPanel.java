package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.information.SupplyInformationPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.SupplyDialog;
import team.skadi.powersellsys.model.manager.SupplyTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageSupplyPanel extends ManagePanel {

	private Supply supply;

	private SupplyTableModel supplyTableModel;
	private JTable supplyTable;
	private JButton addSupplyBtn;
	private JButton showSupplyBtn;
	private JButton modifySupplyBtn;
	private JButton refreshBtn;

	public ManageSupplyPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		supplyTableModel = new SupplyTableModel();
		supply = new Supply();
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"供应商id", "电源id", "供应数量"});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		supplyTable = new JTable(supplyTableModel);
		supplyTable.setRowHeight(30);
		supplyTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplyTable.getSelectionModel().addListSelectionListener(this);
		return supplyTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel supplyBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.insets.set(0, 20, 30, 20);

		addSupplyBtn = new JButton("添加订单");
		supplyBtnPanel.add(addSupplyBtn, gbc);

		showSupplyBtn = new JButton("查看订单");
		showSupplyBtn.setEnabled(false);
		supplyBtnPanel.add(showSupplyBtn, gbc);

		modifySupplyBtn = new JButton("修正订单");
		modifySupplyBtn.setEnabled(false);
		supplyBtnPanel.add(modifySupplyBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		supplyBtnPanel.add(refreshBtn, gbc);
		return supplyBtnPanel;
	}

	@Override
	public void initData() {
		if (!supplyTableModel.hasData()) {
			SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
			PageBean<Supply> supplyPageBean = supplyService.querySupply(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(supplyPageBean);
			supplyTableModel.updateData(supplyPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(paginationPanel.getCurPage(), paginationPanel.getPageSize(), supply);
		paginationPanel.setPageBean(supplyPageBean);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	protected void addListener() {
		addSupplyBtn.addActionListener(this);
		showSupplyBtn.addActionListener(this);
		modifySupplyBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addSupplyBtn) {
			addSupply();
		} else if (source == showSupplyBtn) {
			showSupply();
		} else if (source == modifySupplyBtn) {
			modifySupply();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void showSupply() {
		BasicDialog basicDialog = new BasicDialog(app, "供应订单详细信息") {
			@Override
			protected JComponent getCenterLayout() {
				SupplyInformationPanel supplyInformationPanel = new SupplyInformationPanel(app);
				supplyInformationPanel.showSupply(supplyTableModel.getRow(supplyTable.getSelectedRow()));
				return supplyInformationPanel;
			}
		};
		basicDialog.getOption();
	}

	private void modifySupply() {
		SupplyDialog supplyDialog = new SupplyDialog(app, EditDialog.MODIFY_MODE);
		supplyDialog.setData(supplyTableModel.getRow(supplyTable.getSelectedRow()));
		if (supplyDialog.getOption() == BasicDialog.CONFIRM_OPTION)
			supplyTableModel.modifyRow(supplyTable.getSelectedRow(), supplyDialog.getData());
	}

	private void addSupply() {
		SupplyDialog supplyDialog = new SupplyDialog(app, EditDialog.ADD_MODE);
		if (supplyDialog.getOption() == BasicDialog.CONFIRM_OPTION
				&& paginationPanel.isLastPage()
				&& paginationPanel.getLeftRecord() < paginationPanel.getPageSize())
			supplyTableModel.addRow(supply);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = supplyTable.getSelectedRow() != -1;
		showSupplyBtn.setEnabled(b);
		modifySupplyBtn.setEnabled(b);
	}

	@Override
	public void firstPage(int pageSize) {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(1, pageSize, supply);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(curPage, pageSize, supply);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(curPage, pageSize, supply);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(page, pageSize, supply);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(1, pageSize, supply);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		supply = new Supply();
		try {
			switch (optionIndex) {
				case 0 -> supply.setSupplierId(Integer.parseInt(content));
				case 1 -> supply.setPowerId(Integer.parseInt(content));
				case 2 -> supply.setSum(Integer.parseInt(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Supply> supplyPageBean = ServiceUtil.getService(SupplyService.class).querySupply(1, paginationPanel.getPageSize(), supply);
		paginationPanel.setPageBean(supplyPageBean);
		supplyTableModel.updateData(supplyPageBean.getData());
		return supplyPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = supplyService.querySupply(1, paginationPanel.getPageSize(), null);
		supplyTableModel.updateData(supplyPageBean.getData());
	}
}
