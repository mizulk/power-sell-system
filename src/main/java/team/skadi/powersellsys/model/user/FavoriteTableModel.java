package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Favorite;

import java.util.ArrayList;

public class FavoriteTableModel extends DataTableModel<Favorite> {

	private final String[] columnName = new String[]{"用户id", "电源id"};

	public FavoriteTableModel() {
		data = new ArrayList<>();
	}

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
		Favorite favorite = data.get(rowIndex);
		return switch (columnIndex){
			case 0 -> favorite.getUserId();
			case 1 -> favorite.getPowerId();
			default -> null;
		};
	}
}
