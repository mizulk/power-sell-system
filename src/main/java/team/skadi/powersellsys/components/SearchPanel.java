package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.router.PanelRouter;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 搜索面板，包括需要搜索的字段，输入框，搜索按钮和关闭搜索按钮（显示原始内容）
 * <p>
 * 使用方法：
 * <pre><code>
 * // 自定字段名称，类型默认为文本类型(SearchPanel.Type.STRING)
 * SearchPanel searchPanel = new SearchPanel(app, new String[]{"字段1", "字段2", "字段3"});
 * // 也可以指定字段类型，注意字段名必须和字段类型必须一一对应！
 * SearchPanel searchPanel = new SearchPanel(app,
 *     new String[]{"字段1", "字段2", "字段3"},
 *     new SearchPanel.Type[]{
 *         SearchPanel.Type.STRING,
 *         SearchPanel.Type.INTEGER,
 *         SearchPanel.Type.DATE
 *         // 支持的类型请看{@link SearchPanel.Type}
 *     }
 * );
 * // 必须实现并添加下面的接口，不然搜索面板只是一个摆设
 * searchPanel.addOnClickListener(new SearchPanel.OnClickListener() {
 *     &#064;Override
 *     public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
 *         // 查询语句
 *         return SearchPanel.SearchResult.HAVE_RESULT;// 返回查询结果
 *     }
 *     &#064;Override
 *     public void onCloseButtonCLick() {
 *         // 显示原始内容
 *     }
 * });
 * </code></pre>
 * </p>
 */
public class SearchPanel extends BasicComponent {

	private JButton searchBtn;
	private JButton closeBtn;
	private HintTextField hintTextField;
	private JSpinner integerSpinner, floatSpinner;
	private JFormattedTextField dateFormat, timeFormat, dateTimeFormat;
	private ImageButton optionBtn;
	private OnClickListener l;
	private final String[] options;
	private final Type[] optionTypes;
	private boolean isSearching;
	private int curOptionIndex;
	private PanelRouter router;

	/**
	 * 支持的输入类型：
	 * <ul>
	 *     <li>STRING：文本类型</li>
	 *     <li>DATE：日期类型</li>
	 *     <li>TIME：时间类型</li>
	 *     <li>DATETIME：日期时间类型</li>
	 *     <li>INTEGER：整数类型</li>
	 *     <li>FLOAT：浮点类型</li>
	 * </ul>
	 * 如果没有指定类型，默认文本类型
	 */
	public enum Type {
		STRING("string"),
		DATE("date"),
		TIME("time"),
		DATETIME("datetime"),
		INTEGER("integer"),
		FLOAT("float");

		final String value;

		Type(String value) {
			this.value = value;
		}
	}

	/**
	 * 搜索结果，可以时异常
	 * <ul>
	 *     <li>HAVE_RESULT：有结果，数据数目不为零</li>
	 *     <li>NO_RESULT：无结果，数据数目为零</li>
	 *     <li>NAN：不是数字，配合捕捉NumberFormatException异常使用</li>
	 *     <li>ERROR：其他异常</li>
	 * </ul>
	 */
	public enum SearchResult {
		HAVE_RESULT,
		NO_RESULT,
		/** 不是数字 not a number */
		NAN,
		ERROR
	}

	public SearchPanel(App app, String[] options) {
		this(app, options, null);
	}

	public SearchPanel(App app, String[] options, Type[] optionTypes) {
		super(app, false);
		if (options.length < 1)
			throw new IllegalArgumentException("The length of options can not less than one.");
		if (optionTypes != null && options.length != optionTypes.length)
			throw new IllegalArgumentException("The length of options does not equal the length of option types.");
		this.options = options;
		this.optionTypes = optionTypes;
		curOptionIndex = 0;
		init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// (1,1)
		gbc.gridy = 1;
		optionBtn = new ImageButton("/images/drop-down.png");
		optionBtn.setTextPosition(ImageButton.LEFT, ImageButton.CENTER);
		optionBtn.setText(options[curOptionIndex]);
		add(optionBtn, gbc);

		gbc.insets.right = 8;
		// (2->3,1)
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(getTypePanel(), gbc);

		// (3,1)
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		searchBtn = new JButton("搜索");
		add(searchBtn, gbc);

		// (4,1)
		closeBtn = new JButton("关闭");
		closeBtn.setEnabled(false);
		add(closeBtn, gbc);
	}

	private JPanel getTypePanel() {
		JPanel panel = new JPanel(new GridLayout(1, 1));

		if (optionTypes != null) {
			router = new PanelRouter(panel);
			// 使用set去除重复项
			HashSet<Type> typeSet = new HashSet<>();
			for (Type type : optionTypes) {
				if (!typeSet.add(type)) continue;
				switch (type) {
					case INTEGER -> {
						integerSpinner = new JSpinner(new SpinnerNumberModel());
						panel.add(Type.INTEGER.value, integerSpinner);
					}
					case FLOAT -> {
						floatSpinner = new JSpinner(new SpinnerNumberModel(1.0, null, null, 0.1));
						panel.add(Type.FLOAT.value, floatSpinner);
					}
					case TIME -> {
						timeFormat = new JFormattedTextField(new SimpleDateFormat("hh:mm:ss"));
						panel.add(Type.TIME.value, timeFormat);
					}
					case DATETIME -> {
						dateTimeFormat = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
						panel.add(Type.DATETIME.value, dateTimeFormat);
					}
					case DATE -> {
						dateFormat = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
						panel.add(Type.DATE.value, dateFormat);
					}
				}
			}
		}
		hintTextField = new HintTextField("请输入搜索内容");
		panel.add(Type.STRING.value, hintTextField);
		if (optionTypes != null) router.showPanel(optionTypes[0].value);
		return panel;
	}

	@Override
	protected void addListener() {
		searchBtn.addActionListener(this);
		closeBtn.addActionListener(this);
		optionBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == searchBtn) {
			search();
		} else if (source == closeBtn) {
			close();
		} else if (source == optionBtn) {
			changeSearchOption();
		}
	}

	private void changeSearchOption() {
		SearchOptionDialog searchOptionDialog = new SearchOptionDialog(app);
		int option = searchOptionDialog.getOption();
		if (option >= 0) {
			curOptionIndex = option;
			optionBtn.setText(options[curOptionIndex]);
			if (optionTypes != null) {
				router.showPanel(optionTypes[curOptionIndex].value);
			}
		}
	}

	private void close() {
		if (l != null) l.onCloseButtonCLick();
		isSearching = false;
		closeBtn.setEnabled(false);
	}

	private void search() {
		if (l == null) return;
		// 获取输入文字，三元运算符
		String content = optionTypes != null ?
				switch (optionTypes[curOptionIndex]) {
					case DATE -> dateFormat.getText();
					case DATETIME -> dateTimeFormat.getText();
					case TIME -> timeFormat.getText();
					case FLOAT -> floatSpinner.getValue().toString();
					case INTEGER -> integerSpinner.getValue().toString();
					case STRING -> hintTextField.getText();
				}
				: hintTextField.getText();

		if (content.equals("")) {
			JOptionPane.showMessageDialog(app, "请输入搜索内容");
			return;
		}

		SearchResult searchResult = l.onSearchButtonClick(curOptionIndex, content);

		if (searchResult == null) return;
		switch (searchResult) {
			case NO_RESULT -> {
				JOptionPane.showMessageDialog(app, "无搜索结果");
				isSearching = true;
			}
			case HAVE_RESULT -> isSearching = true;
			case NAN -> JOptionPane.showMessageDialog(app, "你输入的不是数字，请重新输入");
			case ERROR -> JOptionPane.showMessageDialog(app, "搜索时发生错误");
		}
		closeBtn.setEnabled(isSearching);
	}

	public void addOnClickListener(OnClickListener l) {
		this.l = l;
	}

	/** 搜索面板的点击事件监听器 */
	public interface OnClickListener {

		/**
		 * 当搜索按钮按下时执行
		 *
		 * @param optionIndex 当前搜索模式索引
		 * @param content     搜索框内容
		 * @return 搜索结果用于回显页面 {@link SearchResult}
		 */
		SearchResult onSearchButtonClick(int optionIndex, String content);

		/**
		 * 当关闭搜索按钮按下时执行，一般用于显示原始内容
		 */
		void onCloseButtonCLick();
	}

	/**
	 * 搜索模式选择对话框
	 */
	private class SearchOptionDialog extends BasicDialog {

		ArrayList<JRadioButton> radioButtons;

		public SearchOptionDialog(JFrame frame) {
			super(frame, "请选择搜索模式");
		}

		@Override
		protected void init() {
			radioButtons = new ArrayList<>();
			super.init();
		}

		@Override
		protected JComponent getCenterLayout() {
			JPanel panel = new JPanel();
			ButtonGroup buttonGroup = new ButtonGroup();
			for (int i = 0; i < options.length; i++) {
				JRadioButton radioButton = new JRadioButton(options[i]);
				radioButton.setSelected(i == curOptionIndex);
				buttonGroup.add(radioButton);
				radioButtons.add(radioButton);
				panel.add(radioButton);
			}
			return panel;
		}

		@Override
		protected boolean onConfirmButtonClick() {
			for (int i = 0; i < radioButtons.size(); i++) {
				if (radioButtons.get(i).isSelected()) {
					option = i;
					break;
				}
			}
			return true;
		}
	}
}
