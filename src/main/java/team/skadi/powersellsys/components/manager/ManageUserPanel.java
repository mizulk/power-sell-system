package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.UserTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageUserPanel extends ManagePanel {

	@Override
	protected JPanel getSearchPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("search");
		label.setFont(Main.TITLE_FONT);
		panel.add(label);
		return panel;
	}

	@Override
	protected JTable getTable() {
		UserTableModel userTableModel = new UserTableModel();
		JTable userTable = new JTable(userTableModel);
		userTable.setRowHeight(30);
		return userTable;
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
