package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.view.LoginView;

public class SupplierLoginView extends LoginView {

	public SupplierLoginView(App app) {
		super(app);
	}

	@Override
	protected String getTitleString() {
		return "供应商";
	}

	@Override
	protected void login() {
		System.out.println("supplier");
		app.useRouter().showView(ViewName.SUPPLIER_LOGIN_VIEW,ViewName.SUPPLIER_MAIN_VIEW);
	}

	@Override
	protected void register() {

	}

}
