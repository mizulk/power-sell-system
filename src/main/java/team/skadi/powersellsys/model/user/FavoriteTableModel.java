package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.FavoritePower;

import java.util.ArrayList;

public class FavoriteTableModel extends DataTableModel<FavoritePower> {

	private final String[] columnName = new String[]{"电源id", "电源库存", "电源价格", "折扣", "容量", "类型", "描述"};

	public FavoriteTableModel() {
		data = new ArrayList<>();
	}

	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FavoritePower favorite = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> favorite.getPowerId();
			case 1 -> favorite.getStock();
			case 2 -> favorite.getPrice();
			case 3 -> favorite.getDiscount() + "%";
			case 4 -> favorite.getCapacity();
			case 5 -> favorite.getValue();
			case 6 -> favorite.getDescribe();
			default -> null;
		};
	}
}
