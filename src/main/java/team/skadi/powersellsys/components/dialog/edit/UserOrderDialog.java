package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.select.SelectField;
import team.skadi.powersellsys.components.dialog.select.SelectPowerDialog;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UserOrderDialog extends EditDialog<Order> implements ChangeListener {

	protected JSpinner sumSpinner;
	protected JSpinner priceSpinner;
	protected JTextField amountField;
	private SelectField selectPowerIdField;

	public UserOrderDialog(JFrame frame, String title, int mode) {
		super(frame, title, mode);
	}

	@Override
	protected void buildInputLayout() {
		selectPowerIdField = new SelectField((App) getParent(), TEXT_FIELD_COLUMNS, SelectPowerDialog.class);
		addField("电源id(必填)：", selectPowerIdField);

		sumSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		sumSpinner.addChangeListener(this);
		addField("订购数量：", sumSpinner);

		priceSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 0.5));
		priceSpinner.addChangeListener(this);
		addField("单价：", priceSpinner);

		amountField = new JTextField(TEXT_FIELD_COLUMNS);
		amountField.setEditable(false);
		amountField.setText("1");
		addField("金额:", amountField);
	}

	@Override
	protected boolean isInputted() {
		return selectPowerIdField.isInputted();
	}

	@Override
	protected Order createData() {
		Order order = new Order();
		order.setPowerId(Integer.valueOf(selectPowerIdField.getText()));
		order.setSum((Integer) sumSpinner.getValue());
		order.setAmount(Float.valueOf(amountField.getText()));
		return order;
	}

	@Override
	protected boolean isModify(Order order) {
		return data != null
				&& order.getPowerId().equals(data.getPowerId())
				&& order.getSum().equals(data.getSum())
				&& order.getAmount().equals(data.getAmount());
	}

	@Override
	protected void modifyData(Order order) {
		data.setPowerId(order.getPowerId());
		data.setSum(order.getSum());
		data.setAmount(order.getAmount());
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必须项");
		} else {
			OrderService orderService = ServiceUtil.getService(OrderService.class);
			Order order = createData();
			if (isModify(order)) return error("信息未修改");

			modifyData(order);

			if (mode == MODIFY_MODE)
				orderService.updateOrder(data);
			else
				orderService.addNewOrder(data);
			return super.onConfirmButtonClick();
		}
	}

	@Override
	public void setData(Order data) {
		super.setData(data);
		selectPowerIdField.setText(String.valueOf(data.getPowerId()));
		if (data.getSum() != null)
			sumSpinner.setValue(data.getSum());
		if (data.getAmount() != null) {
			amountField.setText(String.valueOf(data.getAmount()));
			priceSpinner.setValue(data.getAmount() / data.getSum());
		}
		priceSpinner.setEnabled(false);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (priceSpinner.getValue() instanceof Float f)
			amountField.setText(String.valueOf(f * ((int) sumSpinner.getValue())));
		else
			amountField.setText(String.valueOf(((Double) priceSpinner.getValue()) * ((int) sumSpinner.getValue())));
	}
}
