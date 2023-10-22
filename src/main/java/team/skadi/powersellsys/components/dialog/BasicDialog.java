package team.skadi.powersellsys.components.dialog;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BasicDialog extends JDialog implements ActionListener {

	public static final int NULL_OPTION = -1, CONFIRM_OPTION = -2, CANCEL_OPTION = -3;

	protected int option;
	private JButton confirm;
	private JButton cancel;

	public BasicDialog(JFrame frame, String title) {
		super(frame, title, true);
		init();
	}

	public BasicDialog(JDialog owner, String title) {
		super(owner, title, true);
		init();
	}

	public int getOption() {
		setVisible(true);
		return option;
	}

	protected void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		buildLayout();
		addListener();
		pack();
		Rectangle bounds = getOwner().getBounds();
		Point point = new Point();
		point.x = bounds.x + ((bounds.width - getWidth()) >> 1);
		point.y = bounds.y + ((bounds.height - getHeight()) >> 1);
		setLocation(point);
	}

	protected void buildLayout() {
		add(getCenterLayout(), BorderLayout.CENTER);
		add(getSouthBtnPanel(), BorderLayout.SOUTH);
	}

	abstract protected JComponent getCenterLayout();

	protected JComponent getSouthBtnPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;

		// (1-1,1)
		gbc.gridy = 1;
		gbc.insets.set(5, 20, 0, 10);
		confirm = new JButton("确认");
		panel.add(confirm, gbc);

		// (2-2,1)
		gbc.insets.set(5, 20, 0, 20);
		cancel = new JButton("取消");
		panel.add(cancel, gbc);
		return panel;
	}

	protected void addListener() {
		confirm.addActionListener(this);
		cancel.addActionListener(this);
	}

	/**
	 * 当确认按钮点击时执行
	 *
	 * @return 点击事件是否会关闭当前对话框
	 */
	protected boolean onConfirmButtonClick() {
		option = CONFIRM_OPTION;
		return true;
	}

	/**
	 * 当取消按钮被点击时执行
	 *
	 * @return 点击事件是否会关闭当前对话框
	 */
	protected boolean onCancelButtonClick() {
		option = CANCEL_OPTION;
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		boolean isClose = true;
		if (source == confirm) {
			isClose = onConfirmButtonClick();
		} else if (source == cancel) {
			isClose = onCancelButtonClick();
		}
		if (isClose) dispose();
	}
}
