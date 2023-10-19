package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.GoodsTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageGoodsPanel extends ManagePanel{

	public ManageGoodsPanel(App app) {
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
		GoodsTableModel goodsTableModel = new GoodsTableModel();
		JTable goodsTable = new JTable(goodsTableModel);
		goodsTable.setRowHeight(30);
		return goodsTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel goodsBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("456");
		gbc.insets.set(0, 25, 10, 25);
		goodsBtnPanel.add(btn, gbc);
		return goodsBtnPanel;
	}

	@Override
	public void initData() {

	}

	@Override
	public void refreshData() {

	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
