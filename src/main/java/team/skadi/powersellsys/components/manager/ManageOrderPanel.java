package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.OrderTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageOrderPanel extends ManagePanel{

	public ManageOrderPanel(App app) {
		super(app);
	}

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
		OrderTableModel orderTableModel = new OrderTableModel();
		JTable orderTable = new JTable(orderTableModel);
		orderTable.setRowHeight(30);
		return orderTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel orderBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("654");
		gbc.insets.set(0, 25, 10, 25);
		orderBtnPanel.add(btn, gbc);
		return orderBtnPanel;
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
