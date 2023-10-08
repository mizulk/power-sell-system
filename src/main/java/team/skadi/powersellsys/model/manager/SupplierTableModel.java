package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SupplierTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"姓名", "电话", "邮编", "地址"};
	private List<Supplier> data;

	public SupplierTableModel() {
		data = new ArrayList<>();
	}

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
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
}
