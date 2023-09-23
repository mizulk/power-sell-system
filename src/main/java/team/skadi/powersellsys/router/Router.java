package team.skadi.powersellsys.router;

import team.skadi.powersellsys.App;

import java.awt.CardLayout;
import java.util.LinkedList;

/**
 * 用于面板的跳转
 */
public class Router {
	private final LinkedList<PanelName> panelStack;

	private final App app;

	private final CardLayout cardLayout;


	public void showPanel(PanelName from, PanelName to) {
		cardLayout.show(app.getContentPane(), to.getValue());
		panelStack.push(from);
	}

	public void showPreviousPanel() {
		cardLayout.show(app.getContentPane(), panelStack.pop().getValue());
	}

	public Router(App app) {
		this.app = app;
		cardLayout = new CardLayout();
		panelStack = new LinkedList<>();
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
