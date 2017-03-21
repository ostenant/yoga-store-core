package org.ostenant.yoga.store.core.service.order;

import java.util.List;

import org.ostenant.yoga.store.core.bean.cart.BuyerCart;
import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.query.OrderQuery;

public interface OrderService {

	/**
	 * @Title: saveOrderInfo <br>
	 * @Description: 保存订单信息 <br>
	 * @Author: madison <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	Integer saveOrderInfo(Order order, BuyerCart buyerCart);

	/**
	 * @Title: getOrdersByIsPaiy <br>
	 * @Description: 查询订单 <br>
	 * @Author: madison <br>
	 * @return List<Order>    返回类型   <br>
	 * @throws
	 */
	List<Order> getOrdersByIsPaiy(OrderQuery orderQuery);

	/**
	 * @Title: getOrderById <br>
	 * @Description: 查询订单<br>
	 * @Author: madison <br>
	 * @return Order    返回类型   <br>
	 * @throws
	 */
	Order getOrderById(Integer id);

}
