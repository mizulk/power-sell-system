package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GoodsTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"名称", "型号", "容量", "库存", "单价", "折扣", "状态"};

	private List<Goods> data;

	public GoodsTableModel() {
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
		Goods goods = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> goods.getName();
			case 1 -> goods.getModel();
			case 2 -> goods.getCapacity();
			case 3 -> goods.getStock();
			case 4 -> goods.getPrice();
			case 5 -> goods.getDiscount();
			case 6 -> goods.getStatus();
			default -> null;
		};
	}
}
