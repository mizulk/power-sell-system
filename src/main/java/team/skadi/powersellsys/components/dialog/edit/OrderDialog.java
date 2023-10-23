package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.select.SelectField;
import team.skadi.powersellsys.components.dialog.select.SelectUserDialog;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import java.util.Objects;

public class OrderDialog extends UserOrderDialog {

	private SelectField selectUserIdField;

	public OrderDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加订单" : "修改订单", mode);
	}

	@Override
	protected void buildInputLayout() {

		selectUserIdField = new SelectField((App) getParent(), TEXT_FIELD_COLUMNS, SelectUserDialog.class);
		addField("用户id(必填)：", selectUserIdField);

		super.buildInputLayout();
	}

	@Override
	protected void addListener() {
		super.addListener();
	}

	@Override
	protected boolean isInputted() {
		return super.isInputted() && selectUserIdField.isInputted();
	}

	@Override
	protected Order createData() {
		Order order = super.createData();
		order.setUserId(Integer.valueOf(selectUserIdField.getText()));
		return order;
	}

	@Override
	protected boolean isModify(Order order) {
		return super.isModify(order)
				&& order.getUserId().equals(data.getUserId());
	}

	@Override
	protected void modifyData(Order order) {
		super.modifyData(order);
		data.setUserId(order.getUserId());
	}

	@Override
	public void setData(Order data) {
		super.setData(data);
		selectUserIdField.setText(String.valueOf(data.getUserId()));
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			Order order = createData();
			OrderService orderService = ServiceUtil.getService(OrderService.class);

			if (!ServiceUtil.getService(UserService.class).isUserExist(order.getUserId())) {
				return error("不存在id为" + order.getUserId() + "的用户！");
			}

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(order.getPowerId())) {
				return error("不存在id为" + order.getPowerId() + "的电源商品");
			}

			if (isModify(order)) return error("信息未修改");

			if (Objects.nonNull(data)) {
				modifyData(order);
				orderService.updateOrder(data);
				return successAndExit("修改成功");
			} else {
				data = order;
			}
			orderService.addNewOrder(order);
			return successAndExit("添加成功");
		}
	}
}
