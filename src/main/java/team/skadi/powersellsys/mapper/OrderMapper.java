package team.skadi.powersellsys.mapper;

import team.skadi.powersellsys.pojo.Order;

import java.util.List;

public interface OrderMapper {

	List<Order> page(Order order);
}
