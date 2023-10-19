package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.model.user.OrderTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserOrderPanel extends BasicComponent {
	public UserOrderPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		OrderTableModel orderTableModel = new OrderTableModel();
		JTable table = new JTable(orderTableModel);
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
