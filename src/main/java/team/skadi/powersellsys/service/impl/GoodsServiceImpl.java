package team.skadi.powersellsys.service.impl;

import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.GoodsMapper;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;


@SuppressWarnings("unused")
public class GoodsServiceImpl implements GoodsService {
    @Override
    public void putOnShelf(Goods goods) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        goodsMapper.putOnShelf(goods);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void puyOffShelf(Goods goods) {

    }

}
