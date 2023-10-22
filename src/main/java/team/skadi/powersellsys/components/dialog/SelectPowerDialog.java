package team.skadi.powersellsys.components.dialog;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class SelectPowerDialog extends BasicDialog {

	public SelectPowerDialog(JFrame frame, String title) {
		super(frame, title);
	}

	@Override
	protected JComponent getCenterLayout() {
		return null;
	}
}
