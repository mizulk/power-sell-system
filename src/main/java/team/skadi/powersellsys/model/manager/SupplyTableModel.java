package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.Supply;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SupplyTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"供应商id", "电源id", "供应数量", "创建日期", "供应日期"};

	private List<Supply> data;

	public SupplyTableModel() {
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
		Supply supply = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> supply.getSupplierId();
			case 1 -> supply.getPowerId();
			case 2 -> supply.getSum();
			case 3 -> supply.getCreateTime();
			case 4 -> supply.getSupplyTime() == null ? "尚未供应" : supply.getSupplyTime();
			default -> null;
		};
	}
}
