package team.skadi.powersellsys.router;

import team.skadi.powersellsys.view.BasicView;
import team.skadi.powersellsys.view.HelpView;
import team.skadi.powersellsys.view.manager.ManagerLoginView;
import team.skadi.powersellsys.view.manager.ManagerMainView;
import team.skadi.powersellsys.view.supplier.SupplierLoginView;
import team.skadi.powersellsys.view.SelectLoginView;
import team.skadi.powersellsys.view.supplier.SupplierMainView;
import team.skadi.powersellsys.view.user.UserLoginView;

/**
 * 该枚举类是用于切换不同页面
 */
public enum ViewName {
	SELECT_LOGIN_VIEW("selectLogin", SelectLoginView.class),
	USER_LOGIN_VIEW("userLogin", UserLoginView.class),
	SUPPLIER_LOGIN_VIEW("supperLogin", SupplierLoginView.class),
	SUPPLIER_MAIN_VIEW("supplierMain", SupplierMainView.class),
	MANAGER_LOGIN_VIEW("managerLogin", ManagerLoginView.class),
	MANAGER_MAIN_VIEW("managerMain", ManagerMainView.class),
	HELP_VIEW("helpView", HelpView.class);
//	在这里新建类，UserMainView.java -> USER_MAIN_VIEW("userMain", UserMainView.class)

	/** 页面名称，用于跳转 */
	private final String value;
	/** 页面类型，用于初始化 */
	private final Class<? extends BasicView> target;

	ViewName(String value, Class<? extends BasicView> target) {
		this.value = value;
		this.target = target;
	}

	public String getValue() {
		return value;
	}

	public Class<? extends BasicView> getTarget() {
		return target;
	}
}
