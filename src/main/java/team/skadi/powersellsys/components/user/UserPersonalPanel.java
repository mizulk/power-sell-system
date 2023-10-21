package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.UserInformationPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.UserDialog;
import team.skadi.powersellsys.model.manager.UserTableModel;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserPersonalPanel extends BasicComponent implements ListSelectionListener {

	private UserInformationPanel userInformationPanel;

	private JButton modifyBtn;
	private User user;

	public UserPersonalPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		userInformationPanel = new UserInformationPanel(app);
		userInformationPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		add(userInformationPanel, BorderLayout.CENTER);

		modifyBtn = new JButton("修改个人信息");
		add(modifyBtn, BorderLayout.SOUTH);

	}

	public void initData() {
		UserService userService = ServiceUtil.getService(UserService.class);
		user = userService.queryUser(app.useStore().userStore.account());
		userInformationPanel.showUser(user);
	}

	@Override
	protected void addListener() {
		modifyBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == modifyBtn) {
			modify();
		}
	}

	private void modify() {
		UserDialog userDialog = new UserDialog(app, UserDialog.MODIFY_MODE);
		userDialog.setData(user);
		if (userDialog.getOption()==BasicDialog.CONFIRM_OPTION){
			JOptionPane.showMessageDialog(app,"修改成功");
			userInformationPanel.showUser(userDialog.getData());
		} else {
			JOptionPane.showMessageDialog(app,"已取消");
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}
}
