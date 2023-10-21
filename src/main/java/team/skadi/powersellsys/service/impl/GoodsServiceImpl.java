package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.GoodsMapper;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.PowerType;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
public class GoodsServiceImpl implements GoodsService {
	@Override
	public void putOnShelf(Goods goods) {
		log.info("商品上架：{}", goods);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		goods.setStatus(1);
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
	public List<PowerType> getAllPowerType() {
		log.info("获取所有电源类型");
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		List<PowerType> powerTypes = goodsMapper.getAllPowerType();
		sqlSession.commit();
		sqlSession.close();
		return powerTypes;
	}

	@Override
	public void updateGoods(Goods goods) {
		log.info("更新电源：{}", goods);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		goodsMapper.updatePower(goods);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void addNewGoods(Goods goods) {
		log.info("");
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
			goods.setStatus(1);
			goods.setSum(0);
			goods.setCreateTime(LocalDateTime.now());
			goods.setUpdateTime(LocalDateTime.now());
			goodsMapper.insertNewPower(goods);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("添加新商品时出现错误，数据库回滚：", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void putOffShelf(Goods goods) {
		log.info("商品下架：{}", goods);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		goods.setStatus(0);
		goodsMapper.putOffShelf(goods);
		sqlSession.commit();
		sqlSession.close();
	}

}
