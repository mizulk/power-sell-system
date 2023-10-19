package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel implements DataTableModel<User> {

	private final String[] columnName = new String[]{"账号", "姓名", "性别", "年龄", "电话"};
	private List<User> data;

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

	@Override
	public void updateData(List<User> data) {
		this.data = data;
		fireTableDataChanged();// 让整个表格重新渲染
	}

	@Override
	public void addRow(User user) {
		data.add(user);
		fireTableRowsInserted(data.size(), data.size());// 让表格渲染新增加的记录
	}

	@Override
	public boolean delRow(int rowIndex) {
		UserService userService = ServiceUtil.getService(UserService.class);
		boolean b = userService.delUser(data.get(rowIndex).getAccount());
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);// 让表格移除删除的那一列
		return b;
	}

	@Override
	public void modifyRow(int rowIndex, User user) {
		data.set(rowIndex, user);
		fireTableRowsUpdated(rowIndex, rowIndex);// 让表格更新修改的行
	}

	@Override
	public User getRow(int rowIndex) {
		return data.get(rowIndex);
	}

	@Override
	public boolean hasData() {
		return getRowCount() != 0;// getRowCount()中进行了非空判断
	}
}
