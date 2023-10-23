package team.skadi.powersellsys.components.dialog.select;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.information.SupplierInformationPanel;
import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SelectSupplierDialog extends SelectDialog<Supplier> {

	public SelectSupplierDialog(JFrame frame) {
		super(frame, "选择供应商");
	}

	public SelectSupplierDialog(JDialog owner) {
		super(owner, "选择供应商");
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		SupplierService service = ServiceUtil.getService(SupplierService.class);
		PageBean<Supplier> supplierPageBean = service.querySupplier(curPage, paginationPanel.getPageSize(), null);
		dataTableModel.updateData(supplierPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		SupplierService service = ServiceUtil.getService(SupplierService.class);
		PageBean<Supplier> supplierPageBean = service.querySupplier(curPage, paginationPanel.getPageSize(), null);
		dataTableModel.updateData(supplierPageBean.getData());
	}

	@Override
	protected DataTableModel<Supplier> getTableModel() {
		return new DataTableModel<>() {
			private static final String[] columName = new String[]{"供应商id", "供应商名"};

			@Override
			public int getColumnCount() {
				return columName.length;
			}

			@Override
			public String getColumnName(int column) {
				return columName[column];
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Supplier supplier = data.get(rowIndex);
				return switch (columnIndex) {
					case 0 -> supplier.getId();
					case 1 -> supplier.getName();
					default -> null;
				};
			}
		};
	}

	@Override
	protected BasicComponent getInformationPanel() {
		SupplierInformationPanel supplierInformationPanel = new SupplierInformationPanel((App) getParent());
		supplierInformationPanel.showSupplier(dataTableModel.getRow(dataTable.getSelectedRow()));
		return supplierInformationPanel;
	}

	@Override
	protected int getSelectedValue() {
		return dataTableModel.getRow(dataTable.getSelectedRow()).getId();
	}

	@Override
	protected void initData() {
		SupplierService service = ServiceUtil.getService(SupplierService.class);
		PageBean<Supplier> supplierPageBean = service.querySupplier(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(supplierPageBean);
		dataTableModel.updateData(supplierPageBean.getData());
	}
}
