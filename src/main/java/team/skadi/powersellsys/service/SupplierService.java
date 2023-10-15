package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierService extends Service{

	List<Supplier> getAllSupplier();

	Supplier login(String name, String password);

	void register(Supplier supplier);

	boolean getTelexists(String tel);

}
