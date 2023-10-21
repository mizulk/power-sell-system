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

import java.time.LocalDateTime;

@Slf4j
@SuppressWarnings("unused")
public class OrderServiceImpl implements OrderService {

	@Override
	public PageBean<Order> queryOrder(int page, int pageSize, Order order) {
		log.info("分页查询订单，page：{}，pageSize：{}，order：{}", page, pageSize, order);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
		Page<Order> p = PageHelper.startPage(page, pageSize).doSelectPage(() -> orderMapper.page(order == null ? new Order() : order));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(p.getTotal(), p.getResult());
	}

	@Override
	public void updateOrder(Order order) {
		log.info("修改订单：{}", order);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			orderMapper.updateOrder(order);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("更新订单时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void addNewOrder(Order order) {
		log.info("添加订单：{}", order);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
			order.setCreateTime(LocalDateTime.now());
			orderMapper.insertNewOrder(order);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("创建新订单时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
}
