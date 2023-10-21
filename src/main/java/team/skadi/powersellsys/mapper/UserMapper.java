package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.*;
import team.skadi.powersellsys.pojo.User;

import java.util.List;

public interface UserMapper {
	@Select("SELECT * FROM users")
	List<User> selectAll();

	List<User> page(User user);

	void updateUser(User user);

	@Select("SELECT id FROM users WHERE `account` IS NULL;")
	int getEmptyAccountId();

	@Insert("INSERT INTO users(`account`, `password`, `name`, sex, age, zip_code, tel, address, balance, create_time, update_time, login_time) " +
			"VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)")
	void insertEmptyAccount();

	@Select("SELECT id, `account`, `password`, `name`, sex, age, zip_code, tel, address, balance, create_time, update_time, login_time " +
			"FROM users WHERE `account` = #{account} AND `password` = #{password}")
	User findUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

	@Select("SELECT `account`, `password`, `name`, sex, age, zip_code, tel, address, balance, create_time, update_time, login_time" +
			" FROM users WHERE `account` = #{account}")
	User findUserByAccount(@Param("account") String account);

	@Select("SELECT `name` FROM users WHERE id = #{userId}")
	String findUserNameById(@Param("userId") Integer userId);

	@Delete("DELETE FROM users WHERE `account` = #{account}")
	void delUserByAccount(@Param("account") String account);

	@Update("UPDATE users SET login_time = NOW() WHERE account = #{account}")
	void updateLoginTimeByUserId(@Param("account") String account);
}
