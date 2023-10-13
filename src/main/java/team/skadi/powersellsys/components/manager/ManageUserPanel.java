package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.manager.UserTableModel;
import team.skadi.powersellsys.pojo.PageBean;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ManageUserPanel extends ManagePanel {

	private UserTableModel userTableModel;

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
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"123", "321"});
		searchPanel.addOnClickListener(userTableModel);
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
		PaginationPanel paginationPanel = new PaginationPanel(app, false);
		paginationPanel.setPageBean(new PageBean<>(100,new ArrayList<>()));
		paginationPanel.addOnclickListener(userTableModel);
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
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
