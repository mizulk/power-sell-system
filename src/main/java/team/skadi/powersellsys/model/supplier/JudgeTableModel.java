package team.skadi.powersellsys.model.supplier;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Judge;
import team.skadi.powersellsys.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class JudgeTableModel extends DataTableModel<Judge> {

    private final String[] columnName = new String[]{"电源名称", "用户账号", "评分", "内容","评论时间"};

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
        return switch (columnIndex){
            case 0 -> visits.getPowerName();
            case 1 -> visits.getUserAccount();
            case 2 -> visits.getStar();
            case 3 -> visits.getContent();
            case 4 -> DateUtil.replaceT(visits.getCreateTime());
            default -> null;
        };
    }

}
