package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.UserMapper;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;

@Slf4j
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {
	@Override
	public User login(String account, String password) {
		log.debug("用户登录，account: {}, password: {}", account, password);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserByAccountAndPassword(account, password);
		sqlSession.commit();
		sqlSession.close();
		return user;
	}

	@Override
	public String register(User user) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertEmptyAccount();
			int id = userMapper.getEmptyAccountId();
			// 向id前面填充0直到8位
			String account = String.format("%08d", id);
			user.setAccount(account);
			user.setCreateTime(LocalDateTime.now());
			user.setUpdateTime(LocalDateTime.now());
			userMapper.updateUser(user);
			sqlSession.commit();
			log.debug("用户注册：{}", user);
			return account;
		} catch (Exception e) {
			log.error("用户注册时出现异常，数据库回滚", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return null;
	}

	@Override
	public PageBean<User> queryUser(int page, int pageSize, User user) {
		log.debug("用户列表查询，page: {}, pageSize: {}, User: {}", page, pageSize, user);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Page<User> users = PageHelper.startPage(page, pageSize).doSelectPage(() -> userMapper.page(user == null ? new User() : user));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(users.getTotal(), users.getResult());
	}

}
