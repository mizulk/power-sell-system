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
 * 使用方法：使用{@link #addOnclickListener(OnClickListener)}
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
	private int curPage = 1;
	private int pageSize = 10;
	private long total;
	private int pageLength;
	private SpinnerNumberModel spinnerNumberModel;
	private final String[] layout;
	private JButton exportBtn;

	/**
	 * @param app        从属于那个app
	 * @param exportable 是否能导出数据
	 * @deprecated replaced by {@code new PaginationPanel(App,String)}.
	 */
	@Deprecated(since = "26f6bdafcfcf9e62c97756a94ad45ad9313c19fa", forRemoval = true)
	public PaginationPanel(App app, boolean exportable) {
		this(app, "total,sizes,first,prev,pager,next,jumper");
		if (exportable) {
			exportBtn = new JButton("导出");
			add(exportBtn);
		}
	}

	/**
	 * 以默认布局(total,sizes,first,prev,pager,next,jumper)创建分页面板
	 *
	 * @param app 从属于那个app
	 */
	public PaginationPanel(App app) {
		this(app, "total,sizes,first,prev,pager,next,jumper");
	}

	/**
	 * 依照给定的布局创建一个分页面板
	 *
	 * @param app    从属于那个app
	 * @param layout 组件布局，子组件名用逗号分隔。提供的组件有 total,sizes,first,prev,pager,next,jumper,export
	 */
	public PaginationPanel(App app, String layout) {
		super(app, false);
		String[] layout1;
		layout1 = layout.split(",\\s|,");
		if (layout1.length == 0) {
			layout1 = new String[]{"total", "sizes", "first", "prev", "pager", "next", "jumper"};
		}
		this.layout = layout1;
		init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridLayout(1, 6, 8, 0));

		for (String str : layout) {
			switch (str) {
				case "total" -> {
					totalLabel = new JLabel(/*"共 1024"*/);
					add(totalLabel);
				}
				case "sizes" -> {
					pageSizeComboBox = new JComboBox<>(new String[]{"10/页", "15/页", "20/页"});
					add(pageSizeComboBox);
				}
				case "first" -> {
					firstBtn = new JButton("首页");
					add(firstBtn);
				}
				case "prev" -> {
					previousBtn = new JButton("<");
					add(previousBtn);
				}
				case "pager" -> {
					pageLabel = new JLabel("", JLabel.CENTER);
					add(pageLabel);
				}
				case "next" -> {
					nextBtn = new JButton(">");
					add(nextBtn);
				}
				case "jumper" -> {
					spinnerNumberModel = new SpinnerNumberModel(1, 1, 10, 1);
					JSpinner pageSpinner = new JSpinner(spinnerNumberModel);
					add(pageSpinner);

					jumpBtn = new JButton("跳转");
					add(jumpBtn);
				}
				case "export" -> {// unfinished
					exportBtn = new JButton("导出");
					add(exportBtn);
				}
			}
		}
	}

	@Override
	protected void addListener() {
		for (String str : layout) {
			switch (str) {
				case "sizes" -> pageSizeComboBox.addActionListener(this);
				case "first" -> firstBtn.addActionListener(this);
				case "prev" -> previousBtn.addActionListener(this);
				case "next" -> nextBtn.addActionListener(this);
				case "jumper" -> jumpBtn.addActionListener(this);
				case "export" -> exportBtn.addActionListener(this);// unfinished
			}
		}
	}


	public void addOnclickListener(OnClickListener l) {
		this.l = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (l == null) return;
		Object source = e.getSource();
		if (source == pageSizeComboBox) {
			pageSizeChange();
		} else if (source == firstBtn) {
			firstPage();
		} else if (source == previousBtn) {
			previousPage();
		} else if (source == nextBtn) {
			nextPage();
		} else if (source == jumpBtn) {
			jumpTo();
		}
		l.pageChange(curPage, pageSize);
	}

	private void jumpTo() {
		int i = spinnerNumberModel.getNumber().intValue();
		if (curPage != i) {
			curPage = i;
			updateLabel();
			l.jumpTo(curPage, pageSize);
		}
	}

	private void pageSizeChange() {
		pageSize = (pageSizeComboBox.getSelectedIndex() + 2) * 5;
		initPageSize();
		updateLabel();
		l.pageSizeChange(pageSize);
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
		if (pageLabel != null)
			pageLabel.setText(curPage + "/" + pageLength);
	}

	private void initPageSize() {
		pageLength = (int) Math.ceil(total / (double) pageSize);
		if (spinnerNumberModel != null) {
			spinnerNumberModel.setMaximum(pageLength);
			spinnerNumberModel.setValue(1);
		}
	}

	public void setPageBean(PageBean<?> pageBean) {
		total = pageBean.getTotal();
		initPageSize();
		if (totalLabel != null)
			totalLabel.setText("共 " + total);
		updateLabel();
	}

	public void incTotal() {
		if (totalLabel != null)
			totalLabel.setText("共 " + (++total));
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

	/**
	 * 是否是最后一页
	 *
	 * @return true 最后一页
	 */
	public boolean isLastPage() {
		return curPage == pageLength;
	}

	/**
	 * 最后一页的剩余的记录数
	 */
	public int getLeftRecord() {
		return (int) (total % pageSize);
	}

	/** 分页面板点击事件，需要实现并使用{@link #addOnclickListener(OnClickListener)}注册监听才能正常使用 */
	public interface OnClickListener {

		/**
		 * 当页面发生变化时调用
		 *
		 * @param curPage  当前页数
		 * @param pageSize 每页包含记录数
		 */
		void pageChange(int curPage, int pageSize);

		/**
		 * 当第一页被按下时
		 *
		 * @param pageSize 每页含有记录数
		 */
		default void firstPage(int pageSize) {

		}

		/**
		 * 当下一页被按下时
		 *
		 * @param curPage  下一页的页码
		 * @param pageSize 每页含有记录数
		 */
		default void nextPage(int curPage, int pageSize) {

		}

		/**
		 * 当上一页被按下时
		 *
		 * @param curPage  上一页的页码
		 * @param pageSize 每页含有记录数
		 */
		default void previousPage(int curPage, int pageSize) {

		}

		/**
		 * 当跳转按钮被按下时
		 *
		 * @param page     需要跳转到的页面
		 * @param pageSize 每页含有记录数
		 */
		default void jumpTo(int page, int pageSize) {

		}

		/**
		 * 当每页记录数改变时调用
		 *
		 * @param pageSize 新的记录数
		 */
		default void pageSizeChange(int pageSize) {

		}

	}
}
