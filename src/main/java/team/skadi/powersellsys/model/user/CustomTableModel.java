package team.skadi.powersellsys.model.user;


import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.StringUtil;

import java.util.ArrayList;


public class CustomTableModel extends DataTableModel<Order> {

	private final String[] columnName = new String[]{"电源id", "总数", "总价", "下单时间"};

	public CustomTableModel() {
		data = new ArrayList<>();
	}

	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getRowCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order custom = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> custom.getPowerId();
			case 1 -> StringUtil.formatInt(custom.getSum());
			case 2 -> custom.getAmount();
			case 3 -> DateUtil.replaceT(custom.getCreateTime());
			default -> null;
		};
	}
}
