package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.OrderTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserOrderPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener{
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

		PaginationPanel paginationPanel = new PaginationPanel(app,false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel,BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"名称", "型号", "单价"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void firstPage(int pageSize) {

	}

	@Override
	public void nextPage(int curPage, int pageSize) {

	}

	@Override
	public void previousPage(int curPage, int pageSize) {

	}

	@Override
	public void jumpTo(int page, int pageSize) {

	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		return null;
	}

	@Override
	public void onCloseButtonCLick() {

	}
}
