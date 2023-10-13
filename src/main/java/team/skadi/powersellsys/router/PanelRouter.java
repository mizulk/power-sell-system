package team.skadi.powersellsys.router;

import javax.swing.JPanel;

public class PanelRouter extends Router {

	private final JPanel targetPanel;

	public PanelRouter(JPanel targetPanel) {
		super();
		this.targetPanel = targetPanel;
		targetPanel.setLayout(getCardLayout());
	}

	public void showPanel(String name) {
		cardLayout.show(targetPanel, name);
	}

}
