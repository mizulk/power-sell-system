package team.skadi.powersellsys.view.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.view.LoginView;

public class UserLoginView extends LoginView {

	public UserLoginView(App app) {
		super(app);
	}

	@Override
	protected String getTitleString() {
		return "用户";
	}

	@Override
	protected void login() {
		System.out.println("user");
	}

	@Override
	protected void register() {

	}


}
