package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.SupplierMapper;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.util.List;

@Slf4j
@SuppressWarnings("unused")
public class SupplierServiceImpl implements SupplierService {
	@Override
	public List<Supplier> getAllSupplier() {
		log.info("获取所有供应商");
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
		List<Supplier> suppliers = mapper.selectAll();

		sqlSession.commit();
		sqlSession.close();

		return suppliers;
	}

	@Override
	public Supplier login(String account, String password) {
		log.info("供应商登录，账号：{}，密码：{}", account, password);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		Supplier supplier = supplierMapper.getSupplierByAccountAndPassword(account, password);
		sqlSession.commit();
		sqlSession.close();
		return supplier;
	}


	@Override
	public String register(Supplier supplier) {
		log.info("供应商注册：{}", supplier);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		supplierMapper.insertNewAccount(supplier);
		Integer id = supplierMapper.getEmptyAccountId();
		String account = String.format("%06d", id);
		supplier.setId(id);
		supplier.setAccount(account);
		supplierMapper.updateAccount(supplier);
		sqlSession.commit();
		sqlSession.close();
		return account;
	}

	@Override
	public boolean getTelexists(String tel) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		Supplier telexists = supplierMapper.getTelexists(tel);
		sqlSession.commit();
		sqlSession.close();
		return telexists == null;
	}

	@Override
	public Supplier querySupplier(String account) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		Supplier supplier = supplierMapper.findSupplierByAccount(account);
		sqlSession.commit();
		sqlSession.close();
		return supplier;
	}

	@Override
	public String getSupplierNameById(Integer id) {
		log.info("通过id获取供应商的名称，id：{}", id);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		String name = supplierMapper.findSupplierNameById(id);
		sqlSession.commit();
		sqlSession.close();
		return name;
	}

	@Override
	public PageBean<Supplier> querySupplier(int page, int pageSize, Supplier supplier) {
		log.info("供应商列表查询：page：{}，pageSize：{}，supplier：{}", page, pageSize, supplier);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
		Page<Supplier> suppliers = PageHelper.startPage(page, pageSize).doSelectPage(() -> supplierMapper.page(supplier == null ? new Supplier() : supplier));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(suppliers.getTotal(), suppliers.getResult());
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
			supplierMapper.updateSupplier(supplier);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("更新供应商时出现错误，数据库回滚：", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

}
