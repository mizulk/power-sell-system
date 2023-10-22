package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PowerType;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class GoodsDialog extends EditDialog<Goods> {

	private JTextField nameField;
	private JComboBox<PowerType> typeComboBox;
	private JTextField modelField;
	private JSpinner capacitySpinner;
	private JSpinner stockSpinner;
	private JSpinner priceSpinner;
	private JSpinner discountSpinner;
	private JTextField describeField;
	private JButton addTypeBtn;
	private DefaultComboBoxModel<PowerType> powerTypComboBoxModel;

	public GoodsDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加商品" : "修正商品", mode);
	}


	@Override
	protected void buildInputLayout() {
		nameField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("电源名称(必填)：", nameField);

		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		JPanel typePanel = new JPanel(new BorderLayout(0, 5));
		powerTypComboBoxModel = new DefaultComboBoxModel<>(goodsService.getAllPowerType().toArray(new PowerType[]{}));
		typeComboBox = new JComboBox<>(powerTypComboBoxModel);
		typePanel.add(typeComboBox, BorderLayout.CENTER);
		addTypeBtn = new JButton("+");
		typePanel.add(addTypeBtn, BorderLayout.EAST);
		addField("电源类型：", typePanel);

		modelField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("型号(必填)：", modelField);

		capacitySpinner = new JSpinner(new SpinnerNumberModel(1000, 1, Integer.MAX_VALUE, 1));
		addField("容量(mA•h)：", capacitySpinner);

		stockSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		addField("库存：", stockSpinner);

		priceSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.01, Integer.MAX_VALUE, 0.05));
		addField("单价：", priceSpinner);

		discountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
		addField("折扣(off)：", discountSpinner);

		describeField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("描述：", describeField);
	}

	@Override
	public void setData(Goods data) {
		super.setData(data);
		nameField.setText(data.getName());
		typeComboBox.setSelectedItem(data.getType());
		modelField.setText(data.getModel());
		capacitySpinner.setValue(data.getCapacity());
		stockSpinner.setValue(data.getStock());
		priceSpinner.setValue(data.getPrice());
		discountSpinner.setValue(data.getDiscount());
		describeField.setText(data.getDescribe() == null ? "暂无描述" : data.getDescribe());
	}

	@Override
	protected void addListener() {
		super.addListener();
		addTypeBtn.addActionListener((e) -> {
			PowerTypeDialog powerTypeDialog = new PowerTypeDialog(this, EditDialog.ADD_MODE);
			if (powerTypeDialog.getOption() == CONFIRM_OPTION) {
				powerTypComboBoxModel.addElement(powerTypeDialog.getData());
			}
		});
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (nameField.getText().equals("") && modelField.getText().equals("")) {
			return error("请输入必填项");
		} else {
			Goods goods = new Goods();
			goods.setName(nameField.getText());
			goods.setTypeId(((PowerType) Objects.requireNonNull(typeComboBox.getSelectedItem())).getId());
			goods.setModel(modelField.getText());
			goods.setCapacity((Integer) capacitySpinner.getValue());
			goods.setStock((Integer) stockSpinner.getValue());
			if (priceSpinner.getValue() instanceof Float f)
				goods.setPrice(f);
			else
				goods.setPrice(((Double) priceSpinner.getValue()).floatValue());
			if (discountSpinner.getValue() instanceof Byte b)
				goods.setDiscount(b);
			else
				goods.setDiscount(((Integer) discountSpinner.getValue()).byteValue());
			goods.setDescribe(describeField.getText().equals("") ? null : describeField.getText());

			GoodsService goodsService = ServiceUtil.getService(GoodsService.class);

			if (data != null
					&& goods.getName().equals(data.getName())
					&& goods.getTypeId().equals(data.getTypeId())
					&& goods.getModel().equals(data.getModel())
					&& goods.getCapacity().equals(data.getCapacity())
					&& goods.getStock().equals(data.getStock())
					&& goods.getPrice().equals(data.getPrice())
					&& goods.getDiscount().equals(data.getDiscount())
					&& goods.getDescribe().equals(data.getDescribe())
			) return error("信息未修改");

			if (Objects.nonNull(data)) {
				data.setName(goods.getName());
				data.setTypeId(goods.getTypeId());
				data.setModel(goods.getModel());
				data.setCapacity(goods.getCapacity());
				data.setStock(goods.getStock());
				data.setPrice(goods.getPrice());
				data.setDiscount(goods.getDiscount());
				data.setDescribe(goods.getDescribe());
				goodsService.updateGoods(data);
				return successAndExit("修改成功");
			} else data = goods;

			goodsService.addNewGoods(goods);
			return successAndExit("添加成功");
		}
	}
}
