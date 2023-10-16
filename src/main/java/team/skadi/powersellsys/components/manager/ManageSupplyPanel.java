package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.SupplyTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageSupplyPanel extends ManagePanel {

	public ManageSupplyPanel(App app) {
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
		SupplyTableModel supplyTableModel = new SupplyTableModel();
		JTable supplyTable = new JTable(supplyTableModel);
		supplyTable.setRowHeight(30);
		return supplyTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel supplyBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("987");
		gbc.insets.set(0, 25, 10, 25);
		supplyBtnPanel.add(btn, gbc);
		return supplyBtnPanel;
	}

	@Override
	public void initData() {

	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
