package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.utils.DateUtil;

public class SupplyTableModel extends DataTableModel<Supply> {

    private final String[] columnName = new String[]{"订单编号", "电源id", "供应数量", "创建日期", "供应日期"};

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
        Supply supply = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> supply.getId();
            case 1 -> supply.getPowerId();
            case 2 -> supply.getSum();
            case 3 -> DateUtil.replaceT(supply.getCreateTime());
            case 4 -> supply.getSupplyTime() != null ? DateUtil.replaceT(supply.getSupplyTime()) : "尚未供应";
            default -> null;
        };
    }

}
