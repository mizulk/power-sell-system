package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.StringUtil;

import java.util.ArrayList;

public class DetailTableModel extends DataTableModel<Goods> {

	private final String[] columnName = new String[]{"名称", "电源id", "型号", "容量mA•h", "单价", "折扣"};

	public DetailTableModel() {
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
		Goods detail = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> detail.getName();
			case 1 -> detail.getId();
			case 2 -> detail.getModel();
			case 3 -> StringUtil.INTEGER_FORMAT.format(detail.getCapacity());
			case 4 -> StringUtil.formatDiscountPrice(detail.getPrice(), detail.getDiscount());
			case 5 -> detail.getDiscount() == 0 ? "无" : String.format("-%d%%off", detail.getDiscount());
			default -> null;
		};
	}
}
