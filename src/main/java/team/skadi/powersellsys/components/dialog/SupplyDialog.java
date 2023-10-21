package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class SupplyDialog extends EditDialog<Supply> {

	private JTextField supplierIdField;
	private JTextField powerIdField;
	private JSpinner sumSpinner;
	private JFormattedTextField supplyTimeField;

	public SupplyDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加供应订单" : "修正订单", mode);
	}

	@Override
	protected void buildInputLayout() {
		supplierIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("供应商id(必填)：", supplierIdField);

		powerIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("电源id(必填)：", powerIdField);

		sumSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		addField("供应数量：", sumSpinner);

		supplyTimeField = new JFormattedTextField(new SimpleDateFormat("yyyy-dd-MM hh:mm:ss"));
		addField("供应日期：", supplyTimeField);
	}

	@Override
	public void setData(Supply data) {
		super.setData(data);
		supplierIdField.setText(String.valueOf(data.getSupplierId()));
		powerIdField.setText(String.valueOf(data.getPowerId()));
		sumSpinner.setValue(data.getSum());
		if (data.getSupplyTime() != null)
			supplyTimeField.setText(DateUtil.replaceT(data.getSupplyTime()));
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (supplierIdField.getText().equals("")
				&& powerIdField.getText().equals("")) {
			return error("请输入必填项");
		} else {
			Supply supply = new Supply();
			supply.setSupplierId(Integer.valueOf(supplierIdField.getText()));
			supply.setPowerId(Integer.valueOf(powerIdField.getText()));
			supply.setSum((Integer) sumSpinner.getValue());
			supply.setSupplyTime(supplyTimeField.getText().equals("") ? null : DateUtil.parse(supplyTimeField.getText()));

			SupplyService supplyService = ServiceUtil.getService(SupplyService.class);

			if (data != null
					&& supply.getSupplierId().equals(data.getSupplierId())
					&& supply.getPowerId().equals(data.getPowerId())
					&& supply.getSum().equals(data.getSum())
					&& supply.getSupplyTime().equals(data.getSupplyTime())
			) return error("信息未修改");

			if (Objects.nonNull(data)) {
				data.setSupplierId(supply.getSupplierId());
				data.setPowerId(supply.getPowerId());
				data.setSum(supply.getSum());
				data.setSupplyTime(supply.getSupplyTime());
				supplyService.updateSupply(data);
				return successAndExit("修改成功");
			} else {
				data = supply;
			}
			supplyService.addNewSupply(supply);
			return successAndExit("添加成功");
		}
	}
}
