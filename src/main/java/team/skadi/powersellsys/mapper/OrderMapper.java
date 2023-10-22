package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.Statement;

import java.util.List;

public interface OrderMapper {

	List<Order> page(Order order);

	void updateOrder(Order order);

	@Insert("INSERT INTO orders(user_id, power_id, sum, amount, create_time, `count`) " +
			"VALUES(#{userId}, #{powerId}, #{sum}, #{amount}, #{createTime}, #{count})")
	void insertNewOrder(Order order);

	List<Statement> pageStatement(Statement statement);
}
