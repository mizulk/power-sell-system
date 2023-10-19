package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface GoodsMapper {

	List<Goods> page(Goods goods);

	@Insert("INSERT INTO powers (`name`,`model`,`capacity`,`stock`,`price`,`discount`,`status`,`create_time`,`update_time`) values(#{name},#{model},#{capacity},#{stock},#{price},#{discount},1,#{createTime},#{updateTime})")
	void putOnShelf(Goods goods);
}
