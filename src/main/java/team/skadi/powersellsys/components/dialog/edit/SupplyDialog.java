package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Objects;

public class SupplyDialog extends SupplierSupplyDialog {

	private JTextField supplierIdField;

	public SupplyDialog(JFrame frame, int mode) {
		super(frame, mode);
	}

	@Override
	protected void buildInputLayout() {
		supplierIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("供应商id(必填)：", supplierIdField);

		super.buildInputLayout();
	}

	@Override
	public void setData(Supply data) {
		super.setData(data);
		supplierIdField.setText(String.valueOf(data.getSupplierId()));

	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			Supply supply = createData();

			SupplyService supplyService = ServiceUtil.getService(SupplyService.class);

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(supply.getPowerId())) {
				return error("不存在id为" + supply.getPowerId() + "的电源商品");
			}

			if (!ServiceUtil.getService(SupplierService.class).isSupplierExist(supply.getPowerId())) {
				return error("不存在id为" + supply.getPowerId() + "的供应商");
			}

			if (isModify(supply)) return error("信息未修改");

			if (Objects.nonNull(data)) {
				modifyData(supply);
				supplyService.updateSupply(data);
				return successAndExit("修改成功");
			} else {
				data = supply;
			}

			supplyService.addNewSupply(supply);
			return successAndExit("添加成功");
		}
	}

	@Override
	protected boolean isInputted() {
		return super.isInputted() && supplierIdField.getText().equals("");
	}

	@Override
	protected Supply createData() {
		Supply supply = super.createData();
		supply.setSupplierId(Integer.valueOf(supplierIdField.getText()));
		return supply;
	}

	@Override
	protected boolean isModify(Supply supply) {
		return super.isModify(supply)
				&& supply.getSupplierId().equals(data.getSupplierId());
	}

	@Override
	protected void modifyData(Supply supply) {
		super.modifyData(supply);
		data.setSupplierId(supply.getSupplierId());
	}
}
