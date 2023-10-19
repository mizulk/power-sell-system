package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Order;

public class StatementTableModel extends DataTableModel<Order> {

    private final String[] columnName = new String[]{"电源类型", "电源型号", "订购日期", "订购人数"};

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
        Order order = data.get(rowIndex);
        return switch (columnIndex){
            case 0 -> order.getCreateTime();
            case 1 -> order.getCount();
            default -> null;
        };
    }
}
