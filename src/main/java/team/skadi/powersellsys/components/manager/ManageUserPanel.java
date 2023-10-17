package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.manager.UserTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ManageUserPanel extends ManagePanel implements SearchPanel.OnClickListener, PaginationPanel.OnClickListener {

	private UserTableModel userTableModel;
	private PaginationPanel paginationPanel;
	private User user;// 全局搜索对象

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
		JTable userTable = new JTable(userTableModel);
		userTable.setRowHeight(30);
		return userTable;
	}

	@Override
	protected JPanel getPaginationPanel() {
		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		return paginationPanel;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel userBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("123");
		gbc.insets.set(0, 25, 10, 25);
		userBtnPanel.add(btn, gbc);
		return userBtnPanel;
	}

	@Override
	public void initData() {
		UserService userService = ServiceUtil.getService(UserService.class);
		PageBean<User> userPageBean = userService.queryUser(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(userPageBean);
		userTableModel.initData(userPageBean.getData());
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
}
