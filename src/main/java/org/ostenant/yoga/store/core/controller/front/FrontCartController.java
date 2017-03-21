package org.ostenant.yoga.store.core.controller.front;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.json.JSONObject;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.common.response.ResponseUtils;
import org.ostenant.yoga.store.core.bean.cart.BuyerCart;
import org.ostenant.yoga.store.core.bean.cart.BuyerItem;
import org.ostenant.yoga.store.core.bean.product.Color;
import org.ostenant.yoga.store.core.bean.product.Product;
import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.bean.user.Address;
import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.service.buyer.AddressService;
import org.ostenant.yoga.store.core.service.product.ColorService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.ostenant.yoga.store.core.service.product.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 项目名称：yoga-store <br>
 * 类名称：FrontOrderController <br>
 * 类描述：订单控制器 <br>
 * 创建人：madison <br>
 * 创建时间：2015-11-17 上午9:57:42 <br>
 * 修改人：madison <br>
 * 修改时间：2015-11-17 上午9:57:42 <br>
 * 修改备注： <br>
 * 
 * @version
 */
@Controller
public class FrontCartController extends BaseController {

	@Autowired
	private SkuCellService skuCellService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private ProductService productService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private AddressService addressService;

	/**
	 * @Title: queryBuyerCart <br>
	 * @Description: 查询购物车 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/buyerCart/query.shtml", method = RequestMethod.GET)
	public String queryBuyerCart(HttpServletRequest request, ModelMap map) {

		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Inclusion.NON_NULL);

		BuyerCart buyerCartForCookie = null;
		Cookie[] cookies = request.getCookies();

		if (null != cookies && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (CustomConstant.BUYER_CART_COOKIE.equals(c.getName())) {
					buyerCartForCookie = filledBuyerCartWithJson(om,
							buyerCartForCookie, c);

					map.addAttribute("buyerCart", buyerCartForCookie);
					map.addAttribute("basePath",
							CustomConstant.IMAGE_SERVER_URL);
					break;
				}
			}
		}

		return "product/cart";
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
		if (null != buyerCartForCookie && null != buyerItems
				&& buyerItems.size() > 0) {
			/* 总费用 */
			double totalExpenses = 0;
			/* 总邮费 */
			double totalDeliverFee = 0;
			/* 商品总费用 */
			double totalProdExpenses = 0;

			// 填充购物车
			for (BuyerItem bi : buyerItems) {

				SkuCell skuCellForCookie = bi.getSkuCell();

				// -- 2.准备前台数据
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

	/**
	 * @Title: buyerCart <br>
	 * @Description: 加入购物车 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/buyerCart/add.shtml", method = RequestMethod.GET)
	@ResponseBody
	public void addBuyerCart(Integer skuId, Integer amount, Integer buyLimit,
			Integer productId, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Assert.notNull(skuId, "skuId不能为空!");
			Assert.notNull(buyLimit, "buyLimit不能为空!");
			Assert.notNull(amount, "amount不能为空!");
			Assert.notNull(productId, "productId不能为空!");
			/* 判定amount是否合法 */
			Assert.isTrue(amount <= buyLimit);

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			// 响应cookie的购物车
			BuyerCart buyerCartForCookie = null;
			// -- 1.获取cookie请求头
			Cookie[] cookies = request.getCookies();

			buyerCartForCookie = getBuyerCartForCookie(om, buyerCartForCookie,
					cookies);

			// -- 2.对象 -->JSON
			// 更新cookie
			if (null == buyerCartForCookie) {
				buyerCartForCookie = new BuyerCart();
			}

			boolean ifAddSameSkuCookie = ifAddSameSku(skuId, amount,
					buyerCartForCookie, productId, response);

			if (!ifAddSameSkuCookie) {
				BuyerItem b = new BuyerItem();
				b.setAmount(amount);
				b.setBuyLimit(buyLimit);
				SkuCell sCell = new SkuCell();
				sCell.setId(skuId);
				b.setSkuCell(sCell);

				buyerCartForCookie.setLastModifieDate(new Date());
				buyerCartForCookie.setProductId(productId);
				buyerCartForCookie.addBuyerItem(b);

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("info", "成功添加 " + amount + "件商品!");
				jsonObject.put("status", 1);
				ResponseUtils.renderJson(response, jsonObject.toString());
			}

			writeCookie(response, om, buyerCartForCookie);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-FrontOrderController-buyerCart: ",
					e.getMessage());
		}

	}

	protected void writeCookie(HttpServletResponse response, ObjectMapper om,
			BuyerCart buyerCartForCookie) throws IOException,
			JsonGenerationException, JsonMappingException {
		StringWriter w = new StringWriter();
		om.writeValue(w, buyerCartForCookie);

		// 设置Cookie响应
		Cookie buyerCartCookie = new Cookie(CustomConstant.BUYER_CART_COOKIE,
				w.toString());
		// 设置cookie生命周期
		buyerCartCookie.setPath("/");
		buyerCartCookie.setMaxAge(3600 * 24 * 7);
		response.addCookie(buyerCartCookie);
	}

	@RequestMapping(value = "/shopping/buyerCart/deleteOne.shtml", method = RequestMethod.GET)
	public String deleteBySkuId(Integer skuId, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {

			Assert.notNull(skuId, "skuId不能为空!");

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			// 响应cookie的购物车
			BuyerCart buyerCartForCookie = null;
			// -- 1.获取cookie请求头
			Cookie[] cookies = request.getCookies();

			buyerCartForCookie = getBuyerCartForCookie(om, buyerCartForCookie,
					cookies);

			if (null != buyerCartForCookie
					&& buyerCartForCookie.getBuyerItems().size() > 0) {

				for (BuyerItem bi : buyerCartForCookie.getBuyerItems()) {
					if (bi.getSkuCell().getId().equals(skuId)) {
						// 移除商品
						buyerCartForCookie.getBuyerItems().remove(bi);
					}
				}
			}

			if (buyerCartForCookie.getBuyerItems().size() <= 0) {
				// 清空购物车 销毁cookie
				destroyCookie(response);
			} else {
				// 输出cookie
				writeCookie(response, om, buyerCartForCookie);
			}

			// 输出cookie
			writeCookie(response, om, buyerCartForCookie);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-FrontOrderController-deleteBySkuId: ",
					e.getMessage());
		}

		return "redirect:/shopping/buyerCart/query.shtml";
	}

	/**
	 * @Title: addAmount <br>
	 * @Description: 添加指定商品数量 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/buyerCart/addAmount.shtml", method = RequestMethod.GET)
	public String addAmount(Integer skuId, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {

			Assert.notNull(skuId, "skuId不能为空!");

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			// 响应cookie的购物车
			BuyerCart buyerCartForCookie = null;
			// -- 1.获取cookie请求头
			Cookie[] cookies = request.getCookies();

			buyerCartForCookie = getBuyerCartForCookie(om, buyerCartForCookie,
					cookies);

			if (null != buyerCartForCookie
					&& buyerCartForCookie.getBuyerItems().size() > 0) {

				for (BuyerItem bi : buyerCartForCookie.getBuyerItems()) {
					if (bi.getSkuCell().getId().equals(skuId)) {
						Integer buyLimit = bi.getBuyLimit();
						Integer amount = bi.getAmount();
						if (amount < buyLimit) {
							bi.setAmount(++amount);
						} else {
							map.addAttribute("message", "购买数量超出限制！");
						}
					}
				}
			}

			writeCookie(response, om, buyerCartForCookie);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-FrontOrderController-addAmount: ",
					e.getMessage());
		}
		return "redirect:/shopping/buyerCart/query.shtml";
	}

	/**
	 * @Title: lessenAmount <br>
	 * @Description: 减少指定商品数量<br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/buyerCart/lessenAmount.shtml", method = RequestMethod.GET)
	public String lessenAmount(Integer skuId, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {

			Assert.notNull(skuId, "skuId不能为空!");

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			// 响应cookie的购物车
			BuyerCart buyerCartForCookie = null;
			// -- 1.获取cookie请求头
			Cookie[] cookies = request.getCookies();

			buyerCartForCookie = getBuyerCartForCookie(om, buyerCartForCookie,
					cookies);

			if (null != buyerCartForCookie
					&& buyerCartForCookie.getBuyerItems().size() > 0) {

				for (BuyerItem bi : buyerCartForCookie.getBuyerItems()) {
					if (bi.getSkuCell().getId().equals(skuId)) {
						Integer amount = bi.getAmount();
						if (amount > 1) {
							bi.setAmount(--amount);
						} else {
							// -- 等于1移除商品
							buyerCartForCookie.getBuyerItems().remove(bi);
						}
					}
				}
			}

			if (buyerCartForCookie.getBuyerItems().size() <= 0) {
				// 清空购物车 销毁cookie
				destroyCookie(response);
			} else {
				// 输出cookie
				writeCookie(response, om, buyerCartForCookie);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-FrontOrderController-lessenAmount: ",
					e.getMessage());
		}
		return "redirect:/shopping/buyerCart/query.shtml";
	}

	protected BuyerCart getBuyerCartForCookie(ObjectMapper om,
			BuyerCart buyerCartForCookie, Cookie[] cookies) throws IOException,
			JsonParseException, JsonMappingException {
		if (null != cookies && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (CustomConstant.BUYER_CART_COOKIE.equals(c.getName())) {
					// 如果有历史购物车
					String buyerCartJson = c.getValue();
					// -- JSON-->对象
					buyerCartForCookie = om.readValue(buyerCartJson,
							BuyerCart.class);

					break;
				}
			}
		}
		return buyerCartForCookie;
	}

	/**
	 * @Title: clearBuyerCart <br>
	 * @Description: 清空购物车 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/buyerCart/clear.shtml", method = RequestMethod.GET)
	public String clearBuyerCart(HttpServletRequest request,
			HttpServletResponse response) {

		destroyCookie(response);

		return "redirect:/shopping/buyerCart/query.shtml";
	}

	/**
	 * 需要登录权限
	 * 
	 * @Title: settleAccount <br>
	 * @Description: 结算购物车 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/buyerCart/settle.shtml", method = RequestMethod.GET)
	public String settleAccount(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {

			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(Inclusion.NON_NULL);

			// 响应cookie的购物车
			BuyerCart buyerCartForCookie = null;
			// -- 1.获取cookie请求头
			Cookie[] cookies = request.getCookies();

			buyerCartForCookie = getBuyerCartForCookie(om, buyerCartForCookie,
					cookies);

			if (null != buyerCartForCookie) {
				LinkedList<BuyerItem> buyerItems = buyerCartForCookie
						.getBuyerItems();
				// -- 1.购物车不能为空
				if (null != buyerItems && buyerItems.size() > 0) {
					Integer prevSize = buyerItems.size();
					// -- 2.购买数量是否小于库存
					for (BuyerItem bi : buyerItems) {
						SkuCell skuCell = bi.getSkuCell();
						// 是否超过库存
						boolean isOverStock = bi.isOverStock(skuCellService
								.getSkuById(skuCell.getId()));
						if (isOverStock) {
							// -- >> 清理购物车 刷新cookie
							buyerItems.remove(bi);
						}
					}
					Integer postSize = buyerItems.size();
					if (postSize <= 0) { // 全部超过库存
						// 销毁购物车cookie
						destroyCookie(response);

					} else if (postSize < prevSize) { // 有超过库存
						// 重写cookie
						writeCookie(response, om, buyerCartForCookie);
					} else { // 没有超过库存
						// -- >> 填写核对订单信息

						// -- 1.填充购物车
						BuyerCart buyerCart = filledBuyerCartWithObj(
								buyerCartForCookie, buyerItems);

						// -- 2.准备送货地址信息
						Buyer buyer = (Buyer) sessionProvider.getAttribute(
								request, CustomConstant.BUYER_ID);
						Address address = addressService
								.getDefaultAddress(buyer.getUsername());

						map.addAttribute("address", address);
						map.addAttribute("buyerCart", buyerCart);
						map.addAttribute("basePath",
								CustomConstant.IMAGE_SERVER_URL);

						return "product/productOrder";
					}

					return "redirect:/shopping/buyerCart/query.shtml";

				} else {
					return "redirect:/shopping/buyerCart/query.shtml";
				}
			} else {
				return "redirect:/shopping/buyerCart/query.shtml";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-FrontOrderController-settleAccount: ",
					e.getMessage());
			return "redirect:/shopping/buyerCart/query.shtml";
		}

	}

	protected void destroyCookie(HttpServletResponse response) {
		// 销毁覆盖
		Cookie cookie = new Cookie(CustomConstant.BUYER_CART_COOKIE, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * @Title: ifAddSameSku <br>
	 * @Description: 判断是否是相同sku 如果是新增amount 如果不是不处理<br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return boolean 返回类型 <br>
	 * @throws
	 */
	protected boolean ifAddSameSku(Integer skuId, Integer amount,
			BuyerCart cart, Integer productId, HttpServletResponse response) {
		boolean isSame = false;
		// 如果已经有相同的sku
		if (cart.getBuyerItems().size() > 0) {
			for (BuyerItem bi : cart.getBuyerItems()) {
				if (skuId.equals(bi.getSkuCell().getId())) {
					JSONObject jsonObject = new JSONObject();
					// 更新sku购买数量
					Integer assginAmount = bi.getAmount() + amount;
					if (assginAmount > bi.getBuyLimit()) {
						// 默认显示最大数量
						Integer rare = bi.getBuyLimit() - bi.getAmount();
						jsonObject.put("info", "添加失败，商品数目超出限购数，你还可以添加  " + rare
								+ " 件商品!");
						jsonObject.put("status", 0);
					} else {
						jsonObject.put("info", "成功添加 " + amount + "件商品!");
						jsonObject.put("status", 1);
						bi.setAmount(assginAmount);
					}
					ResponseUtils.renderJson(response, jsonObject.toString());
					cart.setLastModifieDate(new Date());
					cart.setProductId(productId);
					isSame = true;
					break;
				}
			}
		}
		return isSame;
	}

}
