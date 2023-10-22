package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.StringUtil;


public class GoodsTableModel extends DataTableModel<Goods> {

	private final String[] columnName = new String[]{"电源名称", "电源型号", "电源容量mA•h", "库存", "单价", "折扣", "状态"};

    @Override
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
        Goods goods = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> goods.getName();
			case 1 -> goods.getModel();
			case 2 -> StringUtil.formatInt(goods.getCapacity());
			case 3 -> goods.getStock();
			case 4 -> goods.getPrice();
			case 5 -> StringUtil.formatDiscount(goods.getDiscount());
			case 6 -> goods.getStatus() == 0 ? " 已下架" : "销售中";
            default -> null;
        };
    }


}
