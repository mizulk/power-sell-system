package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.information.SupplierInformationPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.SupplierDialog;
import team.skadi.powersellsys.model.manager.SupplierTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
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

public class ManageSupplierPanel extends ManagePanel {

	private Supplier supplier;
	private SupplierTableModel supplierTableModel;
	private JTable supplierTable;
	private JButton addSupplierBtn;
	private JButton delSupplierBtn;
	private JButton modifySupplierBtn;
	private JButton showSupplierBtn;
	private JButton refreshBtn;

	public ManageSupplierPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		supplierTableModel = new SupplierTableModel();
		supplier = new Supplier();
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"供应商账号", "供应商姓名", "供应商电话", "供应商地址"});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		supplierTable = new JTable(supplierTableModel);
		supplierTable.setRowHeight(30);
		supplierTable.getTableHeader().setReorderingAllowed(false);
		supplierTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierTable.getSelectionModel().addListSelectionListener(this);
		return supplierTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel supplierBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets.set(0, 20, 30, 20);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		addSupplierBtn = new JButton("添加供应商");
		supplierBtnPanel.add(addSupplierBtn, gbc);

		delSupplierBtn = new JButton("删除供应商");
		delSupplierBtn.setEnabled(false);
		supplierBtnPanel.add(delSupplierBtn, gbc);

		modifySupplierBtn = new JButton("修改供应商");
		modifySupplierBtn.setEnabled(false);
		supplierBtnPanel.add(modifySupplierBtn, gbc);

		showSupplierBtn = new JButton("查看供应商");
		showSupplierBtn.setEnabled(false);
		supplierBtnPanel.add(showSupplierBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		supplierBtnPanel.add(refreshBtn, gbc);
		return supplierBtnPanel;
	}

	@Override
	public void initData() {
		if (!supplierTableModel.hasData()) {
			SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
			PageBean<Supplier> supplierPageBean = supplierService.querySupplier(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(supplierPageBean);
			supplierTableModel.updateData(supplierPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
		PageBean<Supplier> supplierPageBean = supplierService.querySupplier(paginationPanel.getCurPage(), paginationPanel.getPageSize(), supplier);
		paginationPanel.setPageBean(supplierPageBean);
		supplierTableModel.updateData(supplierPageBean.getData());
		JOptionPane.showMessageDialog(app, "刷新成功！");
	}

	@Override
	protected void addListener() {
		addSupplierBtn.addActionListener(this);
		delSupplierBtn.addActionListener(this);
		modifySupplierBtn.addActionListener(this);
		showSupplierBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addSupplierBtn) {
			addSupplier();
		} else if (source == delSupplierBtn) {
			delUser();
		} else if (source == modifySupplierBtn) {
			modifySupplier();
		} else if (source == showSupplierBtn) {
			showSupplier();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void showSupplier() {
		BasicDialog basicDialog = new BasicDialog(app, "供应商资料") {
			@Override
			protected JComponent getCenterLayout() {
				SupplierInformationPanel supplierInformationPanel = new SupplierInformationPanel(app);
				supplierInformationPanel.showSupplier(supplierTableModel.getRow(supplierTable.getSelectedRow()));
				return supplierInformationPanel;
			}
		};
		basicDialog.getOption();
	}

	private void modifySupplier() {
		SupplierDialog supplierDialog = new SupplierDialog(app, EditDialog.MODIFY_MODE);
		supplierDialog.setData(supplierTableModel.getRow(supplierTable.getSelectedRow()));
		if (supplierDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			supplierTableModel.modifyRow(supplierTable.getSelectedRow(), supplierDialog.getData());
		}
	}

	private void delUser() {
		if (JOptionPane.showConfirmDialog(app, "你确定要删除这个供应商吗？") == JOptionPane.OK_OPTION) {
			if (supplierTableModel.delRow(supplierTable.getSelectedRowCount())) {
				JOptionPane.showMessageDialog(app, "删除成功");
			} else {
				JOptionPane.showMessageDialog(app, "删除失败");
			}
		} else {
			JOptionPane.showMessageDialog(app, "已取消");
		}
	}

	private void addSupplier() {
		SupplierDialog supplierDialog = new SupplierDialog(app, EditDialog.ADD_MODE);
		if (supplierDialog.getOption() == BasicDialog.CANCEL_OPTION) return;
		if (paginationPanel.isLastPage()
				&& paginationPanel.getLeftRecord() < paginationPanel.getPageSize())
			supplierTableModel.addRow(supplierDialog.getData());
		paginationPanel.incTotal();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = supplierTable.getSelectedRow() != -1;
		delSupplierBtn.setEnabled(b);
		modifySupplierBtn.setEnabled(b);
		showSupplierBtn.setEnabled(b);
	}

	@Override
	public void pageChange(int curPage, int pageSize) {
		SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
		PageBean<Supplier> supplierPageBean = supplierService.querySupplier(curPage, pageSize, supplier);
		supplierTableModel.updateData(supplierPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		supplier = new Supplier();
		switch (optionIndex) {
			case 0 -> supplier.setAccount(content);
			case 1 -> supplier.setName(content);
			case 2 -> supplier.setTel(content);
			case 3 -> supplier.setAddress(content);
		}
		PageBean<Supplier> supplierPageBean = ServiceUtil.getService(SupplierService.class).querySupplier(1, paginationPanel.getPageSize(), supplier);
		supplierTableModel.updateData(supplierPageBean.getData());
		paginationPanel.setPageBean(supplierPageBean);
		return supplierPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Supplier> supplierPageBean = ServiceUtil.getService(SupplierService.class).querySupplier(1, paginationPanel.getPageSize(), null);
		supplierTableModel.updateData(supplierPageBean.getData());
	}
}
