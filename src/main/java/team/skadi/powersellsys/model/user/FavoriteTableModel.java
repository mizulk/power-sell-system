package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.utils.StringUtil;

import java.util.ArrayList;

public class FavoriteTableModel extends DataTableModel<FavoritePower> {

	private final String[] columnName = new String[]{"电源id", "电源名称", "电源价格", "折扣", "容量mA•h", "类型", "描述"};

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
			case 1 -> favorite.getName();
			case 2 -> favorite.getPrice();
			case 3 -> StringUtil.formatDiscount(favorite.getDiscount());
			case 4 -> StringUtil.formatInt(favorite.getCapacity());
			case 5 -> favorite.getValue();
			case 6 -> favorite.getDescribe();
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		FavoritePowerService favoritePowerService = ServiceUtil.getService(FavoritePowerService.class);
		boolean b = favoritePowerService.delFavorite(getRow(rowIndex).getId());
		super.delRow(rowIndex);
		return b;
	}
}
