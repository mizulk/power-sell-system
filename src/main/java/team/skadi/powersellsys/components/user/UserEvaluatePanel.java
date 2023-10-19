package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.model.user.EvaluationTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserEvaluatePanel extends BasicComponent {
	public UserEvaluatePanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		EvaluationTableModel evaluationTableModel = new EvaluationTableModel();
		JTable table = new JTable(evaluationTableModel);
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
