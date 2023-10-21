package team.skadi.powersellsys.model.supplier;
import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;

public class PriceTableModel extends DataTableModel<Goods> {

    private final String[] columnName = new String[]{"商品id", "商品名称", "商品价格"};

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
            case 0 -> goods.getId();
            case 1 -> goods.getName();
            case 2 -> goods.getPrice();
            default -> null;
        };
    }

}
