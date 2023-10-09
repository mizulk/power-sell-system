package team.skadi.powersellsys;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.skadi.powersellsys.mapper.SupplierMapper;
import team.skadi.powersellsys.mapper.UserMapper;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.util.List;

public class MainTest {

	@Test
	public void test(){
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		List<Supplier> suppliers = supplierMapper.selectAll();
		for (Supplier supplier: suppliers) {
			System.out.println(supplier);
		}
		sqlSession.close();
	}
}
