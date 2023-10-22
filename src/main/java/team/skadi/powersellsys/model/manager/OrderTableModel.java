package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.utils.DateUtil;

import java.util.ArrayList;

public class OrderTableModel extends DataTableModel<Order> {

	private final String[] columnName = new String[]{"用户id", "电源id", "订购数量", "订购金额", "订购日期"};

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
		Order order = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> order.getUserId();
			case 1 -> order.getPowerId();
			case 2 -> order.getSum();
			case 3 -> order.getAmount();
			case 4 -> DateUtil.replaceT(order.getCreateTime());
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		return super.delRow(rowIndex);
	}
}
