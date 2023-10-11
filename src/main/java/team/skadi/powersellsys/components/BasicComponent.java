package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

public abstract class BasicComponent extends JPanel implements ActionListener {

	protected App app;

	public BasicComponent(App app) {
		this.app = app;
		init();
	}

	protected void init() {
		buildLayout();
		addListener();
	}

	abstract protected void buildLayout();

	abstract protected void addListener();
}
