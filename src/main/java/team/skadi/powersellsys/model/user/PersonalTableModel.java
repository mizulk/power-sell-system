package team.skadi.powersellsys.model.user;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.User;

public class PersonalTableModel extends DataTableModel<User> {


	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
}
