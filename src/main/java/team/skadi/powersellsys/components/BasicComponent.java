package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

public abstract class BasicComponent extends JPanel implements ActionListener {

	protected App app;

	public BasicComponent(App app) {
		this(app, true);
	}

	/**
	 * @param app      所属app
	 * @param autoInit 是否自动执行初始化代码
	 */
	public BasicComponent(App app, boolean autoInit) {
		this.app = app;
		if (autoInit)
			init();
	}

	protected void init() {
		buildLayout();
		addListener();
	}

	abstract protected void buildLayout();

	abstract protected void addListener();
}
