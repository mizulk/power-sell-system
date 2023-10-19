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

@Slf4j
@SuppressWarnings("unused")
public class SupplyServiceImpl implements SupplyService {

	@Override
	public PageBean<Supply> querySupply(int page, int pageSize, Supply supply) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplyMapper supplyMapper = sqlSession.getMapper(SupplyMapper.class);
		Page<Supply> supplies = PageHelper.startPage(page, pageSize).doSelectPage(() -> supplyMapper.page(supply == null ? new Supply() : supply));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(supplies.getTotal(), supplies.getResult());
	}
}
