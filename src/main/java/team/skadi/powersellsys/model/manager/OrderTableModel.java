package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"用户id", "电源id", "订购数量", "订购金额", "订购日期"};

	private List<Order> data;

	public OrderTableModel() {
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
		Order order = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> order.getUserId();
			case 1 -> order.getPowerId();
			case 2 -> order.getAmount();
			case 3 -> order.getSum();
			case 4 -> order.getCreateTime();
			default -> null;
		};
	}
}
