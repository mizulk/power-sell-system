package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.manager.UserTableModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageUserPanel extends ManagePanel {

	private UserTableModel userTableModel;

	public ManageUserPanel(App app) {
		super(app);
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"123", "321"});
		searchPanel.addOnClickListener(new SearchPanel.OnClickListener() {
			@Override
			public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
				return SearchPanel.SearchResult.NAN;
			}

			@Override
			public void onCloseButtonCLick() {

			}
		});
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		userTableModel = new UserTableModel();
		JTable userTable = new JTable(userTableModel);
		userTable.setRowHeight(30);
		return userTable;
	}

	@Override
	protected JPanel getPaginationPanel() {
		return new PaginationPanel(app, false);
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
