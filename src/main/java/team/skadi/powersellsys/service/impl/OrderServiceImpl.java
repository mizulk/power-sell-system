package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.OrderMapper;
import team.skadi.powersellsys.pojo.Order;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

@Slf4j
@SuppressWarnings("unused")
public class OrderServiceImpl implements OrderService {
	@Override
	public PageBean<Order> queryOrder(int page, int pageSize, Order order) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		Page<Order> p = PageHelper.startPage(page, pageSize).doSelectPage(() -> orderMapper.page(order == null ? new Order() : order));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(p.getTotal(), p.getResult());
	}
}
