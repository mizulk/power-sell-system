package team.skadi.powersellsys.components.information;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageLabel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.StringUtil;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class GoodsInformationPanel extends BasicComponent {

	private Goods goods;
	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel capacityLabel;
	private JLabel stockLabel;
	private JLabel priceLabel;
	private JLabel discountLabel;
	private JLabel modelLabel;
	private JLabel sumLabel;
	private JLabel createTimeLabel;
	private JLabel updateTimeLabel;
	private JLabel describeLabel;

	public GoodsInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.gridy = 1;
		gbc.insets.set(0, 0, 10, 10);

		ImageLabel imageLabel;
		// (1,1)
		imageLabel = new ImageLabel("电源名称：", "/images/goods.png");
		add(imageLabel, gbc);
		// (2,1)
		nameLabel = new JLabel();
		add(nameLabel, gbc);

		// (3,1)
		imageLabel = new ImageLabel("种类：", "/images/type.png");
		add(imageLabel, gbc);
		// (4,1)
		typeLabel = new JLabel();
		add(typeLabel, gbc);

		// (5,1)
		imageLabel = new ImageLabel("容量：", "/images/capacity.png");
		add(imageLabel, gbc);
		// (6,1)
		capacityLabel = new JLabel();
		add(capacityLabel, gbc);

		gbc.gridy++;
		// (1,2)
		imageLabel = new ImageLabel("库存：", "/images/stock.png");
		add(imageLabel, gbc);
		// (2,2)
		stockLabel = new JLabel();
		add(stockLabel, gbc);

		// (3,2)
		imageLabel = new ImageLabel("单价：", "/images/price.png");
		add(imageLabel, gbc);
		// (4,2)
		priceLabel = new JLabel();
		add(priceLabel, gbc);

		// (5,2)
		imageLabel = new ImageLabel("折扣：", "/images/discount.png");
		add(imageLabel, gbc);
		// (6,2)
		discountLabel = new JLabel();
		add(discountLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		// (1-2,3)
		imageLabel = new ImageLabel("型号：", "/images/model.png");
		add(imageLabel, gbc);
		// (3-4,3)
		modelLabel = new JLabel();
		add(modelLabel, gbc);

		gbc.gridwidth = 1;
		// (5,3)
		imageLabel = new ImageLabel("访问量：", "/images/page-view.png");
		add(imageLabel, gbc);
		// (6,3)
		sumLabel = new JLabel();
		add(sumLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 3;
		// (1-3,4)
		imageLabel = new ImageLabel("创建日期：", "/images/time.png");
		add(imageLabel, gbc);
		// (4-6,4)
		createTimeLabel = new JLabel();
		add(createTimeLabel, gbc);

		gbc.gridy++;
		// (1-3,5)
		imageLabel = new ImageLabel("修改日期：", "/images/update-time.png");
		add(imageLabel, gbc);
		// (4-6,5)
		updateTimeLabel = new JLabel();
		add(updateTimeLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 1;
		// (1,6)
		imageLabel = new ImageLabel("描述：", "/images/describe.png");
		add(imageLabel, gbc);
		// (2-6,6)
		gbc.gridwidth = 5;
		describeLabel = new JLabel();
		add(describeLabel, gbc);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void showGoods(Goods goods) {
		this.goods = goods;
		nameLabel.setText(goods.getName());
		typeLabel.setText(goods.getType());
		capacityLabel.setText(goods.getCapacity() + "mA•h");
		stockLabel.setText(String.valueOf(goods.getStock()));
		priceLabel.setText(goods.getDiscount() != 0 ?
				String.format("%s (%s)",
						StringUtil.FLOAT_FORMAT.format(goods.getPrice() * (1 - goods.getDiscount() * 0.01)),
						StringUtil.INTEGER_FORMAT.format(goods.getPrice()))
				:
				StringUtil.FLOAT_FORMAT.format(goods.getPrice()));
		discountLabel.setText(goods.getDiscount() == 0 ? "无" : String.format("-%d%%off", goods.getDiscount()));
		modelLabel.setText(goods.getModel());
		sumLabel.setText(String.valueOf(goods.getSum()));
		createTimeLabel.setText(DateUtil.replaceT(goods.getCreateTime()));
		updateTimeLabel.setText(DateUtil.replaceT(goods.getUpdateTime()));
		describeLabel.setText(goods.getDescribe());
	}

	public Goods getGoods() {
		return goods;
	}
}
