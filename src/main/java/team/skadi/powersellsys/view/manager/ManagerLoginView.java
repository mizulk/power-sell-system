package team.skadi.powersellsys.view.manager;

import team.skadi.powersellsys.App;
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

	}

	@Override
	protected void register() {

	}

}
