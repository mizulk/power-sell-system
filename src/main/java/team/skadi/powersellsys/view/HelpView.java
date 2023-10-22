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
		hashMap.put("关于注册", "注册需要提供一定的信息\n" +
				"      用户需提供昵称，密码，手机号码或电话号码，地址以便于更好的使用本app和以便下单后快递能\n" +
				"正确地送往您的地址。\n" +
				"      供应商需提供姓名，密码，手机号码或电话号码，地址以便于您接受订单和更好的发货");
		hashMap.put("关于用户", "注册：当顾客想要使用完整的软件功能时，必须先注册，因此系统提供顾客注册功能。\n" + "\n" +
				"登录：当顾客想要了解商品详情、定制或下订单时，必须登录成功之后才可以进行。\n" + "\n" +
				"商品浏览：以列表的方式显示电源的信息，提供商品浏览方式为包括分类浏览。\n" + "\n" +
				"收藏：当顾客看到喜欢电源商品但不打算马上购买，可以先将关注的商品收藏起来，方便以后登录时\n" +
				"快捷的找到商品相关信息。\n" + "\n" +
				"商品详情：以列表的方式精细的展现电源的何种信息，同时列表分类也包括最新商品，推荐商品，热销\n" +
				"商品等，让顾客精准的找到想要的电源。\n" + "\n" +
				"订单：当顾客通过商品详情了解到心仪的商品，决定购买商品时，可以下订单，订单信息会传给供应商\n" +
				"，供应商会根据顾客订单的信息发货。\n" + "\n" +
				"个人信息：顾客管理自己的地址信息、以及相关的用户信息修改、密码维护等功能和已买到的商品、\n" +
				"收藏、积分、评价等。\n" + "\n" +
				"评价：对商品进行评分，用五颗星来快速打分；顾客还可以对商品进行评论。\n");
		hashMap.put("关于供应商", "商品上传：供应商上传电源商品，以及商品的详细参数及说明。\n" + "\n" +
				"查看访问量：供应商可以查看顾客对商品的访问量，以列表的形式呈现。\n" + "\n" +
				"订单处理：当供应商接收到顾客订单信息后，根据订单要求给顾客发货，打印发货单。\n" + "\n" +
				"统计报表：供应商可以查看每月的销售报表，并支持报表下载以及支持导出。\n" + "\n" +
				"竞价排名：供应商上传需要进行竞价排名的商品信息，由网站管理员设置搜索关键词，当顾客进行\n" +
				"搜索时，供应商竞价结果出现在搜索结果靠前的位置，容易引起用户的关注和点击，顾客有点击时\n" +
				"，则收取一定的推广费用。\n" + "\n" +
				"顾客评价：查看顾客评价，以便去改正。\n" + "\n" +
				"普通订单：查看普通类型的订单。\n");
		hashMap.put("关于管理员", "权限管理：主要是功能级权限管理，系统提供角色管理界面，由管理员定义\n" +
				"角色，给角色赋权限。\n" + "\n" +
				"管理员是公司指派的公司员工。");
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
