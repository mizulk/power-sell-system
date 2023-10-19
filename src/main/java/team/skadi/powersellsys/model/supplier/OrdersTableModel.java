package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Order;

public class OrdersTableModel extends DataTableModel<Order> {

    private final String[] columnName = new String[]{"订单编号", "用户id", "电源id", "电源类型", "电源型号", "电源容量", "订购数量", "订购金额", "订购日期"};

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
        return switch (rowIndex){
            case 0 -> order.getId();
            case 1 -> order.getUserId();
            case 2 -> order.getPowerId();
            case 3 -> order.getSum();
            case 4 -> order.getAmount();
            case 5 -> order.getCreateTime();
            default -> null;
        };
    }

}
