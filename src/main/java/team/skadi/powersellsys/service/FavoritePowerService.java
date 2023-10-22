package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.pojo.PageBean;

public interface FavoritePowerService extends Service{

	PageBean<FavoritePower> queryFavorite(int page, int pageSize, FavoritePower favoritePower);

	FavoritePower queryUserId(Integer userId);

	FavoritePower queryPowerId(Integer powerId);

	void updateFavorite(FavoritePower favoritePower);

	boolean delFavorite(Integer favoritePower);

	void addNewFavorite(FavoritePower favoritePower);
}
