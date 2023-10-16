package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GoodsTableModel extends AbstractTableModel implements SearchPanel.OnClickListener, PaginationPanel.OnClickListener {

    private final String[] columnName = new String[]{"电源id","电源名称", "电源类型","电源容量", "库存","状态"};

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
            case 0 -> goods.getId();
            case 1 -> goods.getName();
            case 2 -> goods.getModel();
            case 3 -> goods.getStock();
            case 4 -> goods.getStatus();
            default -> null;
        };
    }

    @Override
    public void firstPage(int pageSize) {

    }

    @Override
    public void nextPage(int curPage, int pageSize) {

    }

    @Override
    public void previousPage(int curPage, int pageSize) {

    }

    @Override
    public void jumpTo(int page, int pageSize) {

    }

    @Override
    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        return null;
    }

    @Override
    public void onCloseButtonCLick() {

    }
}
