package team.skadi.powersellsys.components.dialog;

import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;

public class FavoriteDialog extends EditDialog<FavoritePower>{

	private JSpinner powerId;

	public FavoriteDialog(JFrame frame) {
		super(frame, "添加收藏", MODIFY_MODE);
	}

	@Override
	protected void buildInputLayout() {
		powerId = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
		addField("收藏的电源id：",powerId);
	}

	@Override
	public void setData(FavoritePower powerId) {
		super.setData(powerId);
	}

	@Override
	protected boolean onConfirmButtonClick() {
		FavoritePower favorite = new FavoritePower();
		if (powerId.getValue() instanceof Integer p) {
			favorite.setPowerId(p);
		}else {
			favorite.setPowerId(((Integer) powerId.getValue()).intValue());
		}

		FavoritePowerService favoritePowerService = ServiceUtil.getService(FavoritePowerService.class);

		if (data != null && favorite.getPowerId().equals(data.getPowerId())) {
			return error("未添加收藏");
		}
		favoritePowerService.addNewFavorite(favorite);
		return successAndExit("添加成功");
	}
}
