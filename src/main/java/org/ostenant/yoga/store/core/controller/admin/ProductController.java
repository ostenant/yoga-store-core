package org.ostenant.yoga.store.core.controller.admin;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.common.page.PageConstant;
import org.ostenant.yoga.store.common.page.ViewPageBean;
import org.ostenant.yoga.store.common.response.ResponseUtils;
import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.bean.product.Color;
import org.ostenant.yoga.store.core.bean.product.Feature;
import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.query.ProductQuery;
import org.ostenant.yoga.store.core.query.ProductSaveData;
import org.ostenant.yoga.store.core.query.base.PageQuery;
import org.ostenant.yoga.store.core.response.ProductResponse;
import org.ostenant.yoga.store.core.response.SkuCellResponse;
import org.ostenant.yoga.store.core.service.product.BrandService;
import org.ostenant.yoga.store.core.service.product.ColorService;
import org.ostenant.yoga.store.core.service.product.FeatureService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.ostenant.yoga.store.core.service.product.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

/**
 * 后台管理 商品后台控制器
 * 
 * @author madison
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private ColorService colorService;

	@Autowired
	private FeatureService featureService;

	@Autowired
	private SkuCellService skuCellService;

	/**
	 * @Title: getProductListByQuery <br>
	 * @Description: 转到商品列表页面 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/list.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String getProductListByQuery(ProductQuery productQuery,
			BindingResult errors, ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "product/list";
			}

			// -- 1. 获取分页对象
			PageConstant.setDefaultPageSize(7);
			List<ProductResponse> pagelist = productService
					.getProductListByQuery(productQuery);
			int totalCount = productService.getTotalCountByQuery(productQuery);

			ViewPageBean<ProductResponse> pageBean = new ViewPageBean<ProductResponse>(
					productQuery.getPageNo(), totalCount, pagelist);

			// -- 2. 渲染分页试图

			HashMap<String, Object> params = Maps.newHashMap();

			push(params, "name", productQuery.getName()); // -- 商品名称
			push(params, "isShow", productQuery.getIsShow()); // -- 是否显示
			push(params, "brandId", productQuery.getBrandId()); // -- 是否显示

			String url = request.getContextPath()
					+ "/admin/product/product/list.do";

			List<String> pageView = pageBean.renderPageView(url, params);

			map.addAttribute("pageBean", pageBean);
			map.addAttribute("pageView", pageView);
			map.addAttribute("params", params);
			map.put("basePath", CustomConstant.IMAGE_SERVER_URL);

			return "product/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-getProductListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "product/list";
		}
	}

	/**
	 * @Title: loadBrandInfo <br>
	 * @Description: 异步加载品牌数据 <br>
	 * @Author: madison <br>
	 * @param @param response 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/load/brand.do", method = RequestMethod.GET)
	public void loadBrandInfo(HttpServletResponse response) {
		try {
			// -- 获取品牌列表
			List<Brand> brandList = brandService.getBrandNameList();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("key", brandList);
			// -- 响应数据
			ResponseUtils.renderJson(response, jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-loadBrandInfo:"
					+ e.getMessage());
		}
	}

	/**
	 * @Title: loadTypeInfo <br>
	 * @Description: 加载商品类型信息 <br>
	 * @Author: madison <br>
	 * @param @param response 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/load/type.do", method = RequestMethod.GET)
	public void loadTypeInfo(HttpServletResponse response) {
		try {
			// -- 获取商品类型列表
			List<Type> typeList = typeService.getTypeNameList();

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("typeList", typeList);
			// -- 响应数据
			ResponseUtils.renderJson(response, jsonObject.toString());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-loadTypeInfo:"
					+ e.getMessage());
		}

	}

	/**
	 * @Title: toAdd <br>
	 * @Description: 转到添加页面 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/toAdd.do", method = RequestMethod.GET)
	public String toAdd(ModelMap map) {
		try {
			// -- 获取颜色集列表
			List<Color> colorList = colorService.getColorList();

			// -- 获取属性集合列表
			List<Feature> featureList = featureService.getFeatureList();

			map.put("colorList", colorList);
			map.put("featureList", featureList);

			return "product/add";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-toAdd:" + e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "product/add";
		}
	}

	/**
	 * @Title: add <br>
	 * @Description: 添加商品信息 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/addProduct.do", method = RequestMethod.POST)
	public String add(ProductSaveData productSaveData, BindingResult errors,
			ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "redirect:/admin/product/product/list.do?isShow=0";
			}

			productService.addProduct(productSaveData);

			return "redirect:/admin/product/product/list.do?isShow=0";

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-add:" + e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "redirect:/admin/product/product/list.do?isShow=0";
		}
	}

	/**
	 * @Title: toStockList <br>
	 * @Description: 转到库存列表 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/{productId}/toStockList.do", method = RequestMethod.GET)
	public String toStockList(@PathVariable("productId") Integer productId,
			PageQuery pageQuery, BindingResult errors, ModelMap map,
			HttpServletRequest request) {

		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "sku/list";
			}

			if (productId != null) {
				List<SkuCellResponse> skuList = skuCellService
						.getSkuListByProductId(productId, pageQuery);
				int totalCount = skuCellService
						.getTotalCountByProductId(productId);
				ViewPageBean<SkuCellResponse> pageBean = new ViewPageBean<SkuCellResponse>(
						pageQuery.getPageNo(), totalCount, skuList);

				String url = request.getContextPath()
						+ "/admin/product/product/" + productId
						+ "/toStockList.do";
				HashMap<String, Object> params = Maps.newHashMap();
				List<String> pageView = pageBean.renderPageView(url, params);

				map.addAttribute("pageBean", pageBean);
				map.addAttribute("pageView", pageView);
				map.addAttribute("productId", productId);
			}

			return "sku/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-toStockList:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "sku/list";
		}
	}

	/**
	 * @Title: saveSku <br>
	 * @Description: 保存最小单元修改信息 <br>
	 * @Author: madison <br>
	 * @param @param skuCell
	 * @param @param response 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/saveSku.do", method = RequestMethod.POST)
	public void saveSku(SkuCell skuCell, HttpServletResponse response) {
		try {
			int row = skuCellService.saveSku(skuCell);
			JSONObject jsonObject = new JSONObject();
			if (row > 0) {
				jsonObject.put("msg", "保存成功，影响" + row + "行！");
			} else {
				jsonObject.put("msg", "保存失败!");
			}
			ResponseUtils.renderJson(response, jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-saveSku:"
					+ e.getMessage());
		}
	}

	/**
	 * @Title: deleteProductById <br>
	 * @Description: 根据id删除商品 - 级联删除图片表和最小销售单元表 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/deleteProduct.do", method = RequestMethod.GET)
	public String deleteProductById(ProductQuery productQuery,
			BindingResult errors, ModelMap map, HttpServletRequest request) {
		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				return "redirect:/admin/product/product/list.do?isShow=0";
			}

			productService.deleteProductById(productQuery);
			map.put("productQuery", productQuery);
			return "redirect:/admin/product/product/list.do?isShow=0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-toStockList:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "redirect:/admin/product/product/list.do?isShow=0";
		}
	}

	/**
	 * @Title: underCarriage <br>
	 * @Description:下架 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/underCarriage.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void underCarriage(Integer[] ids) {
		try {
			// -- 批量下架
			productService.underCarriageByIds(ids);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-underCarriage:"
					+ e.getMessage());
		}
	}

	/**
	 * @Title: topCarriage <br>
	 * @Description: 上架<br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/product/topCarriage.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void topCarriage(Integer[] ids) {
		try {
			// -- 批量上架和生成freemarker静态页面
			productService.topCarriageByIds(ids);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-ProductController-topCarriage:"
					+ e.getMessage());
		}
	}
	
	
}
