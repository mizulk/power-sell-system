package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class VisitTableModel extends AbstractTableModel {

    private final String[] columnName = new String[]{"商品名称","查看次数"};

    private List<Goods> data = new ArrayList<>();

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
        Goods goods = data.get(rowIndex);
        return switch (columnIndex){
            case 0 -> goods.getName();
            case 1 -> goods.getSum();
            default -> null;
        };

    }

}
