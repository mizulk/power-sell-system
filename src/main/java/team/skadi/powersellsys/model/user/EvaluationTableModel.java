package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EvaluationTableModel extends AbstractTableModel {

    private final String[] columnName = new String[]{"用户id", "用户名称", "评价内容", "评价时间"};
    private List<Goods> data;

    public EvaluationTableModel() {
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
        Goods evaluation = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> evaluation.getId();
            case 1 -> evaluation.getName();
            case 2 -> evaluation.getDescribe();
            case 3 -> evaluation.getUpdateTime();
            default -> null;
        };
    }
}
