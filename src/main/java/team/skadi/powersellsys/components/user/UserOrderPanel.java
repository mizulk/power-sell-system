package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;

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
		JTable table = new JTable();
		add(table, BorderLayout.CENTER);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
