package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Objects;

public class OrderDialog extends EditDialog<Order> implements ChangeListener {

	private JTextField userIdField;
	private JTextField powerIdField;
	private JSpinner sumSpinner;
	private JSpinner priceSpinner;
	private JTextField amountField;

	public OrderDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加订单" : "修改订单", mode);
	}

	@Override
	protected void buildInputLayout() {
		userIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("用户id(必填)：", userIdField);

		powerIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("电源id(必填)：", powerIdField);

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
	public void setData(Order data) {
		super.setData(data);
		userIdField.setText(String.valueOf(data.getUserId()));
		powerIdField.setText(String.valueOf(data.getPowerId()));
		sumSpinner.setValue(data.getSum());
		priceSpinner.setValue(data.getAmount() / data.getSum());
		priceSpinner.setEnabled(false);
		amountField.setText(String.valueOf(data.getAmount()));
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (userIdField.getText().equals("")
				&& powerIdField.getText().equals("")
				&& priceSpinner.getValue().equals(0)) {
			return error("请输入必填项");
		} else {
			Order order = new Order();
			order.setUserId(Integer.valueOf(userIdField.getText()));
			order.setPowerId(Integer.valueOf(powerIdField.getText()));
			order.setSum((Integer) sumSpinner.getValue());
			order.setAmount(Float.valueOf(amountField.getText()));

			OrderService orderService = ServiceUtil.getService(OrderService.class);

			if (!ServiceUtil.getService(UserService.class).isUserExist(order.getUserId())) {
				return error("不存在id为" + order.getUserId() + "的用户！");
			}

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(order.getPowerId())) {
				return error("不存在id为" + order.getPowerId() + "的电源商品");
			}

			if (data != null
					&& order.getUserId().equals(data.getUserId())
					&& order.getPowerId().equals(data.getPowerId())
					&& order.getSum().equals(data.getSum())
					&& order.getAmount().equals(data.getAmount())
			) return error("信息未修改");

			if (Objects.nonNull(data)) {
				data.setUserId(order.getUserId());
				data.setPowerId(order.getPowerId());
				data.setSum(order.getSum());
				data.setAmount(order.getAmount());
				orderService.updateOrder(data);
				return successAndExit("修改成功");
			} else {
				data = order;
			}
			orderService.addNewOrder(order);
			return successAndExit("添加成功");
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (priceSpinner.getValue() instanceof Float f)
			amountField.setText(String.valueOf(f * ((int) sumSpinner.getValue())));
		else
			amountField.setText(String.valueOf(((Double) priceSpinner.getValue()) * ((int) sumSpinner.getValue())));
	}
}
