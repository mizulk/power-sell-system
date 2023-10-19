package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierService extends Service{

	List<Supplier> getAllSupplier();

	Supplier login(String account, String password);

    String register(Supplier supplier);

	boolean getTelexists(String tel);

    Supplier querySupplier(String account);
}
