package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Favorite;
import team.skadi.powersellsys.pojo.PageBean;

public interface FavoriteService extends Service{

	PageBean<Favorite> queryFavorite(int page, int pageSize, Favorite favorite);

	Favorite queryUserid(Integer userId);

	Favorite queryPowerid(Integer powerId);

}
