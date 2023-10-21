package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.ArrayList;

public class SupplierTableModel extends DataTableModel<Supplier> {

	private final String[] columnName = new String[]{"姓名", "电话", "邮编", "地址"};

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Supplier supplier = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> supplier.getName();
			case 1 -> supplier.getTel();
			case 2 -> supplier.getZipCode();
			case 3 -> supplier.getAddress();
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		return super.delRow(rowIndex);
	}
}
