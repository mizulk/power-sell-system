package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Judge;

import java.util.ArrayList;
import java.util.List;

public class JudgeTableModel extends DataTableModel<Judge> {

    private final String[] columnName = new String[]{"用户账号", "商品名称", "商品容量", "商品评价"};

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
        Judge visits = data.get(rowIndex);
        return switch (rowIndex){
            case 0 -> visits.getAccount();
            case 1 -> visits.getName();
            case 2 -> visits.getCapacity();
            case 3 -> visits.getDescribe();
            default -> null;
        };
    }

}
