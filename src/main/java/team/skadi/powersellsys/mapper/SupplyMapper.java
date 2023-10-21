package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import team.skadi.powersellsys.pojo.Supply;

import java.util.List;

public interface SupplyMapper {

	List<Supply> page(Supply supply);

	void updateSupply(Supply supply);

	@Insert("INSERT INTO supplies(supplier_id, power_id, sum, create_time, supply_time)" +
			" VALUES(#{supplierId}, #{powerId}, #{sum}, #{createTime}, #{supplyTime})")
	void insertNewSupply(Supply supply);
}
