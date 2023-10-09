package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierMapper {
	@Select("SELECT * FROM suppliers;")
	List<Supplier> selectAll();

}
