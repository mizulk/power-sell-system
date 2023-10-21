package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierService extends Service {

	List<Supplier> getAllSupplier();

	Supplier login(String account, String password);

	String register(Supplier supplier);

	boolean getTelexists(String tel);

	PageBean<Supplier> querySupplier(int page, int pageSize, Supplier supplier);

	void updateSupplier(Supplier supplier);

	Supplier querySupplier(String account);

	String getSupplierNameById(Integer id);
}
