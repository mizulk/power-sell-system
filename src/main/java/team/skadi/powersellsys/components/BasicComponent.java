package team.skadi.powersellsys.components;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

public abstract class BasicComponent extends JPanel implements ActionListener {

	public BasicComponent(){
		init();
	}

	protected void init(){
		buildLayout();
		addListener();
	}

	abstract protected void buildLayout();

	abstract protected void addListener();
}
