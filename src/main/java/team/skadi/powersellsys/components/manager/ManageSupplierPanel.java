package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.SupplierTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageSupplierPanel extends ManagePanel {
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
		SupplierTableModel supplierTableModel = new SupplierTableModel();
		JTable supplierTable = new JTable(supplierTableModel);
		supplierTable.setRowHeight(30);
		return supplierTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel supplierBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("321");
		gbc.insets.set(0, 25, 10, 25);
		supplierBtnPanel.add(btn, gbc);
		return supplierBtnPanel;
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
