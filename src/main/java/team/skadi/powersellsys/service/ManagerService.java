package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Manager;

public interface ManagerService extends Service {

	/**
	 * 管理员登入
	 *
	 * @param jobNumber 管理员工号
	 * @param password  密码
	 * @return null：账号或密码错误
	 */
	Manager login(Short jobNumber, String password);

	/**
	 * 根据工号查询指定的管理员
	 *
	 * @param jobNumber 工号
	 * @return null：不存在
	 */
	Manager queryManager(Short jobNumber);
}
