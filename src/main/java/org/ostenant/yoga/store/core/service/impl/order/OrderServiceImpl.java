package org.ostenant.yoga.store.core.service.impl.order;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.cart.BuyerCart;
import org.ostenant.yoga.store.core.bean.cart.BuyerItem;
import org.ostenant.yoga.store.core.bean.order.Detail;
import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.dao.order.OrderMapper;
import org.ostenant.yoga.store.core.query.OrderQuery;
import org.ostenant.yoga.store.core.service.order.DetailService;
import org.ostenant.yoga.store.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Resource
	private DetailService detailService;

	
	public Integer saveOrderInfo(Order order, BuyerCart buyerCart) {

		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		Date createTime = new Date();
		// 订单号
		order.setOid(format.format(createTime));
		// 创建时间
		order.setCreateDate(createTime);
		// 总金额
		order.setPayableFee(buyerCart.getTotalExpenses());
		// 订单金额
		order.setTotalPrice(buyerCart.getTotalProdExpenses());
		// 运费
		BigDecimal deliverFee = new BigDecimal(buyerCart.getTotalDeliverFee());
		order.setDeliverFee(deliverFee);
		// 支付状态
		if (order.getPaymentWay() == 0) {
			order.setIsPaiy(0);
		} else {
			order.setIsPaiy(1);
		}

		// 订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
		order.setState(0);

		// 保存订单信息
		int rowCount = orderMapper.insertSelective(order);

		// 保存订单详情
		LinkedList<BuyerItem> buyerItems = buyerCart.getBuyerItems();
		for (BuyerItem bi : buyerItems) {
			Detail detail = new Detail();
			// 订单ID
			detail.setOrderId(order.getId());
			// 颜色
			detail.setColor(bi.getSkuCell().getColor().getName());
			// 商品ID
			detail.setProductNo(bi.getSkuCell().getProduct().getNo());
			// 商品名
			detail.setProductName(bi.getSkuCell().getProduct().getName());
			// 尺寸
			detail.setSize(bi.getSkuCell().getSize());
			// 购买数量
			detail.setAmount(bi.getAmount());
			// sku价格
			detail.setSkuPrice(bi.getSkuCell().getSkuPrice()); // 保存订单详情
			
			detailService.saveOrderDetail(detail);
		}


		return rowCount;
	}

	@Transactional(readOnly = true)
	public List<Order> getOrdersByIsPaiy(OrderQuery orderQuery) {
		List<Order> orders = orderMapper.getOrdersBy(orderQuery);
		return orders;
	}

	@Transactional(readOnly = true)
	public Order getOrderById(Integer id) {
		Order order = orderMapper.selectByPrimaryKey(id);
		return order;
	}

}
