package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.select.SelectField;
import team.skadi.powersellsys.components.dialog.select.SelectPowerDialog;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class SupplierSupplyDialog extends EditDialog<Supply> {

	protected JSpinner sumSpinner;
	protected JFormattedTextField supplyTimeField;
	private JButton nowBtn;
	private SelectField selectPowerIdField;

	public SupplierSupplyDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加供应订单" : "修正订单", mode);
	}

	@Override
	protected void buildInputLayout() {
		selectPowerIdField = new SelectField((App) getParent(), TEXT_FIELD_COLUMNS, SelectPowerDialog.class);
		addField("电源id(必填)：", selectPowerIdField);

		sumSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		addField("供应数量：", sumSpinner);

		JPanel supplyTimePanel = new JPanel(new BorderLayout());
		supplyTimeField = new JFormattedTextField(new SimpleDateFormat("yyyy-dd-MM hh:mm:ss"));
		supplyTimePanel.add(supplyTimeField, BorderLayout.CENTER);
		nowBtn = new JButton("现在");
		supplyTimePanel.add(nowBtn, BorderLayout.EAST);
		addField("供应日期：", supplyTimePanel);
	}

	@Override
	protected void addListener() {
		super.addListener();
		nowBtn.addActionListener(e -> supplyTimeField.setText(DateUtil.replaceT(LocalDateTime.now())));
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
			Supply supply = createData();

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(supply.getPowerId())) {
				return error("不存在id为" + supply.getPowerId() + "的电源商品");
			}

			if (isModify(supply)) return error("信息未修改");

			if (data != null) modifyData(supply);

			if (mode == MODIFY_MODE)
				supplyService.updateSupply(data);
			else
				supplyService.addNewSupply(data);
			return super.onConfirmButtonClick();
		}
	}

	@Override
	public void setData(Supply data) {
		super.setData(data);
		if (data.getPowerId() != null)
			selectPowerIdField.setText(String.valueOf(data.getPowerId()));
		if (data.getSum() != null)
			sumSpinner.setValue(data.getSum());
		if (data.getSupplyTime() != null)
			supplyTimeField.setText(DateUtil.replaceT(data.getSupplyTime()));
	}

	@Override
	protected boolean isInputted() {
		return selectPowerIdField.isInputted();
	}

	@Override
	protected Supply createData() {
		Supply supply = new Supply();
		supply.setPowerId(Integer.valueOf(selectPowerIdField.getText()));
		supply.setSum((Integer) sumSpinner.getValue());
		supply.setSupplyTime(supplyTimeField.getText().equals("") ? null :
				DateUtil.parse(supplyTimeField.getText()));
		return supply;
	}

	@Override
	protected boolean isModify(Supply supply) {
		return data != null
				&& supply.getPowerId().equals(data.getPowerId())
				&& supply.getSum().equals(data.getSum())
				&& supply.getSupplyTime().equals(data.getSupplyTime());
	}

	@Override
	protected void modifyData(Supply supply) {
		data.setPowerId(supply.getPowerId());
		data.setSum(supply.getSum());
		data.setSupplyTime(supply.getSupplyTime());
	}
}
