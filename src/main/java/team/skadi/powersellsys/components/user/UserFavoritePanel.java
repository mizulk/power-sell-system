package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.model.user.FavoriteTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserFavoritePanel extends BasicComponent {
	public UserFavoritePanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		FavoriteTableModel favoriteTableModel = new FavoriteTableModel();
		JTable table = new JTable(favoriteTableModel);
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
