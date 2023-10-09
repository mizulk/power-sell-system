package team.skadi.powersellsys.service.impl;

import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.SupplierMapper;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
	@Override
	public List<Supplier> getAllSupplier() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
		List<Supplier> suppliers = mapper.selectAll();

		sqlSession.commit();
		sqlSession.close();

		return suppliers;
	}
}
