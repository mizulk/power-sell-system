package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

public class HelpView extends BasicView implements ActionListener {

	private HashMap<String, String> hashMap;
	private JLabel titleLabel;
	private JTextArea textArea;
	private static JButton exitBtn;

	public HelpView(App app) {
		super(app);
	}

	@Override
	protected void init() {
		hashMap = new HashMap<>();
		hashMap.put("关于注册", "注册需要提供一定的信息");
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		titleLabel = new JLabel();
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(Main.MIDDLE_FONT);
		add(titleLabel, BorderLayout.NORTH);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(230, 230, 230));
		textArea.setBorder(BorderFactory.createLoweredBevelBorder());
		add(textArea, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		// (1->1,1) 0,10,10,10
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.set(0, 10, 10, 10);
		Set<String> keySet = hashMap.keySet();
		for (String string : keySet) {
			JButton button = new JButton(string);
			button.addActionListener(this);
			btnPanel.add(button, gbc);
		}
		exitBtn = new JButton("返回");
		btnPanel.add(exitBtn, gbc);
		btnPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		add(btnPanel, BorderLayout.EAST);

		JLabel label = new JLabel("如有问题请致电11451419198", JLabel.RIGHT);
		add(label, BorderLayout.SOUTH);

		reset();
	}

	@Override
	protected void addListener() {
		exitBtn.addActionListener(this);
	}

	@Override
	public void onShow() {

	}

	@Override
	public void onHide() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.equals(exitBtn)) {
			app.useRouter().showPreviousView();
			titleLabel.setText("欢迎来到帮助页面");
			reset();
			return;
		}
		String text = btn.getText();
		textArea.setText(hashMap.get(text));
		titleLabel.setText(text);
	}

	private void reset() {
		titleLabel.setText("欢迎来到帮助页面");
		textArea.setText("欢迎来到帮助界面，请选择右边你想要查询的功能。");
	}
}
