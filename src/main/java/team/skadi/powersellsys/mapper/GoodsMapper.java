package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PowerType;

import java.util.List;

/**
 * GoodsMapper包含对powers表，power_types表和视图goods的操作
 */
public interface GoodsMapper {

	List<Goods> page(Goods goods);

	@Update("UPDATE powers SET `status` = 1 WHERE id = #{id}")
	void putOnShelf(Goods goods);

	@Update("UPDATE powers SET `status` = 0 WHERE id = #{id}")
	void putOffShelf(Goods goods);

	@Select("SELECT id, `value` FROM power_types;")
	List<PowerType> getAllPowerType();

	@Insert("INSERT INTO power_types(`value`) values(#{value})")
	void insertNewPowerType(PowerType powerType);

	@Select("SELECT id FROM power_types WHERE `value` = #{value}")
	int findPowerTypeById(PowerType powerType);

	@Select("SELECT COUNT(0) FROM power_types WHERE `value` = #{value}")
	int selectPowerTypeValueCount(PowerType powerType);

	@Delete("DELETE FROM power_types WHERE id = #{id}")
	void deletePowerType(PowerType powerType);

	@Update("UPDATE power_types SET `value` = #{value} WHERE id = #{id}")
	void updatePowerType(PowerType powerType);

	@Insert("INSERT INTO powers(`name`, type, model, capacity, stock, price, discount, `status`, create_time, update_time, `describe`, sum)" +
			" VALUES(#{name}, #{typeId}, #{model}, #{capacity}, #{stock}, #{price}, #{discount}, #{status}, #{createTime}, #{updateTime}, #{describe}, #{sum}) ")
	void insertNewPower(Goods goods);

	void updatePower(Goods goods);

	@Select("SELECT id,`name`,price FROM goods ORDER BY price DESC")
	List<Goods> selectPriceRank();

	@Select("SELECT `name` FROM powers WHERE id = #{id}")
	String findGoodsNameById(@Param("id") Integer id);
}
