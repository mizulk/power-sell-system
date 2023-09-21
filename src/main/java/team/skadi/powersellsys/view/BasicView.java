package team.skadi.powersellsys.view;

import javax.swing.*;

public abstract class BasicView extends JPanel {

	public BasicView(){

	}

	protected void init() {

	}

	abstract void buildLayout();

	abstract void addListener();
}
