package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.BasicDialog;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * 搜索面板，包括需要搜索的字段，输入框，搜索按钮和关闭搜索按钮（显示原始内容）
 * <p>
 * 使用方法：
 * <pre><code>
 * SearchPanel searchPanel = new SearchPanel(app, new String[]{"字段1", "字段2", "字段3"});
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
// TODO：分类变化输入框
public class SearchPanel extends BasicComponent {

	private JButton searchBtn;
	private JButton closeBtn;
	private HintTextField hintTextField;
	private ImageButton btn;
	private OnClickListener l;
	private final String[] options;
	private boolean isSearching;
	private int curOptionIndex;

	public SearchPanel(App app, String[] options) {
		super(app);
		if (options.length < 1)
			throw new IllegalArgumentException("The options length can not less than 1");
		this.options = options;
		curOptionIndex = 0;
		btn.setText(options[curOptionIndex]);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// (1,1)
		gbc.gridy = 1;
		btn = new ImageButton("/images/drop-down.png");
		btn.setTextPosition(ImageButton.LEFT, ImageButton.CENTER);
		add(btn, gbc);

		gbc.insets.right = 8;
		// (2->3,1)
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		hintTextField = new HintTextField("请输入搜索内容");
		add(hintTextField, gbc);

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

	@Override
	protected void addListener() {
		searchBtn.addActionListener(this);
		closeBtn.addActionListener(this);
		btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == searchBtn) {
			search();
		} else if (source == closeBtn) {
			close();
		} else if (source == btn) {
			SearchOptionDialog searchOptionDialog = new SearchOptionDialog(app);
			int option = searchOptionDialog.getOption();
			if (option >= 0) {
				curOptionIndex = option;
				btn.setText(options[curOptionIndex]);
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
		if (!hintTextField.isEmpty()) {
			SearchResult searchResult = l.onSearchButtonClick(curOptionIndex, hintTextField.getText());
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
		} else {
			JOptionPane.showMessageDialog(app, "请输入搜索内容");
		}
	}

	public void addOnClickListener(OnClickListener l) {
		this.l = l;
	}

	/**
	 * 搜索结果，可以时异常
	 */
	public enum SearchResult {
		HAVE_RESULT,
		NO_RESULT,
		/** 不是数字 not a number */
		NAN,
		ERROR
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
