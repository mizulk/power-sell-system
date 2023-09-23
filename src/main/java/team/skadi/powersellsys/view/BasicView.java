package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.router.PanelName;

import javax.swing.JPanel;

/**
 * 所有功能面板的基类
 * 继承后需要在 {@link PanelName} 中注册对应的面板名字
 */
public abstract class BasicView extends JPanel {

	App app;

	public BasicView(App app) {
		this.app = app;
		init();
	}

	protected void init() {
		buildLayout();
		addListener();
	}

	abstract void buildLayout();

	abstract void addListener();
}
