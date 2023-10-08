package team.skadi.powersellsys.components.manager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

// 相当于wrapper panel
public abstract class ManagePanel extends JPanel implements ActionListener {

	public ManagePanel() {
		init();
	}

	protected void init() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		int margin = 15;
		gbc.insets.set(margin, margin, margin, margin);
		gbc.fill = GridBagConstraints.BOTH;

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.add(getBtnPanel(), BorderLayout.WEST);

		JPanel innerPanel = new JPanel(new BorderLayout());
		innerPanel.add(getSearchPanel(), BorderLayout.NORTH);
		innerPanel.add(new JScrollPane(getTable()), BorderLayout.CENTER);

		outerPanel.add(innerPanel, BorderLayout.CENTER);
		add(outerPanel, gbc);

		addListener();
	}

	abstract protected JPanel getSearchPanel();

	abstract protected JTable getTable();

	abstract protected JPanel getBtnPanel();

	abstract protected void addListener();

	protected JPanel createBtnPanel() {
		JPanel btnPanel = new JPanel(new GridBagLayout());
		btnPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		return btnPanel;
	}
}
