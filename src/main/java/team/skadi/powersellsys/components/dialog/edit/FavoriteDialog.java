package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;

public class FavoriteDialog extends EditDialog<FavoritePower> {

	private JSpinner powerId;

	public FavoriteDialog(JFrame frame) {
		super(frame, "添加收藏", MODIFY_MODE);
	}

	@Override
	protected void buildInputLayout() {
		powerId = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		addField("收藏的电源id：", powerId);
	}

	@Override
	protected boolean isInputted() {
		return powerId.getValue().equals(0);
	}

	@Override
	protected FavoritePower createData() {
		return null;
	}

	@Override
	protected boolean isModify(FavoritePower favoritePower) {
		return false;
	}

	@Override
	protected void modifyData(FavoritePower favoritePower) {

	}

	@Override
	public void setData(FavoritePower powerId) {
		super.setData(powerId);
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) return false;

		FavoritePower favorite = new FavoritePower();
		if (powerId.getValue() instanceof Integer p) {
			favorite.setPowerId(p);
		}

		FavoritePowerService favoritePowerService = ServiceUtil.getService(FavoritePowerService.class);

		if (data != null && favorite.getPowerId().equals(data.getPowerId())) {
			return error("未添加收藏");
		}
		favoritePowerService.addNewFavorite(favorite);
		return successAndExit("添加成功");
	}
}
