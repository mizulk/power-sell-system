package team.skadi.powersellsys.router;

import java.awt.CardLayout;

public class Router {
	protected final CardLayout cardLayout;

	public Router() {
		cardLayout = new CardLayout();
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
