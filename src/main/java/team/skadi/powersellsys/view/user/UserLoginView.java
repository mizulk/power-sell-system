package team.skadi.powersellsys.view.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.view.LoginView;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;

public class UserLoginView extends LoginView {

	public UserLoginView(App app) {
		super(app);
	}

	@Override
	protected void buildTextField(JPanel centerPanel, GridBagConstraints gbc) {

	}

	@Override
	protected String getTitleString() {
		return "用户";
	}

	@Override
	protected void login() {
		app.useRouter().showView(ViewName.USER_LOGIN_VIEW,ViewName.USER_MAIN_VIEW);
	}

	@Override
	protected void register() {

	}


}
