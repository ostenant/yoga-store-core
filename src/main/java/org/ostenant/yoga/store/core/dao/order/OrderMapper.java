package org.ostenant.yoga.store.core.dao.order;

import java.util.List;

import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.query.OrderQuery;

public interface OrderMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<Order> getOrdersBy(OrderQuery orderQuery);
}