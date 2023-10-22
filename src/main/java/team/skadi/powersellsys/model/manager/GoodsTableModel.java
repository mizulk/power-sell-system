package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.StringUtil;

public class GoodsTableModel extends DataTableModel<Goods> {

	private final String[] columnName = new String[]{"名称", "型号", "容量mA•h", "库存", "单价", "折扣", "状态"};

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
		Goods goods = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> goods.getName();
			case 1 -> goods.getModel();
			case 2 -> StringUtil.INTEGER_FORMAT.format(goods.getCapacity());
			case 3 -> goods.getStock();
			case 4 -> StringUtil.formatDiscountPrice(goods.getPrice(), goods.getDiscount());
			case 5 -> StringUtil.formatDiscount(goods.getDiscount());
			case 6 -> goods.getStatus() == 1 ? "售卖中" : "已下架";
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		return super.delRow(rowIndex);
	}
}
