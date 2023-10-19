package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.model.user.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserCustomPanel extends BasicComponent {
	public UserCustomPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		CustomTableModel customTableModel = new CustomTableModel();
		JTable table = new JTable(customTableModel);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
