package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supply;

public interface SupplyService extends Service {

	PageBean<Supply> querySupply(int page, int pageSize, Supply supply);
}
