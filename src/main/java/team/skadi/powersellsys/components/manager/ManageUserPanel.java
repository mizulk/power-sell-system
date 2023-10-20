package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.UserInformationPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.UserDialog;
import team.skadi.powersellsys.model.manager.UserTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageUserPanel extends ManagePanel {

	private UserTableModel userTableModel;
	private User user;// 全局搜索对象
	private JButton addUserBtn;
	private JButton delUserBtn;
	private JButton modifyUserBtn;
	private JButton showUserBtn;
	private JButton refreshBtn;
	private JTable userTable;

	public ManageUserPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		userTableModel = new UserTableModel();
		user = new User();// 创建全局搜索对象，防止空指针异常
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户账号", "用户姓名", "用户性别", "用户年龄", "用户住址"});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		userTable = new JTable(userTableModel);
		userTable.setRowHeight(30);
		userTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.getSelectionModel().addListSelectionListener(this);
		return userTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel userBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;

		gbc.insets.set(0, 20, 30, 20);
		addUserBtn = new JButton("添加用户");
		userBtnPanel.add(addUserBtn, gbc);

		delUserBtn = new JButton("删除用户");
		delUserBtn.setEnabled(false);
		userBtnPanel.add(delUserBtn, gbc);

		modifyUserBtn = new JButton("修改用户");
		modifyUserBtn.setEnabled(false);
		userBtnPanel.add(modifyUserBtn, gbc);

		showUserBtn = new JButton("查看用户");
		showUserBtn.setEnabled(false);
		userBtnPanel.add(showUserBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		userBtnPanel.add(refreshBtn, gbc);
		return userBtnPanel;

	}

	@Override
	public void initData() {
		if (!userTableModel.hasData()) {
			UserService userService = ServiceUtil.getService(UserService.class);
			PageBean<User> userPageBean = userService.queryUser(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(userPageBean);
			userTableModel.updateData(userPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(paginationPanel.getCurPage(), paginationPanel.getPageSize(), user);
		paginationPanel.setPageBean(userPageBean);
		userTableModel.updateData(userPageBean.getData());
		JOptionPane.showMessageDialog(app, "刷新成功！");
	}

	@Override
	protected void addListener() {
		addUserBtn.addActionListener(this);
		delUserBtn.addActionListener(this);
		modifyUserBtn.addActionListener(this);
		showUserBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addUserBtn) {
			addUser();
		} else if (source == delUserBtn) {
			delUser();
		} else if (source == modifyUserBtn) {
			modifyUser();
		} else if (source == showUserBtn) {
			showUser();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void addUser() {
		UserDialog userDialog = new UserDialog(app, UserDialog.ADD_MODE);
		if (userDialog.getOption() == BasicDialog.CONFIRM_OPTION &&
				paginationPanel.isLastPage() &&
				paginationPanel.getLeftRecord() < paginationPanel.getPageSize()
		) {// 当分页是最后一页，并且还能往里面塞记录时
			userTableModel.addRow(userDialog.getUser());
		}
	}

	private void modifyUser() {
		UserDialog userDialog = new UserDialog(app, UserDialog.MODIFY_MODE);
		userDialog.setData(userTableModel.getRow(userTable.getSelectedRow()));
		if (userDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			userTableModel.modifyRow(userTable.getSelectedRow(), userDialog.getUser());
		}
	}

	private void showUser() {
		BasicDialog userInformationDialog = new BasicDialog(app, "个人信息") {
			@Override
			protected JComponent getCenterLayout() {
				UserInformationPanel userInformationPanel = new UserInformationPanel(app);
				userInformationPanel.showUser(userTableModel.getRow(userTable.getSelectedRow()));
				return userInformationPanel;
			}
		};
		userInformationDialog.getOption();
	}

	private void delUser() {
		if (JOptionPane.showConfirmDialog(app, "你确定要删除这个用户吗？") == JOptionPane.OK_OPTION) {
			if (userTableModel.delRow(userTable.getSelectedRowCount())) {
				JOptionPane.showMessageDialog(app, "删除成功");
			} else {
				JOptionPane.showMessageDialog(app, "删除失败");
			}
		} else {
			JOptionPane.showMessageDialog(app, "已取消");
		}
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		user = new User();// 每次搜索清空内容（待定）
		switch (optionIndex) {
			case 0 -> user.setAccount(content);
			case 1 -> user.setName(content);
			case 2 -> {
				if (content.equals("男") || content.equals("女"))
					user.setSex((byte) (content.equals("男") ? 1 : 0));
				else
					return SearchPanel.SearchResult.ERROR;// 输入错误
			}
			case 3 -> {
				short age;
				try {
					age = Short.parseShort(content);
				} catch (NumberFormatException e) {
					return SearchPanel.SearchResult.NAN;
				}
				user.setAge(age);
			}
			case 4 -> user.setAddress(content);
		}
		PageBean<User> users = ServiceUtil.getService(UserService.class).queryUser(1, paginationPanel.getPageSize(), user);
		userTableModel.updateData(users.getData());
		paginationPanel.setPageBean(users); // 更新分页面板
		return users.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<User> userPageBean = ServiceUtil.getService(UserService.class).queryUser(1, paginationPanel.getPageSize(), null);
		userTableModel.updateData(userPageBean.getData());
	}
	@Override
	public void firstPage(int pageSize) {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(1, pageSize, user);
		userTableModel.updateData(userPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(curPage, pageSize, user);
		userTableModel.updateData(userPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(curPage, pageSize, user);
		userTableModel.updateData(userPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(page, pageSize, user);
		userTableModel.updateData(userPageBean.getData());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = userTable.getSelectedRow() != -1;
		delUserBtn.setEnabled(b);
		modifyUserBtn.setEnabled(b);
		showUserBtn.setEnabled(b);
	}
}
