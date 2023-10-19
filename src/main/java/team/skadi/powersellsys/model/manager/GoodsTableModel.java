package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;

import java.util.ArrayList;

public class GoodsTableModel extends DataTableModel<Goods> {

	private final String[] columnName = new String[]{"名称", "型号", "容量", "库存", "单价", "折扣", "状态"};

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

	@Override
	public boolean delRow(int rowIndex) {
		return super.delRow(rowIndex);
	}
}
