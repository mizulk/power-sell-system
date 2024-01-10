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
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * 分页面板，必须注册{@link OnClickListener}来使用
 * <p>
 * 使用方法：使用{@link #addOnclickListener(OnClickListener)}
 * </p>
 */
public class PaginationPanel extends BasicComponent {

	public static final Component[] DEFAULT_LAYOUT = {
			Component.TOTAL,
			Component.SIZES,
			Component.FIRST,
			Component.PREV,
			Component.PAGER,
			Component.NEXT,
			Component.JUMPER
	};

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
	private final LinkedHashSet<Component> layoutSet;
	private SpinnerNumberModel spinnerNumberModel;
	private JButton exportBtn;

	/**
	 * 目前分页面板支持的组件有：
	 * <ul>
	 *     <li>total：总共页数</li>
	 *     <li>sizes：切换每页显示记录数</li>
	 *     <li>first：第一页按钮</li>
	 *     <li>prev：上一页按钮</li>
	 *     <li>pager：页数显示</li>
	 *     <li>next：下一页按钮</li>
	 *     <li>jumper：跳转按钮</li>
	 *     <li>export：导出数据</li>
	 * </ul>
	 */
	public enum Component {
		TOTAL("total"),
		SIZES("sizes"),
		FIRST("first"),
		PREV("prev"),
		PAGER("pager"),
		NEXT("next"),
		JUMPER("jumper"),
		EXPORT("export");
		final String value;

		Component(String value) {
			this.value = value;
		}

		public static Component[] parse(String layout) {
			String[] strings = layout.split(",\\s|,");
			if (strings.length == 0) return null;

			LinkedHashSet<Component> components = new LinkedHashSet<>();
			for (String str : strings) {
				for (Component comp : Component.values()) {
					if (str.equals(comp.value)) {
						components.add(comp);
						break;
					}
				}
			}
			return components.toArray(new Component[]{});
		}
	}

	/**
	 * @param app        从属于那个app
	 * @param exportable 是否能导出数据
	 * @deprecated replaced by {@link PaginationPanel#PaginationPanel(App, String)} or {@link PaginationPanel#PaginationPanel(App, Component[])}.
	 */
	@Deprecated(since = "26f6bdafcfcf9e62c97756a94ad45ad9313c19fa", forRemoval = true)
	public PaginationPanel(App app, boolean exportable) {
		this(app, DEFAULT_LAYOUT);
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
		this(app, DEFAULT_LAYOUT);
	}

	/**
	 * 依照给定的布局创建一个分页面板，字符串模式
	 *
	 * @see PaginationPanel#PaginationPanel(App, Component[])
	 */
	public PaginationPanel(App app, String layout) {
		this(app, Objects.requireNonNullElse(Component.parse(layout), DEFAULT_LAYOUT));
	}

	/**
	 * 依照给定的布局创建一个分页面板，枚举模式
	 *
	 * @param app        从属于那个app
	 * @param components 组件布局，子组件名用逗号分隔。提供的组件有: {@link Component}
	 */
	public PaginationPanel(App app, Component[] components) {
		super(app, false);
		layoutSet = new LinkedHashSet<>(Component.values().length);
		Collections.addAll(layoutSet, components.length == 0 ? DEFAULT_LAYOUT : components);
		init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridLayout(1, layoutSet.size(), 8, 0));

		for (Component component : layoutSet) {
			switch (component) {
				case TOTAL -> {
					totalLabel = new JLabel(/*"共 1024"*/);
					add(totalLabel);
				}
				case SIZES -> {
					pageSizeComboBox = new JComboBox<>(new String[]{"10/页", "15/页", "20/页"});
					add(pageSizeComboBox);
				}
				case FIRST -> {
					firstBtn = new JButton("首页");
					add(firstBtn);
				}
				case PREV -> {
					previousBtn = new JButton("<");
					add(previousBtn);
				}
				case PAGER -> {
					pageLabel = new JLabel("", JLabel.CENTER);
					add(pageLabel);
				}
				case NEXT -> {
					nextBtn = new JButton(">");
					add(nextBtn);
				}
				case JUMPER -> {
					spinnerNumberModel = new SpinnerNumberModel(1, 1, 10, 1);
					JSpinner pageSpinner = new JSpinner(spinnerNumberModel);
					add(pageSpinner);

					jumpBtn = new JButton("跳转");
					add(jumpBtn);
				}
				case EXPORT -> {// unfinished
					exportBtn = new JButton("导出");
					add(exportBtn);
				}
			}
		}
	}

	@Override
	protected void addListener() {
		for (Component component : layoutSet) {
			switch (component) {
				case SIZES -> pageSizeComboBox.addActionListener(this);
				case FIRST -> firstBtn.addActionListener(this);
				case PREV -> previousBtn.addActionListener(this);
				case NEXT -> nextBtn.addActionListener(this);
				case JUMPER -> jumpBtn.addActionListener(this);
				case EXPORT -> exportBtn.addActionListener(this);// unfinished
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
