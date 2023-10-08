package team.skadi.powersellsys.view.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.view.LoginView;

public class ManagerLoginView extends LoginView {


	public ManagerLoginView(App app) {
		super(app);
	}

	@Override
	protected String getTitleString() {
		return "管理员";
	}

	@Override
	protected void login() {
		app.useRouter().showView(ViewName.MANAGER_LOGIN_VIEW, ViewName.MANAGER_MAIN_VIEW);
	}

	@Override
	protected void register() {

	}

}
