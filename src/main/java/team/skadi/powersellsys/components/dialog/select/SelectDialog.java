package team.skadi.powersellsys.components.dialog.select;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.model.DataTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;

public abstract class SelectDialog<T> extends BasicDialog
		implements ListSelectionListener, PaginationPanel.OnClickListener {

	protected PaginationPanel paginationPanel;
	protected DataTableModel<T> dataTableModel;
	protected JTable dataTable;
	protected JButton viewBtn;

	public SelectDialog(JFrame frame, String title) {
		super(frame, title);
	}

	public SelectDialog(JDialog owner, String title) {
		super(owner, title);
	}

	@Override
	protected JComponent getCenterLayout() {
		JPanel panel = new JPanel(new BorderLayout());
		dataTableModel = getTableModel();
		dataTable = new JTable(dataTableModel);
		dataTable.setRowHeight(30);
		dataTable.getSelectionModel().addListSelectionListener(this);
		dataTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(new JScrollPane(dataTable), BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();
		paginationPanel = new PaginationPanel((App) getParent(), "prev,next");
		paginationPanel.addOnclickListener(this);
		btnPanel.add(paginationPanel);

		viewBtn = new JButton("查看详细");
		btnPanel.add(viewBtn);

		panel.add(btnPanel, BorderLayout.SOUTH);

		return panel;
	}

	@Override
	protected void addListener() {
		super.addListener();
		viewBtn.addActionListener(e -> showDataInformation());
	}

	private void showDataInformation() {
		BasicDialog basicDialog = new BasicDialog(this, "详细信息") {
			@Override
			protected JComponent getCenterLayout() {
				return getInformationPanel();
			}
		};
		basicDialog.getOption();
	}

	@Override
	public int getOption() {
		initData();
		setVisible(true);
		return option;
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (dataTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "未选择");
			return false;
		} else {
			option = getSelectedValue();
			return true;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = dataTable.getSelectedRow() != -1;
		viewBtn.setEnabled(b);
	}

	abstract protected DataTableModel<T> getTableModel();

	abstract protected BasicComponent getInformationPanel();

	abstract protected int getSelectedValue();

	abstract protected void initData();

}
