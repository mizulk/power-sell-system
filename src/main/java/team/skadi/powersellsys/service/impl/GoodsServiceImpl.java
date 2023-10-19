package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.GoodsMapper;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;

@Slf4j
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
	public PageBean<Goods> queryGoods(int page, int pageSize, Goods goods) {
		log.info("商品分页查询，page：{}，pageSize：{}，goods：{}", page, pageSize, goods);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		Page<Goods> goodsPage = PageHelper.startPage(page, pageSize).doSelectPage(() -> goodsMapper.page(goods == null ? new Goods() : goods));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(goodsPage.getTotal(), goodsPage.getResult());
	}

	@Override
	public void puyOffShelf(Goods goods) {

	}

}
