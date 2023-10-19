package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.manager.CommentTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageCommentPanel extends ManagePanel{

	public ManageCommentPanel(App app) {
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
		CommentTableModel commentTableModel = new CommentTableModel();
		JTable commentTable = new JTable(commentTableModel);
		commentTable.setRowHeight(30);
		return commentTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel commentBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		JButton btn = new JButton("789");
		gbc.insets.set(0, 25, 10, 25);
		commentBtnPanel.add(btn, gbc);
		return commentBtnPanel;
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
