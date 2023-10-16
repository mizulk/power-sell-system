package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.User;

import java.util.List;

public interface UserMapper {
	@Select("SELECT * FROM users")
	List<User> selectAll();

	List<User> page(User user);

	void updateUser(User user);

	@Select("SELECT id FROM users;")
	int getEmptyAccountId();

	@Insert("INSERT INTO users(`account`, `password`, `name`, sex, age, zip_code, tel, address, create_time, update_time, login_time) " +
			"VALUES(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)")
	void insertEmptyAccount();

	@Select("SELECT id, `account`, `password`, `name`, sex, age, zip_code, tel, address, create_time, update_time, login_time " +
			"FROM users WHERE `account` = #{account} AND `password` = #{password}")
	User findUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

}
