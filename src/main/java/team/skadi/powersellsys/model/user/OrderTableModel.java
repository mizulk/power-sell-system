package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.StringUtil;

import java.util.ArrayList;

public class OrderTableModel extends DataTableModel<Goods> {

	private final String[] columnName = new String[]{"名称", "型号", "单价", "折扣", "查看次数"};

	public OrderTableModel() {
		data = new ArrayList<>();
	}

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
		Goods order = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> order.getName();
			case 1 -> order.getModel();
			case 2 -> StringUtil.formatDiscountPrice(order.getPrice(), order.getDiscount());
			case 3 -> StringUtil.formatDiscount(order.getDiscount());
			case 4 -> order.getSum();
			default -> null;
		};
	}

}
