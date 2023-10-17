package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface GoodsMapper {
//	@Insert(Insert into )
	List<Goods> page();

	@Insert("INSERT INTO goods(`name`,`type`,`capacity`,`stock`,`status`) values(#{name},#{type},#{capacity},#{stock},'上架')")
	void putOnShelf(Goods goods);
}
