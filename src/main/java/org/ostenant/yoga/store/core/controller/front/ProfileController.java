package org.ostenant.yoga.store.core.controller.front;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.common.response.ResponseUtils;
import org.ostenant.yoga.store.common.utils.DigestUtils;
import org.ostenant.yoga.store.core.bean.country.City;
import org.ostenant.yoga.store.core.bean.country.Province;
import org.ostenant.yoga.store.core.bean.country.Town;
import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.bean.user.Address;
import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.query.OrderQuery;
import org.ostenant.yoga.store.core.service.buyer.AddressService;
import org.ostenant.yoga.store.core.service.buyer.BuyerService;
import org.ostenant.yoga.store.core.service.country.CountryService;
import org.ostenant.yoga.store.core.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class ProfileController extends BaseController {

	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private AddressService addressService;
	@Resource
	private OrderService orderService;

	/**
	 * @Title: login <br>
	 * @Description: GET方法转到登录页面 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/login.shtml", method = RequestMethod.GET)
	public String login() {
		return "buyer/login";
	}

	/**
	 * @Title: login <br>
	 * @Description: POST验证登录信息<br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/login.shtml", method = RequestMethod.POST)
	public String login(String captcha, Buyer buyer, String returnUrl,
			ModelMap map, HttpServletRequest request) {

		// -- 1.验证码不为空
		if (StringUtils.isNotBlank(captcha)) {
			// -- 2.验证码是否正确
			String sessionId = sessionProvider.getSessionId(request);
			Boolean isValidation = imageCaptchaService.validateResponseForID(
					sessionId, captcha); // JSESSIONID 验证码
			if (isValidation) {
				// -- 3.用户名不为空
				if (null != buyer
						&& StringUtils.isNotBlank(buyer.getUsername())) {
					// -- 4.密码不为空
					if (StringUtils.isNotBlank(buyer.getPassword())) {
						// -- 5.用户名验证
						Buyer b = buyerService.getBuyerByUsername(buyer
								.getUsername());
						if (null != b) {
							// -- 6.密码验证
							String md5HexPwd = DigestUtils
									.md5WithHexEncoder(buyer.getPassword());
							if (b.getPassword().equals(md5HexPwd)) {
								// 把当前用户登录信息存入session
								sessionProvider.setAttribute(request,
										CustomConstant.BUYER_ID, b);
								if (StringUtils.isNotBlank(returnUrl)) {
									// 重定向到登录前访问的页面
									return "redirect:" + returnUrl;
								} else {
									// 重定向到个人主页
									return "redirect:buyer/index.shtml";
								}
							} else {
								map.addAttribute("error", "密码输入错误！");
							}
						} else {
							map.addAttribute("error", "用户名不存在！");
						}
					} else {
						map.addAttribute("error", "请输入密码！");
					}
				} else {
					map.addAttribute("error", "请输入用户名！");
				}
			} else {
				map.addAttribute("error", "验证码不正确！");
			}
		} else {
			map.addAttribute("error", "请输入验证码！");
		}

		return "buyer/login";
	}

	/**
	 * @Title: logout <br>
	 * @Description: 注销用户<br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/shopping/logout.shtml", method = RequestMethod.GET)
	public String logout(String returnUrl, HttpServletRequest request,
			HttpServletResponse response) {
		sessionProvider.logout(request, response);
		if (StringUtils.isNotBlank(returnUrl)) {
			return "redirect:" + returnUrl;
		} else {
			return "redirect:/product/display/list.shtml";
		}
	}

	/**
	 * @Title: index <br>
	 * @Description: 个人主页 <br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/index.shtml", method = RequestMethod.GET)
	public String index() {
		return "buyer/index";
	}

	/**
	 * @Title: queryProfile <br>
	 * @Description: 查看个人资料<br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/query/profile.shtml", method = RequestMethod.GET)
	public String queryProfile(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {

		// -- 获取 省份 - 城市 - 区县
		// 1.省份
		List<Province> provinces = countryService.getAllProvinceList();

		Buyer buyer = (Buyer) sessionProvider.getAttribute(request,
				CustomConstant.BUYER_ID);
		// 2.城市
		List<City> cities = countryService
				.getCityListByPid(buyer.getProvince());
		// 3.区县镇
		List<Town> towns = countryService.getTownListByCid(buyer.getCity());

		map.addAttribute("provinces", provinces);
		map.addAttribute("cities", cities);
		map.addAttribute("towns", towns);

		return "buyer/profile";
	}

	/**
	 * @Title: ajaxPostProvince <br>
	 * @Description: ajax根据省份id查询城市列表<br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/query/profile/province.shtml", method = RequestMethod.POST)
	public void ajaxPostProvince(String provinceCode,
			HttpServletRequest request, HttpServletResponse response) {

		// 2.城市
		List<City> cities = countryService.getCityListByPid(provinceCode);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cities", cities);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * @Title: ajaxPostCity <br>
	 * @Description: ajax根据城市id查询城镇列表 <br>
	 * @Author: madison <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/query/profile/city.shtml", method = RequestMethod.POST)
	public void ajaxPostCity(String cityCode, HttpServletRequest request,
			HttpServletResponse response) {

		// 2.城市
		List<Town> towns = countryService.getTownListByCid(cityCode);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("towns", towns);
		ResponseUtils.renderJson(response, jsonObject.toString());
	}

	/**
	 * @Title: saveProfile <br>
	 * @Description: 保存个人资料<br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/save/profile.shtml", method = RequestMethod.POST)
	public String saveProfile(Buyer buyer, HttpServletRequest request,
			HttpServletResponse response) {
		buyerService.saveBuyerInfo(buyer);
		// 更新session
		Buyer b = buyerService.getBuyerByUsername(buyer.getUsername());
		sessionProvider.setAttribute(request, CustomConstant.BUYER_ID, b);
		return "redirect:/buyer/query/profile.shtml";
	}

	/**
	 * @Title: setDefaultAddress <br>
	 * @Description: 设置默认地址 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/modify/default/address.shtml", method = RequestMethod.GET)
	public String setDefaultAddress(Integer addrId, HttpServletRequest request,
			HttpServletResponse response) {

		addressService.modifyDefaultAddress(addrId);
		return "redirect:/buyer/toAddress.shtml";
	}

	/**
	 * @Title: deleteAddress <br>
	 * @Description: 删除收货地址 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/delete/address.shtml", method = RequestMethod.GET)
	public String deleteAddress(Integer addrId, HttpServletRequest request,
			HttpServletResponse response) {

		addressService.deleteAddressById(addrId);
		return "redirect:/buyer/toAddress.shtml";
	}

	/**
	 * @Title: changePassword <br>
	 * @Description: 修改密码<br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/change/password.shtml", method = RequestMethod.GET)
	public String changePassword() {

		return "buyer/change_password";
	}

	/**
	 * @Title: myOrders <br>
	 * @Description: 我的订单 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/orders.shtml", method = RequestMethod.GET)
	public String myOrders(ModelMap map) {
		// -- 查询订单
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.orderBy("create_date", true);
		
		List<Order> orders = orderService.getOrdersByIsPaiy(orderQuery);
		map.put("basePath", CustomConstant.IMAGE_SERVER_URL);
		map.addAttribute("orders", orders);
		return "buyer/orders";
	}

	/**
	 * @Title: saveNewAddress <br>
	 * @Description: 保存新地址 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/save/address.shtml", method = RequestMethod.POST)
	public String saveNewAddress(String province, String city, String town,
			Address address, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		Buyer buyer = (Buyer) sessionProvider.getAttribute(request,
				CustomConstant.BUYER_ID);

		// -- 查询保存个数
		Integer saveCount = addressService.getOwnAddrCount(buyer.getUsername());

		if (saveCount >= CustomConstant.BUYER_SAVE_ADDR_COUNT) {
			map.addAttribute("message", "至多添加5个收货地址!");
			return "redirect:/buyer/toAddress.shtml";
		}

		Province p = countryService.getProvinceNameById(province);
		City c = countryService.getCityNameById(city);
		Town t = countryService.getTownNameById(town);

		address.setBuyerId(buyer.getUsername());
		String ct = p.getName() + c.getName() + t.getName();
		address.setCity(ct);

		if (null == address.getIsDef()) {
			address.setIsDef(0);
		}

		addressService.saveOwnAddr(address);
		if (null != address.getIsDef() && address.getIsDef() == 1) {
			// 设为默认收货地址
			addressService.modifyDefaultAddress(address.getId());
		}

		return "redirect:/buyer/toAddress.shtml";
	}

	/**
	 * @Title: deliverAddress <br>
	 * @Description: 转到收货地址 <br>
	 * @Author: madison <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/buyer/toAddress.shtml", method = RequestMethod.GET)
	public String toDeliverAddress(ModelMap map, HttpServletRequest request) {

		// -- 获取 省份 - 城市 - 区县,
		// 1.省份
		List<Province> provinces = countryService.getAllProvinceList();

		Buyer buyer = (Buyer) sessionProvider.getAttribute(request,
				CustomConstant.BUYER_ID);
		// 2.城市
		List<City> cities = countryService
				.getCityListByPid(buyer.getProvince());
		// 3.区县镇
		List<Town> towns = countryService.getTownListByCid(buyer.getCity());

		// -- 准备收货信息列表
		List<Address> addresses = addressService.getOwnAddrList(buyer);

		map.addAttribute("addresses", addresses);
		map.addAttribute("provinces", provinces);
		map.addAttribute("cities", cities);
		map.addAttribute("towns", towns);

		return "buyer/deliver_address";
	}

}
