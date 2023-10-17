package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Manager;

public interface ManagerService extends Service {

	Manager login(Short jobNumber, String password);
}
