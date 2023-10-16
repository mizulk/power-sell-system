package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.pojo.Favorite;
import team.skadi.powersellsys.pojo.Goods;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FavoriteTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"用户id", "电源id"};
	private List<Favorite> data;

	public FavoriteTableModel() {
		data = new ArrayList<>();
	}

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
		Favorite favorite = data.get(rowIndex);
		return switch (columnIndex){
			case 0 -> favorite.getUserId();
			case 1 -> favorite.getPowerId();
			default -> null;
		};
	}
}
