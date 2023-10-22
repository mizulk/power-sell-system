package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Statement;

public class StatementTableModel extends DataTableModel<Statement> {

    private final String[] columnName = new String[]{"电源id", "电源名称", "订购数量", "订购金额"};

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
        Statement statement = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> statement.getPowerId();
            case 1 -> statement.getPowerName();
            case 2 -> statement.getSum();
            case 3 -> statement.getTotalPrice();
            default -> null;
        };
    }
}
