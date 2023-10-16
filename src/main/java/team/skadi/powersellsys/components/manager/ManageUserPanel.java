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

public class ManageUserPanel extends ManagePanel implements SearchPanel.OnClickListener {

	private UserTableModel userTableModel;
	private PaginationPanel paginationPanel;

	public ManageUserPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		userTableModel = new UserTableModel();
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
		paginationPanel.setPageBean(new PageBean<>(100, new ArrayList<>()));
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
		userTableModel.initData();
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		User user = new User();
		switch (optionIndex) {
			case 0 -> user.setAccount(content);
			case 1 -> user.setName(content);
			case 2 -> {
				byte sex;
				try {
					sex = Byte.parseByte(content);
					if (sex < 1 || sex > 2) return SearchPanel.SearchResult.ERROR;
				} catch (NumberFormatException e) {
					return SearchPanel.SearchResult.NAN;
				}
				user.setSex(sex);
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
		return users.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<User> userPageBean = ServiceUtil.getService(UserService.class).queryUser(1, paginationPanel.getPageSize(), null);
		userTableModel.updateData(userPageBean.getData());
	}
}
