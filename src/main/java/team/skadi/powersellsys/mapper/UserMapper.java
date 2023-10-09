package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.User;

import java.util.List;

public interface UserMapper {
	@Select("SELECT * FROM users")
	List<User> selectAll();

	List<User> page(String account, String name, String address);
}
