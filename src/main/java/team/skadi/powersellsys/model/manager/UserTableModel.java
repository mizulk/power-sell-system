package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.pojo.User;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

	private final String[] columnName = new String[]{"账号", "姓名", "性别", "年龄", "电话"};
	private List<User> data;

	public UserTableModel() {
		data = new ArrayList<>();
		data.add(new User(1, "000001", "123456", "otto", (byte) 1, (short) 24, "5600", "114", "123", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()));

	}

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

}
