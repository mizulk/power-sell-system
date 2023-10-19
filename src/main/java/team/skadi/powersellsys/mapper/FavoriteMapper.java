package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.Favorite;

import java.util.List;

public interface FavoriteMapper {

	@Select("SELECT * FROM favorites;")
	List<Favorite> selectall();

	List<Favorite> page(Favorite favorite);

	@Select("SELECT * FROM favorites WHERE user_id = #{user_id} ;")
	Favorite getFavoriteByUserid(@Param("user_id") Integer userId);

	@Select("SELECT * FROM favorites WHERE power_id = #{power_id} ;")
	Favorite getFavoriteByPowerid(@Param("power_id") Integer powerId);
}
