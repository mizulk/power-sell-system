package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

    private final String[] columnName = new String[]{"名称", "型号", "单价", "折扣", "总价"};
    private List<Goods> data;

    public OrderTableModel() {
        data = new ArrayList<>();
    }


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
        Goods order = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> order.getName();
            case 1 -> order.getModel();
            case 2 -> order.getPrice();
            case 3 -> order.getDiscount();
            case 4 -> order.getSum();
            default -> null;
        };
    }

}
