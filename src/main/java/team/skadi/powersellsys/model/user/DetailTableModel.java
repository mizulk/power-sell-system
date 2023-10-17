package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DetailTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"名称", "型号", "容量", "单价", "折扣"};
	private List<Goods> data;

	public DetailTableModel() {
		data = new ArrayList<>();
	}

	public String getColumnName(int column){
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
		Goods detail = data.get(rowIndex);
		return switch (columnIndex){
			case 0 -> detail.getName();
			case 1 -> detail.getModel();
			case 2 -> detail.getCapacity();
			case 3 -> detail.getPrice();
			case 4 -> detail.getDiscount();
			default -> null;
		};
	}
}