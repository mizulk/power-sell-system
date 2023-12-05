package team.skadi.powersellsys;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.mapper.SupplierMapper;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;

@Slf4j
public class MainTest {

	@Test
	public void testMapper() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		List<Supplier> suppliers = supplierMapper.page(new Supplier());
		for (Supplier supplier : suppliers) {
			log.debug("{}", supplier.toString());
		}
		sqlSession.close();
	}

	@Test
	public void testService() {
//		String s = UserService.class.getPackageName() + ".impl.";
//		String s1 = UserService.class.getSimpleName() + "Impl";
//		Class<Service> aClass = (Class<Service>) Class.forName(s + s1);
//		System.out.println(aClass.newInstance());
		UserService userService = ServiceUtil.getService(UserService.class);
		log.debug("{}", userService);
	}
}
