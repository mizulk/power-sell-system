package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class SupplyInformationPanel extends BasicComponent {

	private JLabel powerNameLabel;
	private JLabel supplierNameLabel;
	private JLabel sumLabel;
	private JLabel createTimeLabel;
	private JLabel supplyTimeLabel;
	private Supply supply;

	public SupplyInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.insets.set(0, 0, 10, 10);
		ImageLabel imageLabel;
		// (1,1)
		imageLabel = new ImageLabel("电源名称：", "/images/goods.png");
		add(imageLabel, gbc);
		// (2,1)
		powerNameLabel = new JLabel();
		add(powerNameLabel, gbc);

		// (3,1)
		imageLabel = new ImageLabel("供应商名: ", "/images/supplier.png");
		add(imageLabel, gbc);
		// (4,1)
		supplierNameLabel = new JLabel();
		add(supplierNameLabel, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		// (1-2,2)
		imageLabel = new ImageLabel("供应数量：", "/images/sum-supply.png");
		add(imageLabel, gbc);
		// (2-4,2)
		sumLabel = new JLabel();
		add(sumLabel, gbc);

		gbc.gridy++;
		// (1-2,3)
		imageLabel = new ImageLabel("创建时间：", "/images/time.png");
		add(imageLabel, gbc);
		// (2-4,3)
		createTimeLabel = new JLabel();
		add(createTimeLabel, gbc);

		gbc.gridy++;
		// (1-2,4)
		imageLabel = new ImageLabel("供应时间：", "/images/supply.png");
		add(imageLabel, gbc);
		// (2-4,4)
		supplyTimeLabel = new JLabel();
		add(supplyTimeLabel, gbc);
	}

	public void showSupply(Supply supply) {
		this.supply = supply;
		String goodsName = ServiceUtil.getService(GoodsService.class).getGoodsNameById(supply.getPowerId());
		String supplierName = ServiceUtil.getService(SupplierService.class).getSupplierNameById(supply.getSupplierId());
		powerNameLabel.setText(goodsName);
		supplierNameLabel.setText(supplierName);
		sumLabel.setText(String.valueOf(supply.getSum()));
		createTimeLabel.setText(DateUtil.replaceT(supply.getCreateTime()));
		supplyTimeLabel.setText(supply.getSupplyTime() == null ? "尚未供应" : DateUtil.replaceT(supply.getSupplyTime()));
	}

	public Supply getSupply() {
		return supply;
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
