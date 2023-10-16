package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.Manager;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"账号", "姓名", "性别", "年龄", "电话"};
	private List<User> data;

	public UserTableModel() {

	}

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
		User user = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> user.getAccount();
			case 1 -> user.getName();
			case 2 -> user.getSex() == 0 ? "女" : "男";
			case 3 -> user.getAge();
			case 4 -> user.getTel();
			default -> null;
		};
	}

	public void updateData(List<User> data) {
		this.data = data;
		fireTableDataChanged();
	}

	public void initData() {
		UserService userService = ServiceUtil.getService(UserService.class);
		data = userService.queryUser(1, 10, null).getData();
		fireTableDataChanged();
	}
}
