package team.skadi.powersellsys.components.dialog.select;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.information.UserInformationPanel;
import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SelectUserDialog extends SelectDialog<User> {

	public SelectUserDialog(JFrame frame) {
		super(frame, "选择用户");
	}

	public SelectUserDialog(JDialog owner) {
		super(owner, "选择用户");
	}

	@Override
	public void pageChange(int curPage, int pageSize) {
		UserService service = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = service.queryUser(curPage, paginationPanel.getPageSize(), null);
		dataTableModel.updateData(userPageBean.getData());
	}

	@Override
	protected DataTableModel<User> getTableModel() {
		return new DataTableModel<>() {

			private static final String[] columnName = new String[]{"用户id", "用户名"};

			@Override
			public int getColumnCount() {
				return columnName.length;
			}

			@Override
			public String getColumnName(int column) {
				return columnName[column];
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				User user = data.get(rowIndex);
				return switch (columnIndex) {
					case 0 -> user.getId();
					case 1 -> user.getName();
					default -> null;
				};
			}
		};
	}

	@Override
	protected BasicComponent getInformationPanel() {
		UserInformationPanel userInformationPanel = new UserInformationPanel((App) getParent());
		userInformationPanel.showUser(dataTableModel.getRow(dataTable.getSelectedRow()));
		return userInformationPanel;
	}

	@Override
	protected int getSelectedValue() {
		return dataTableModel.getRow(dataTable.getSelectedRow()).getId();
	}

	@Override
	protected void initData() {
		UserService service = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = service.queryUser(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(userPageBean);
		dataTableModel.updateData(userPageBean.getData());
	}
}
