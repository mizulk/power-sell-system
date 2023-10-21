package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;

public interface OrderService extends Service {

	PageBean<Order> queryOrder(int page, int pageSize, Order order);

	void updateOrder(Order order);

	void addNewOrder(Order order);
}
