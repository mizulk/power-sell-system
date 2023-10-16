package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.utils.LayoutUtil;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

// 相当于wrapper panel
public abstract class ManagePanel extends BasicComponent {

	public ManagePanel(App app) {
		super(app);
	}

	protected void init() {
		buildLayout();
		addListener();
	}

	@Override
	protected void buildLayout() {
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
		innerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		innerPanel.add(LayoutUtil.createWrapper(new JScrollPane(getTable()), 6), BorderLayout.CENTER);
		innerPanel.add(LayoutUtil.createWrapper(getPaginationPanel(), 6), BorderLayout.SOUTH);

		outerPanel.add(innerPanel, BorderLayout.CENTER);

		add(outerPanel, gbc);
	}

	protected JPanel getPaginationPanel() {
		return new JPanel();
	}

	abstract protected JPanel getSearchPanel();

	abstract protected JTable getTable();

	abstract protected JPanel getBtnPanel();

	abstract public void initData();

	protected JPanel createBtnPanel() {
		return new JPanel(new GridBagLayout());
	}
}
