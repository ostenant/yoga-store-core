package org.ostenant.yoga.store.core.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.core.bean.order.Detail;
import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.bean.user.Address;
import org.ostenant.yoga.store.core.query.OrderQuery;
import org.ostenant.yoga.store.core.service.buyer.AddressService;
import org.ostenant.yoga.store.core.service.order.DetailService;
import org.ostenant.yoga.store.core.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 项目名称：yoga-store <br>
 * 类名称：OrderController <br>
 * 类描述： 订单列表和查看订单 <br>
 * 创建人：madison <br>
 * 创建时间：2015-11-22 上午10:22:48 <br>
 * 修改人：madison <br>
 * 修改时间：2015-11-22 上午10:22:48 <br>
 * 修改备注： <br>
 * 
 * @version
 */
@Controller
@RequestMapping(value = "/admin")
public class OrderController extends BaseController {

	@Resource
	private OrderService orderService;
	@Resource
	private DetailService detailService;
	@Resource
	private AddressService addressService;
	
	/**
	 * @Title: listOrders <br>
	 * @Description: 支付状态 :0到付1待付款,2已付款,3待退款,4退款成功,5退款失败 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/order/list.do", method = RequestMethod.GET)
	public String listOrders(Integer isPaiy, ModelMap map) {
		// -- 查询订单
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.orderBy("create_date", true)//
				.getQueryBean()//
				.setIsPaiy(isPaiy);
		List<Order> orders = orderService.getOrdersByIsPaiy(orderQuery);
		map.addAttribute("orders", orders);
		return "order/list";
	}
	
	
	/**
	 * @Title: viewOrderDetail <br>
	 * @Description: 查看商品详情 <br>
	 * @Author: madison <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/order/view/detail.do", method = RequestMethod.GET)
	public String viewOrderDetail(Integer id, ModelMap map) {
		
		Order order = orderService.getOrderById(id);
		List<Detail> details = detailService.getDetailsByOid(id);
		
		Address addr = addressService.getAddressById(order.getAddrId());
		
		map.addAttribute("addr", addr);
		map.addAttribute("details", details);
		map.addAttribute("order", order);
		return "order/view";
	}

}
