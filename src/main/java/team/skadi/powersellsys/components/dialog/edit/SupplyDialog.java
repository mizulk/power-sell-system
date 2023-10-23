package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.select.SelectField;
import team.skadi.powersellsys.components.dialog.select.SelectSupplierDialog;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import java.util.Objects;

public class SupplyDialog extends SupplierSupplyDialog {

	private SelectField selectSupplierField;

	public SupplyDialog(JFrame frame, int mode) {
		super(frame, mode);
	}

	@Override
	protected void buildInputLayout() {
		selectSupplierField = new SelectField((App) getParent(), TEXT_FIELD_COLUMNS, SelectSupplierDialog.class);
		addField("供应商id(必填)：", selectSupplierField);

		super.buildInputLayout();
	}

	@Override
	public void setData(Supply data) {
		super.setData(data);
		selectSupplierField.setText(String.valueOf(data.getSupplierId()));

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
		return super.isInputted() && selectSupplierField.isInputted();
	}

	@Override
	protected Supply createData() {
		Supply supply = super.createData();
		supply.setSupplierId(Integer.valueOf(selectSupplierField.getText()));
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
