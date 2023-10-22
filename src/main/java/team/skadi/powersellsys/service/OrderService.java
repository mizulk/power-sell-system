package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Statement;

public interface OrderService extends Service {

	PageBean<Order> queryOrder(int page, int pageSize, Order order);

	void updateOrder(Order order);

	void addNewOrder(Order order);

	PageBean<Statement> queryStatement(int page, int pageSize, Statement statement);
}
