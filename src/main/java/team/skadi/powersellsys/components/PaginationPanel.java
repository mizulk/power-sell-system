package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.PageBean;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * 分页面板，必须注册{@link OnClickListener}来使用
 * <p>
 * 使用方法1:使用{@link #addOnclickListener(OnClickListener)}
 * </p>
 * <p>
 * 使用方法2：在继承{@link javax.swing.table.AbstractTableModel}的类中实现
 * </p>
 */
public class PaginationPanel extends BasicComponent {

	private JLabel totalLabel;
	private JComboBox<String> pageSizeComboBox;
	private JButton firstBtn;
	private JButton previousBtn;
	private JLabel pageLabel;
	private JButton nextBtn;
	private JButton jumpBtn;
	private OnClickListener l;
	private int curPage;
	private int pageSize = 10;
	private long total;
	private int pageLength;
	private SpinnerNumberModel spinnerNumberModel;

	public PaginationPanel(App app, boolean exportable) {
		super(app);
		if (exportable) {
			JButton exportBtn = new JButton("导出");
			add(exportBtn);
		}
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridLayout(1, 6, 8, 0));

		totalLabel = new JLabel(/*"共 1024"*/);
		add(totalLabel);

		pageSizeComboBox = new JComboBox<>(new String[]{"10/页", "15/页", "20/页"});
		add(pageSizeComboBox);

		firstBtn = new JButton("首页");
		add(firstBtn);

		previousBtn = new JButton("<");
		add(previousBtn);

		pageLabel = new JLabel("", JLabel.CENTER);
		add(pageLabel);

		nextBtn = new JButton(">");
		add(nextBtn);

		spinnerNumberModel = new SpinnerNumberModel(1, 1, 10, 1);
		JSpinner pageSpinner = new JSpinner(spinnerNumberModel);
		add(pageSpinner);

		jumpBtn = new JButton("跳转");
		add(jumpBtn);
	}

	@Override
	protected void addListener() {
		pageSizeComboBox.addActionListener(this);
		firstBtn.addActionListener(this);
		previousBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		jumpBtn.addActionListener(this);
	}


	public void addOnclickListener(OnClickListener l) {
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (l == null) return;
		Object source = e.getSource();
		if (source == pageSizeComboBox) {
			pageSize = (pageSizeComboBox.getSelectedIndex() + 2) * 5;
			pageSizeChange();
			updateLabel();
		} else if (source == firstBtn) {
			firstPage();
		} else if (source == previousBtn) {
			previousPage();
		} else if (source == nextBtn) {
			nextPage();
		} else if (source == jumpBtn) {
			curPage = spinnerNumberModel.getNumber().intValue();
			updateLabel();
			l.jumpTo(curPage, pageSize);
		}
	}

	private void nextPage() {
		if (curPage != pageLength) {
			curPage++;
			updateLabel();
			l.nextPage(curPage, pageSize);
		}
	}

	private void previousPage() {
		if (curPage != 1) {
			curPage--;
			updateLabel();
			l.previousPage(curPage, pageSize);
		}
	}

	private void firstPage() {
		if (curPage != 1) {
			curPage = 1;
			updateLabel();
			l.firstPage(pageSize);
		}
	}

	private void updateLabel() {
		pageLabel.setText(curPage + "/" + pageLength);
	}

	private void pageSizeChange() {
		pageLength = (int) Math.ceil(total / (double) pageSize);
		spinnerNumberModel.setMaximum(pageLength);
		spinnerNumberModel.setValue(1);
		curPage = 1;
	}

	public void setPageBean(PageBean<?> pageBean) {
		total = pageBean.getTotal();
		pageSizeChange();
		totalLabel.setText("共 " + total);
		updateLabel();
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

	/** 分页面板点击事件，需要实现并使用{@link #addOnclickListener(OnClickListener)}注册监听才能正常使用 */
	public interface OnClickListener {
		/**
		 * 当第一页被按下时
		 *
		 * @param pageSize 每页含有记录数
		 */
		void firstPage(int pageSize);

		/**
		 * 当下一页被按下时
		 *
		 * @param curPage  下一页的页码
		 * @param pageSize 每页含有记录数
		 */
		void nextPage(int curPage, int pageSize);

		/**
		 * 当上一页被按下时
		 *
		 * @param curPage  上一页的页码
		 * @param pageSize 每页含有记录数
		 */
		void previousPage(int curPage, int pageSize);

		/**
		 * 当跳转按钮被按下时
		 *
		 * @param page     需要跳转到的页面
		 * @param pageSize 每页含有记录数
		 */
		void jumpTo(int page, int pageSize);
	}
}
