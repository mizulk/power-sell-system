package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Goods;

public interface GoodsService extends Service {

    void putOnShelf(Goods goods);

    void puyOffShelf(Goods goods);


}
