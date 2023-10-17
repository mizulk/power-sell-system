package team.skadi.powersellsys.store;

import team.skadi.powersellsys.App;

/**
 * 全局存储器, 通过{@link App#useStore()}进行访问，用来存储一些简单数据。
 */
public final class Store {
	public ManagerStore managerStore;
	public UserStore userStore;
	public SupplierStore supplierStore;
}
