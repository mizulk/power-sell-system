package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.FavoritePower;

import java.util.List;

public interface FavoritePowerMapper {

	@Select("SELECT * FROM favorites_power;")
	List<FavoritePower> selectAll();

	List<FavoritePower> page(FavoritePower favoritePower);

	@Insert("INSERT INTO favorites(user_id, power_id, create_time) " +
			"VALUES(#{userId}, #{powerId}, #{createTime})")
	void insertNewFavorite(FavoritePower favoritePower);

	void updateFavorite(FavoritePower favoritePower);

	@Select("SELECT * FROM favorites_power WHERE user_id = #{user_id} ;")
	FavoritePower getFavoritePowerByUserid(@Param("user_id") Integer userId);

}
