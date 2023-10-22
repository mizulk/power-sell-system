package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.PowerType;

import java.util.List;

public interface GoodsService extends Service {

	void putOnShelf(Goods goods);

	PageBean<Goods> queryGoods(int page, int pageSize, Goods goods);

	String getGoodsNameById(Integer id);

	List<PowerType> getAllPowerType();

	boolean addNewPowerType(PowerType powerType);

	boolean delPowerType(PowerType powerType);

	void updatePowerType(PowerType powerType);

	void updateGoods(Goods goods);

	void addNewGoods(Goods goods);

	void putOffShelf(Goods goods);

	PageBean<Goods> getRank(int page, int pageSize);
}
