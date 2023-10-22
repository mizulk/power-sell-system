package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;

public class VisitTableModel extends DataTableModel<Goods> {

    private final String[] columnName = new String[]{"商品名称", "查看次数"};

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
        return switch (columnIndex){
            case 0 -> goods.getName();
            case 1 -> goods.getSum();
            default -> null;
        };

    }

}
