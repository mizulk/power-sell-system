package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.FavoritePower;

import java.util.List;

public interface FavoritePowerMapper {

	@Select("SELECT * FROM favorites_power;")
	List<FavoritePower> selectall();

	List<FavoritePower> page(FavoritePower favoritePower);

	@Select("SELECT * FROM favorites_power WHERE user_id = #{user_id} ;")
	FavoritePower getFavoritePowerByUserid(@Param("user_id") Integer userId);

}
