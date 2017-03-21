package org.ostenant.yoga.store.core.controller.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.common.page.PageConstant;
import org.ostenant.yoga.store.common.page.ViewPageBean;
import org.ostenant.yoga.store.common.response.ResponseUtils;
import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.bean.product.Feature;
import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.query.BrandQuery;
import org.ostenant.yoga.store.core.query.FeatureQuery;
import org.ostenant.yoga.store.core.query.KeyAndName;
import org.ostenant.yoga.store.core.query.MultiProductQuery;
import org.ostenant.yoga.store.core.query.TypeQuery;
import org.ostenant.yoga.store.core.response.ProductResponse;
import org.ostenant.yoga.store.core.response.SkuCellResponse;
import org.ostenant.yoga.store.core.service.product.BrandService;
import org.ostenant.yoga.store.core.service.product.FeatureService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.ostenant.yoga.store.core.service.product.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

/**
 * 前台 商品控制器
 * 
 * @author madison
 * 
 */
@Controller
public class FrontProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private FeatureService featureService;

	@Autowired
	private SkuCellService skuCellService;
	
	

	private static Logger logger = LoggerFactory
			.getLogger(FrontProductController.class);

	/**
	 * @Title: showProductByCondition <br>
	 * @Description: 商品列表 <br>
	 * @Author: madison <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/display/list.shtml", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String showProductByCondition(MultiProductQuery multiProductQuery,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		
		
		try {
			// 准备已选条件
			Map<String, KeyAndName> queryMap = new LinkedHashMap<String, KeyAndName>();

			/*
			 * 商品列表
			 */
			PageConstant.setDefaultPagination(5);
			PageConstant.setDefaultPageSize(12);
			multiProductQuery
					.setFields("bbs_product.id,no,name,create_time,is_new,is_hot,is_commend,url,price,sales");

			// 排序条件
			String orderByDesc = multiProductQuery.getOrderByDesc();
			if (StringUtils.hasText(orderByDesc)) {
				if ("time".equals(orderByDesc.trim())) {
					multiProductQuery.orderBy("create_time", false);
				} else {
					multiProductQuery.orderBy(orderByDesc, false);
				}
				map.addAttribute("orderByDesc", orderByDesc);
			}

			// 1.商品分页对象
			List<ProductResponse> productList = productService
					.getProductListWithPage(multiProductQuery);
			int totalCount = productService
					.getTotalCountListWithPage(multiProductQuery);

			ViewPageBean<ProductResponse> pageBean = new ViewPageBean<ProductResponse>(
					multiProductQuery.getPageNo(), totalCount, productList);

			// 2.商品视图对象
			HashMap<String, Object> params = Maps.newHashMap();
			// 渲染视图
			String url = CustomConstant.APP_SERVER_HOST
					+ "product/display/list.shtml";

			// -- 1.查询品牌条件
			Integer brandId = multiProductQuery.getBrandId();
			if (null != brandId) {
				// 查询条件显示在页面
				multiProductQuery.setDisplayBrand(false);
				// 页面显示已选条件
				queryMap.put(
						"品牌",
						new KeyAndName("brand", multiProductQuery
								.getBrandName()));

				push(params, "brandId", brandId);

				map.addAttribute("brandId", multiProductQuery.getBrandId());
				map.addAttribute("brandName", multiProductQuery.getBrandName());
				map.addAttribute("displayBrand", false);
			} else {
				// 加载品牌
				BrandQuery brandQuery = new BrandQuery();
				// 设置指定字段
				brandQuery.setFields("id,name");
				// 设置可见
				brandQuery.setIsDisplay(1);
				// 查询条件显示在页面
				multiProductQuery.setDisplayBrand(true);
				// 加载品牌
				List<Brand> brandList = brandService.getBrandList(brandQuery);
				map.addAttribute("brandList", brandList);
				map.addAttribute("displayBrand", true);
			}

			// -- 2.查询类型条件
			Integer typeId = multiProductQuery.getTypeId();
			if (null != typeId) {
				// 查询条件显示在页面
				multiProductQuery.setDisplayType(false);
				// 页面显示已选条件
				queryMap.put("类型",
						new KeyAndName("type", multiProductQuery.getTypeName()));

				push(params, "typeId", typeId);

				map.addAttribute("typeId", multiProductQuery.getTypeId());
				map.addAttribute("typeName", multiProductQuery.getTypeName());
				map.addAttribute("displayType", false);
			} else {
				// 查询条件显示在页面
				multiProductQuery.setDisplayType(true);
				// 加载类型
				TypeQuery typeQuery = new TypeQuery();
				// 设置字段
				typeQuery.setFields("id,name");
				// 查询类型列表
				List<Type> typeList = typeService.getTypeList(typeQuery);
				map.addAttribute("typeList", typeList);
				map.addAttribute("displayType", true);
			}

			// -- 3.查询材质条件
			Integer featureId = multiProductQuery.getFeatureId();
			if (null != featureId) {
				// 查询条件显示在页面
				multiProductQuery.setDisplayFeature(false);
				// 页面显示已选条件
				queryMap.put(
						"材质",
						new KeyAndName("feature", multiProductQuery
								.getFeatureName()));

				push(params, "featureId", featureId);
				push(params, "featureName", multiProductQuery.getFeatureName());

				map.addAttribute("featureId", multiProductQuery.getFeatureId());
				map.addAttribute("featureName",
						multiProductQuery.getFeatureName());
				map.addAttribute("displayFeature", false);
			} else {
				// 查询条件显示在页面
				multiProductQuery.setDisplayFeature(true);
				// 加载材质
				FeatureQuery featureQuery = new FeatureQuery();
				// 设置字段
				featureQuery.setFields("id,name");
				// 查询材质列表
				List<Feature> featureList = featureService
						.getFeatureList2(featureQuery);
				map.addAttribute("featureList", featureList);
				map.addAttribute("displayFeature", true);
			}

			// -- 4.查询价格条件
			Integer minPrice = multiProductQuery.getMinPrice();
			Integer maxPrice = multiProductQuery.getMaxPrice();

			if (null == maxPrice && null != minPrice) {
				// -- 大于》》minPrice
				multiProductQuery.setDisplayPrice(false);
				// 页面显示已选条件
				queryMap.put(
						"价格",
						new KeyAndName("price", multiProductQuery
								.getPriceName()));

				push(params, "minPrice", minPrice);

				map.addAttribute("minPrice", multiProductQuery.getMinPrice());
				map.addAttribute("priceName", multiProductQuery.getPriceName());
				map.addAttribute("displayPrice", false);
			} else if (null != minPrice && null != maxPrice) {
				// minPrice < ... < maxPrice
				// 查询条件显示在页面
				multiProductQuery.setDisplayPrice(false);
				// 页面显示已选条件
				queryMap.put(
						"价格",
						new KeyAndName("price", multiProductQuery
								.getPriceName()));

				push(params, "minPrice", minPrice);
				push(params, "maxPrice", maxPrice);

				map.addAttribute("minPrice", multiProductQuery.getMinPrice());
				map.addAttribute("maxPrice", multiProductQuery.getPriceName());
				map.addAttribute("priceName", multiProductQuery.getPriceName());
				map.addAttribute("displayPrice", false);
			} else {
				// 查询条件显示在页面
				multiProductQuery.setDisplayPrice(true);
				map.addAttribute("displayPrice", true);
			}

			// 分页视图
			List<String> pageView = pageBean.renderPageView(url, params);

			map.addAttribute("pageView", pageView); // 分页视图
			map.addAttribute("pageBean", pageBean); // 分页结果
			map.addAttribute("queryMap", queryMap); // 用于已选条件
			map.addAttribute("basePath", CustomConstant.IMAGE_SERVER_URL); // 图片服务器基本地址

			return "product/product";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-getProductListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "product/product";
		}
	}

	/**
	 * @Title: getCookieInfo <br>
	 * @Description: 获取cookie信息 <br>
	 * @Author: madison <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	public void getCookieInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String[] historyIds = {};
		// -- 查询我的浏览记录
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("productId".equalsIgnoreCase(cookie.getName())) {
				historyIds = cookie.getValue().split(",");
				logger.debug(historyIds.toString());
			}
		}

		// -- 查询历史记录
		if (null != historyIds && historyIds.length > 0) {
			// 转化为list
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < historyIds.length; i++) {
				list.add(historyIds[i]);
			}

			// 默认是按照id排序
			List<ProductResponse> historyList = productService
					.getProductByIds(list);
			// 转化为按照historyId排序
			List<ProductResponse> historyOrderBy = new LinkedList<ProductResponse>();
			for (int i = 0; i < historyIds.length; i++) {
				Integer historyId = Integer.parseInt(historyIds[i]);
				for (int j = 0; j < historyList.size(); j++) {
					Integer tempId = historyList.get(j).getId();
					if (null != historyId && null != tempId
							&& historyId == tempId) {
						historyOrderBy.add(historyList.get(j));
					}
				}
			}
			map.addAttribute("historyOrderBy", historyOrderBy); // 历史记录
		}
	}

	/**
	 * @Title: showProductDetail <br>
	 * @Description: 商品详情jsp页面 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@Deprecated
	@RequestMapping(value = "/product/display/detail.shtml", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String showProductDetail(Integer productId,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		try {

			// 指定商品详情
			ProductResponse productResponse = productService
					.getProductById(productId);

			// 最小销售单元
			List<SkuCellResponse> skuCells = skuCellService
					.getSkuListByPidWithoutPage(productId);

			HashMap<Integer, String> colors = new HashMap<Integer, String>();
			for (SkuCellResponse skuCell : skuCells) {
				if (!colors.containsKey(skuCell.getColorId())) {
					// 如果没有key
					colors.put(skuCell.getColorId(), skuCell.getColor());
				}
			}

			map.addAttribute("product", productResponse);
			map.addAttribute("skuCells", skuCells);
			map.addAttribute("colors", colors);
			map.addAttribute("basePath", CustomConstant.IMAGE_SERVER_URL);
			map.put("path",
					CustomConstant.IMAGE_SERVER_URL + productResponse.getUrl());

			return "product/productDetail";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-showProductDetail:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "product/productDetail";
		}
	}

	/**
	 * @Title: modifyCookie <br>
	 * @Description: 修改cookie <br>
	 * @Author: madison <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	public void modifyCookie(Integer productId, HttpServletRequest request,
			HttpServletResponse response) {
		// 方案一 , 设置Cookie 浏览历史记录
		// -- 1.设置Cookie的响应头
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 设置缓存
		response.setIntHeader("expires", -1);
		response.setHeader("content-cache", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("connection", "Keep-Alive");

		// -- 2.设置Cookie
		Cookie[] cookies = request.getCookies();
		Cookie findCookie = null;
		if (cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("productId".equalsIgnoreCase(cookie.getName())) {
					findCookie = cookie;
				}
			}
		}

		// -->
		// --> 1
		// --> 2,1
		// --> 3,2,1
		// --> 4,3,2
		// --> 3,4,2

		String ids = "";
		if (findCookie == null) {
			ids += productId;
		} else {
			String[] cookieValues = findCookie.getValue().split(",");
			StringBuilder sb = new StringBuilder();
			sb.append(productId + ",");
			// 前面一条 + 后面5条
			for (int i = 0; i < (cookieValues.length < 6 ? cookieValues.length
					: 5); i++) {
				Integer cookieVal = Integer.parseInt(cookieValues[i]);
				if (cookieVal != null && !productId.equals(cookieVal)) {
					sb.append(cookieValues[i] + ",");
				}
			}
			ids = sb.substring(0, sb.length() - 1);
		}

		Cookie cookie = new Cookie("productId", ids);
		// 设置最大时间
		cookie.setMaxAge(3600 * 24 * 30);
		// 设置应用名
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);

	}

	/**
	 * @Title: getHistoryProducts <br>
	 * @Description:获取Session中的历史记录 返回json输出<br>
	 * @Author: madison <br>
	 * @return void 返回类型 <br>
	 */
	@RequestMapping(value = "/product/product/history.shtml", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void getHistoryProducts(Integer productId,
			HttpServletRequest request, HttpServletResponse response) {

		// 指定商品详情
		ProductResponse productResponse = productService
				.getProductById(productId);

		// 商品历史记录
		LinkedList<ProductResponse> histProductList = updateHistoryProducts(
				request, response, productResponse);

		// 返回历史列表数据
		if (null != histProductList && histProductList.size() > 0) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("histProductList", histProductList);
			ResponseUtils.renderJson(response, jsonObject.toString());
		}

	}

	/**
	 * @Title: updateHistoryProducts <br>
	 * @Description: 更新session域范围中的商品历史记录 <br>
	 * @Author: madison <br>
	 * @return LinkedList<ProductResponse> 返回类型 <br>
	 * @throws
	 */
	protected LinkedList<ProductResponse> updateHistoryProducts(
			HttpServletRequest request, HttpServletResponse response,
			ProductResponse productResponse) {
		// 获取历史记录
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// -- 1.设置Cookie的响应头
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// 设置缓存
		response.setIntHeader("expires", -1);
		response.setHeader("content-cache", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("connection", "Keep-Alive");

		// 获取sessionId
		// 如果没有则创建，如果已有则直接获取
		HttpSession session = request.getSession();
		Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
		// 设置最大生命周期
		sessionCookie.setMaxAge(60 * 30);
		// 设置访问路径
		sessionCookie.setPath(request.getContextPath());
		response.addCookie(sessionCookie);

		@SuppressWarnings("unchecked")
		LinkedList<ProductResponse> histProductList = (LinkedList<ProductResponse>) session
				.getAttribute("histProductList");
		if (null == histProductList) {
			// 如果为空
			LinkedList<ProductResponse> newHistProductList = new LinkedList<ProductResponse>();
			// 放在循环队列队首
			newHistProductList.addFirst(productResponse);
			session.setAttribute("histProductList", newHistProductList);
		} else {
			// 检查是否存在该记录
			if (histProductList.size() >= 0) {
				if (!histProductList.contains(productResponse)) {
					// -- 如果没有该记录
					if (histProductList.size() < 6) { // 默认6条浏览记录
						// 放在循环队列队首
						histProductList.addFirst(productResponse);
					} else if (histProductList.size() >= 6) {
						// 移除队尾元素
						histProductList.removeLast();
						// 放在循环队列队首
						histProductList.addFirst(productResponse);
					}
				} // -- 如果有此记录
				else {
					boolean isRemove = histProductList.remove(productResponse);
					if (isRemove) {
						histProductList.addFirst(productResponse);
					}
				}

			}
		}
		return histProductList;
	}

}
