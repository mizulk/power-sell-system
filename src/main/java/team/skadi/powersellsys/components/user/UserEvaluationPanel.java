package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.EvaluationTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserEvaluationPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener{
	public UserEvaluationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		EvaluationTableModel evaluationTableModel = new EvaluationTableModel();
		JTable table = new JTable(evaluationTableModel);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);

		PaginationPanel paginationPanel = new PaginationPanel(app,false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel,BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"评价时间"});
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
