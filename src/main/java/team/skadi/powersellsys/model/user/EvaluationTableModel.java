package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.DateUtil;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EvaluationTableModel extends DataTableModel<Comment> {

    private final String[] columnName = new String[]{"电源id", "评价内容", "评价时间", "好评星级"};

    public EvaluationTableModel() {
        data = new ArrayList<>();
    }

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
        Comment evaluation = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> evaluation.getPowerId();
            case 1 -> evaluation.getContent();
            case 2 -> DateUtil.replaceT(evaluation.getCreateTime());
            case 3 -> evaluation.getStar();
            default -> null;
        };
    }
}
