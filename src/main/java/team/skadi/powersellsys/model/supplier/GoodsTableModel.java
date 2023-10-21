package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;


public class GoodsTableModel extends DataTableModel<Goods> {

    private final String[] columnName = new String[]{"电源名称", "电源型号", "电源容量", "库存", "单价", "折扣", "状态"};

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
            case 2 -> goods.getCapacity() + "mA•h";
            case 3 -> goods.getStock();
            case 4 -> goods.getPrice();
            case 5 -> goods.getDiscount() == 0 ? "无" : String.format("-%%%doff",goods.getDiscount());
            case 6 -> goods.getStatus() == 0 ? " 已下架" : "销售中";
            default -> null;
        };
    }


}
