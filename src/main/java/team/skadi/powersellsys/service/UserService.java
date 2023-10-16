package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;

public interface UserService extends Service {

	User login(String account, String password);

	String register(User user);

	PageBean<User> queryUser(int page, int pageSize, User user);
}
