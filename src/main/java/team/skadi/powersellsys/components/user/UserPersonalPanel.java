package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.UserInformationPanel;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserPersonalPanel extends BasicComponent {

	private UserInformationPanel userInformationPanel;

	public UserPersonalPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		userInformationPanel = new UserInformationPanel(app);
		userInformationPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		add(userInformationPanel, BorderLayout.CENTER);
	}

	public void initData() {
		UserService userService = ServiceUtil.getService(UserService.class);
		User user = userService.queryUser(app.useStore().userStore.account());
		userInformationPanel.showUser(user);
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
