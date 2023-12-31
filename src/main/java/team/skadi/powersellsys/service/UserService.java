package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;

public interface UserService extends Service {

	User login(String account, String password);

	String register(User user);

	PageBean<User> queryUser(int page, int pageSize, User user);

	User queryUser(String account);

	String getUserNameById(Integer id);

	boolean delUser(String userAccount);

	void updateUser(User user);

	void updateLoginTime(String account);

	boolean isUserExist(Integer id);
}
