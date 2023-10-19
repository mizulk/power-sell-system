package team.skadi.powersellsys.mapper;

import team.skadi.powersellsys.pojo.Supply;

import java.util.List;

public interface SupplyMapper {

	List<Supply> page(Supply supply);
}
