package team.skadi.powersellsys.model.user;


import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;

import java.util.ArrayList;


public class CustomTableModel extends DataTableModel<Goods> {

    private final String[] columnName = new String[]{"颜色", "型号", "折扣", "总价"};

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
        Goods custom = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> custom.getType();
            case 1 -> custom.getModel();
            case 2 -> custom.getDiscount();
            case 3 -> custom.getSum();

            default -> null;
        };
    }
}
