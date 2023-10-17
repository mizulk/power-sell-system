package team.skadi.powersellsys.service.impl;

import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.ManagerMapper;
import team.skadi.powersellsys.pojo.Manager;
import team.skadi.powersellsys.service.ManagerService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

@SuppressWarnings("unused")
public class ManagerServiceImpl implements ManagerService {
	@Override
	public Manager login(Short jobNumber, String password) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
		Manager manager = managerMapper.findManagerByJobNumberAndPassword(jobNumber, password);
		sqlSession.commit();
		sqlSession.close();
		return manager;
	}

	@Override
	public Manager queryManager(Short jobNumber) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
		Manager manager = managerMapper.findManagerByJobNumber(jobNumber);
		sqlSession.commit();
		sqlSession.close();
		return manager;
	}
}
