package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.SupplierSupplyDialog;
import team.skadi.powersellsys.model.supplier.SupplyTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class ShowOrdersPanel extends SupplierPanel
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, ListSelectionListener {

	private JButton btn;
	private SupplyTableModel supplyTableModel;
	private JTable table;
	private JButton btn2;
	private Supply supply;
	private PaginationPanel paginationPanel;

	public ShowOrdersPanel(App app) {
		super(app);
	}

	@Override
	public void initData() {
		if (!supplyTableModel.hasData()) {
			supply.setSupplierId(app.useStore().supplierStore.id());
		}
		SupplyService service = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = service.querySupply(1, paginationPanel.getPageSize(), supply);
		paginationPanel.setPageBean(supplyPageBean);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	public void refreshData() {
		SupplyService service = ServiceUtil.getService(SupplyService.class);
		PageBean<Supply> supplyPageBean = service.querySupply(1, paginationPanel.getPageSize(), supply);
		paginationPanel.setPageBean(supplyPageBean);
		supplyTableModel.updateData(supplyPageBean.getData());
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		supplyTableModel = new SupplyTableModel();
		table = new JTable(supplyTableModel);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel btnpanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weightx = 1;
		btn = new JButton("添加新的库存");
		btnpanel.add(btn, gbc);

		btn2 = new JButton("供应所选项");
		btn2.setEnabled(false);
		btnpanel.add(btn2, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
		btnpanel.add(paginationPanel, gbc);
		add(btnpanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"电源id", "供应数量", "供应日期"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	protected void addListener() {
		btn.addActionListener(this);
		btn2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btn) { // 添加新的库存
			addNewSupply();
		} else {
			modifySupply();
		}
	}

	private void modifySupply() {
		SupplierSupplyDialog supplierSupplyDialog = new SupplierSupplyDialog(app, EditDialog.MODIFY_MODE);
		supplierSupplyDialog.setData(supplyTableModel.getRow(table.getSelectedRow()));
		if (supplierSupplyDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			JOptionPane.showMessageDialog(app, "供应成功");
			supplyTableModel.modifyRow(table.getSelectedRow(), supplierSupplyDialog.getData());
		}
	}

	private void addNewSupply() {
		SupplierSupplyDialog supplierSupplyDialog = new SupplierSupplyDialog(app, EditDialog.ADD_MODE);
		Supply supply1 = new Supply();
		supply1.setSupplierId(app.useStore().supplierStore.id());
		supply1.setSum(1);
		supplierSupplyDialog.setData(supply1);
		if (supplierSupplyDialog.getOption() == BasicDialog.CANCEL_OPTION) return;
		JOptionPane.showMessageDialog(app, "添加成功");
		if (paginationPanel.isLastPage()
				&& paginationPanel.getLeftRecord() < paginationPanel.getPageSize())
			supplyTableModel.addRow(supplierSupplyDialog.getData());
		paginationPanel.incTotal();
	}

	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		supply = new Supply();
		supply.setSupplierId(app.useStore().supplierStore.id());
		try {
			switch (optionIndex) {
				case 0 -> supply.setPowerId(Integer.parseInt(content));
				case 1 -> supply.setSum(Integer.parseInt(content));
				case 2 -> supply.setSupplyTime(LocalDateTime.parse(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Supply> supplys = ServiceUtil.getService(SupplyService.class).querySupply(1, paginationPanel.getPageSize(), supply);
		supplyTableModel.updateData(supplys.getData());
		paginationPanel.setPageBean(supplys); // 更新分页面板
		return supplys.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Supply> supplyPageBean = ServiceUtil.getService(SupplyService.class).querySupply(1, paginationPanel.getPageSize(), supply);
		supplyTableModel.updateData(supplyPageBean.getData());
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
	public void valueChanged(ListSelectionEvent e) {
		boolean b = table.getSelectedRow() != -1;
		btn2.setEnabled(b);
	}
}
