package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.text.SimpleDateFormat;

public class SupplierSupplyDialog extends EditDialog<Supply> {

	protected JTextField powerIdField;
	protected JSpinner sumSpinner;
	protected JFormattedTextField supplyTimeField;

	public SupplierSupplyDialog(JFrame frame, String title, int mode) {
		super(frame, title, mode);
	}

	@Override
	protected void buildInputLayout() {
		powerIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("电源id(必填)：", powerIdField);

		sumSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		addField("供应数量：", sumSpinner);

		supplyTimeField = new JFormattedTextField(new SimpleDateFormat("yyyy-dd-MM hh:mm:ss"));
		addField("供应日期：", supplyTimeField);
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			SupplyService supplyService = ServiceUtil.getService(SupplyService.class);
			Supply supply = createData();

			if (isModify(supply)) return error("信息未修改");

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
		powerIdField.setText(String.valueOf(data.getPowerId()));
		sumSpinner.setValue(data.getSum());
		if (data.getSupplyTime() != null)
			supplyTimeField.setText(DateUtil.replaceT(data.getSupplyTime()));
	}

	@Override
	protected boolean isInputted() {
		return powerIdField.getText().equals("");
	}

	@Override
	protected Supply createData() {
		Supply supply = new Supply();
		supply.setPowerId(Integer.valueOf(powerIdField.getText()));
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
