package org.ostenant.yoga.store.core.controller.front;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.core.bean.cart.BuyerCart;
import org.ostenant.yoga.store.core.bean.cart.BuyerItem;
import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.bean.product.Color;
import org.ostenant.yoga.store.core.bean.product.Product;
import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.service.order.OrderService;
import org.ostenant.yoga.store.core.service.product.ColorService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.ostenant.yoga.store.core.service.product.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontOrderController extends BaseController {

	@Resource
	private OrderService orderService;
	@Resource
	private SkuCellService skuCellService;
	@Resource
	private ColorService colorService;
	@Resource
	private ProductService productService;
	@Resource
	private TypeService typeService;

	/**
	 * @Title: saveOrder <br>
	 * @Description: 保存订单信息 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/confirm/order.shtml",method = RequestMethod.POST)
	public String comfirmOrder(Order order, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		if(null !=order.getPaymentWay()){
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			BuyerCart buyerCartForCookie = null;
			Cookie[] cookies = request.getCookies();

			if (null != cookies && cookies.length > 0) {
				for (Cookie c : cookies) {
					if (CustomConstant.BUYER_CART_COOKIE.equals(c.getName())) {
						buyerCartForCookie = filledBuyerCartWithJson(om,
								buyerCartForCookie, c);

						break;
					}
				}
			}

			Buyer buyer = (Buyer) sessionProvider.getAttribute(request,
					CustomConstant.BUYER_ID);
			order.setBuyerId(buyer.getUsername());
			// 保存订单信息
			orderService.saveOrderInfo(order, buyerCartForCookie);

			// 清空购物车
			Cookie cookie = new Cookie(CustomConstant.BUYER_CART_COOKIE, null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			return "product/confirmOrder";
		}else {
			return "redirect:/shopping/buyerCart/query.shtml";
		}
		
		
	}

	/**
	 * @Title: filledBuyerCartWithJson <br>
	 * @Description: 填充购物车用json <br>
	 * @Author: madison <br>
	 * @return BuyerCart 返回类型 <br>
	 * @throws
	 */
	protected BuyerCart filledBuyerCartWithJson(ObjectMapper om,
			BuyerCart buyerCartForCookie, Cookie c) {
		// 如果有历史购物车
		String buyerCartJson = c.getValue();
		// -- JSON-->对象
		LinkedList<BuyerItem> buyerItems = null;
		try {
			buyerCartForCookie = om.readValue(buyerCartJson, BuyerCart.class);
			buyerItems = buyerCartForCookie.getBuyerItems();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filledBuyerCartWithObj(buyerCartForCookie, buyerItems);
	}

	/**
	 * @Title: filledBuyerCartWithObj <br>
	 * @Description: 填充购物车用json转化的对象 <br>
	 * @Author: madison <br>
	 * @return BuyerCart 返回类型 <br>
	 * @throws
	 */
	protected BuyerCart filledBuyerCartWithObj(BuyerCart buyerCartForCookie,
			LinkedList<BuyerItem> buyerItems) {
		if (null != buyerItems && buyerItems.size() > 0) {
			/* 总费用 */
			double totalExpenses = 0;
			/* 总邮费 */
			double totalDeliverFee = 0;
			/* 商品总费用 */
			double totalProdExpenses = 0;

			// 填充购物车
			for (BuyerItem bi : buyerItems) {

				SkuCell skuCellForCookie = bi.getSkuCell();

				SkuCell sc = skuCellService
						.getSkuById(skuCellForCookie.getId());
				Product p = productService
						.getProductByIdOnly(sc.getProductId());
				Color cl = colorService.getColorById(sc.getColorId());
				Type t = typeService.getTypeById(p.getTypeId());

				Integer amount = bi.getAmount();
				// -- 邮费
				double perDeliveFee = sc.getDeliveFee();
				totalDeliverFee += perDeliveFee;
				// -- 商品价
				double perSkuPrice = sc.getSkuPrice();
				totalProdExpenses += perSkuPrice * amount;

				sc.setColor(cl);
				sc.setProduct(p);
				sc.setType(t);

				bi.setSkuCell(sc);
			}

			totalExpenses = totalDeliverFee + totalProdExpenses;

			buyerCartForCookie.setTotalProdExpenses(totalProdExpenses);
			buyerCartForCookie.setTotalDeliverFee(totalDeliverFee);

			buyerCartForCookie.setTotalExpenses(totalExpenses);
		}
		return buyerCartForCookie;
	}

}
