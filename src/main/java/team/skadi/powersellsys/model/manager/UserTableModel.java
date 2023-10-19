package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

public class UserTableModel extends DataTableModel<User> {

	private final String[] columnName = new String[]{"账号", "姓名", "性别", "年龄", "电话"};

	@Override
	public String getColumnName(int column) {
		return columnName[column];
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

	@Override
	public boolean delRow(int rowIndex) {
		UserService userService = ServiceUtil.getService(UserService.class);
		boolean b = userService.delUser(data.get(rowIndex).getAccount());
		super.delRow(rowIndex);
		return b;
	}
}
