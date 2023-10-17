package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.pojo.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StatementTableModel extends AbstractTableModel {

    private final String[] columnName = new String[]{"电源类型","电源型号","订购日期","订购人数"};

    private List<Order> data = new ArrayList<>();

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
        return switch (columnIndex){
            case 0 -> order.getCreateTime();
            case 1 -> order.getCount();
            default -> null;
        };
    }
}
