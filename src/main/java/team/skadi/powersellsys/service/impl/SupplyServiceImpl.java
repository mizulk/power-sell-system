package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.SupplyMapper;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supply;
import team.skadi.powersellsys.service.SupplyService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;

@Slf4j
@SuppressWarnings("unused")
public class SupplyServiceImpl implements SupplyService {

	@Override
	public PageBean<Supply> querySupply(int page, int pageSize, Supply supply) {
		log.info("供应订单分页查询，page：{}，pageSize：{}，supply：{}", page, pageSize, supply);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplyMapper supplyMapper = sqlSession.getMapper(SupplyMapper.class);
		Page<Supply> supplies = PageHelper.startPage(page, pageSize).doSelectPage(() -> supplyMapper.page(supply == null ? new Supply() : supply));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(supplies.getTotal(), supplies.getResult());
	}

	@Override
	public void updateSupply(Supply supply) {
		log.info("修改供应订单：{}", supply);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			SupplyMapper supplyMapper = sqlSession.getMapper(SupplyMapper.class);
			supplyMapper.updateSupply(supply);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("修改供应订单时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void addNewSupply(Supply supply) {
		log.info("增加新的供应订单：{}", supply);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			SupplyMapper supplyMapper = sqlSession.getMapper(SupplyMapper.class);
			supply.setCreateTime(LocalDateTime.now());
			supplyMapper.insertNewSupply(supply);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("添加新的供应订单时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
}
