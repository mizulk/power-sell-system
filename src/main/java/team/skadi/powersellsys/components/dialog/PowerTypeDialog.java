package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.PowerType;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PowerTypeDialog extends EditDialog<PowerType> {

	private JTextField typeField;

	public PowerTypeDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加电源种类" : "修改电源种类", mode);
	}

	public PowerTypeDialog(JDialog owner, int mode) {
		super(owner, mode == ADD_MODE ? "添加电源种类" : "修改电源种类", mode);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void buildInputLayout() {
		typeField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("种类名称：", typeField);
	}

	@Override
	public void setData(PowerType data) {
		super.setData(data);
		typeField.setText(data.getValue());
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (typeField.getText().equals("")) {
			return error("请输入电源种类名称");
		} else {
			PowerType powerType = new PowerType();
			powerType.setValue(typeField.getText());

			if (data != null && powerType.getValue().equals(data.getValue())) {
				return error("未修改任何信息");
			}

			GoodsService goodsService = ServiceUtil.getService(GoodsService.class);

			if (data != null) {
				data.setValue(powerType.getValue());
				goodsService.updatePowerType(data);
				return successAndExit("修改成功");
			} else {
				data = powerType;
			}

			if (goodsService.addNewPowerType(powerType))
				return successAndExit("添加成功");
			else
				return error("添加失败，已存在该选项");
		}
	}
}
