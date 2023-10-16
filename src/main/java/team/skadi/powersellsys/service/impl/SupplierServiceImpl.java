package team.skadi.powersellsys.service.impl;

import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.SupplierMapper;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.util.List;

@SuppressWarnings("unused")
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

    @Override
    public Supplier login(String name, String password) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        Supplier supplier = supplierMapper.getSupplierByAccountAndPassword(name, password);
        sqlSession.commit();
        sqlSession.close();
        return supplier;
    }


    @Override
    public void register(Supplier supplier) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        supplierMapper.insertAccount(supplier);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public boolean getTelexists(String tel) {
            SqlSession sqlSession = SqlSessionUtil.getSqlSession();
            SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
            Supplier telexists = supplierMapper.getTelexists(tel);
            if (telexists != null){
                sqlSession.commit();
                sqlSession.close();
                return true;
            }else{
                return  false;
            }
    }

}
