package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * 分页面板
 */
public class PaginationPanel extends BasicComponent {

	private JComboBox<String> pageSizeComboBox;
	private JButton firstBtn;
	private JButton previousBtn;
	private JLabel pageLabel;
	private JButton nextBtn;
	private JSpinner pageSpinner;
	private JButton jumpBtn;
	private boolean exportable;
	private int curPage;
	private int pageSize = 10;

	public PaginationPanel(App app, boolean exportable) {
		super(app);
		this.exportable = exportable;
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridLayout(1, 6, 8, 0));

		JLabel label = new JLabel("共 1024");
		add(label);

		pageSizeComboBox = new JComboBox<>(new String[]{"10/页", "15/页", "20/页"});
		add(pageSizeComboBox);

		firstBtn = new JButton("首页");
		add(firstBtn);

		previousBtn = new JButton("<");
		add(previousBtn);

		pageLabel = new JLabel("1/2", JLabel.CENTER);
		add(pageLabel);

		nextBtn = new JButton(">");
		add(nextBtn);

		pageSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		add(pageSpinner);

		jumpBtn = new JButton("跳转");
		add(jumpBtn);

		JButton exportBtn = new JButton("导出");
		add(exportBtn);
	}

	@Override
	protected void addListener() {
		pageSizeComboBox.addActionListener(this);
		firstBtn.addActionListener(this);
		previousBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		jumpBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == pageSizeComboBox) {
			pageSize = (pageSizeComboBox.getSelectedIndex() + 2) * 5;
		} else if (source == firstBtn) {

		} else if (source == nextBtn) {

		} else if (source == jumpBtn) {

		}
	}

	/**
	 * 获取当前页是那一页
	 *
	 * @return 当前页
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * 获取每页的记录数
	 *
	 * @return 记录数
	 */
	public int getPageSize() {
		return pageSize;
	}
}
