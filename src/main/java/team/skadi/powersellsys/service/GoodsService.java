package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;

public interface GoodsService extends Service {

	void putOnShelf(Goods goods);

	PageBean<Goods> queryGoods(int page, int pageSize, Goods goods);

	void puyOffShelf(Goods goods);


}
