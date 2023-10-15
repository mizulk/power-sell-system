package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.pojo.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrdersTableModel extends AbstractTableModel implements SearchPanel.OnClickListener, PaginationPanel.OnClickListener{

    private final String[] columnName = new String[] {"订单编号","用户id","电源id","订购数量","订购金额","订购日期"};

    private List<Order> data = new ArrayList<>();

    @Override
    public String getColumnName(int column){
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
        Order order = data.get(rowIndex);
        return switch (rowIndex){
            case 0 -> order.getId();
            case 1 -> order.getUserId();
            case 2 -> order.getPowerId();
            case 3 -> order.getSum();
            case 4 -> order.getAmount();
            case 5 -> order.getCreateTime();
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
