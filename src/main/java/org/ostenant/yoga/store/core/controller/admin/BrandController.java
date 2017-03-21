package org.ostenant.yoga.store.core.controller.admin;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.mvc.BaseController;
import org.ostenant.yoga.store.common.page.ViewPageBean;
import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.query.BrandQuery;
import org.ostenant.yoga.store.core.query.MultiBrandQuery;
import org.ostenant.yoga.store.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/admin")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	/**
	 * @Title: getBrandListByCondition <br>
	 * @Description: 条件 + 分页查询品牌列表<br>
	 * @author: madison <br>
	 * @param brandSearchCondtion
	 *            搜索条件
	 * @param errors
	 *            错误信息绑定
	 * @param map
	 *            模型数据集合
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/list.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String getBrandListByCondition(
			BrandQuery brandSearchCondtion, BindingResult errors,
			ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "brand/list";
			}

			List<Brand> brandList = brandService
					.getBrandListBy(brandSearchCondtion);
			Integer totalCount = brandService
					.getTotalCountBy(brandSearchCondtion);

			ViewPageBean<Brand> pageBean = new ViewPageBean<Brand>(
					brandSearchCondtion.getPageNo(), totalCount, brandList);

			String url = request.getContextPath()
					+ "/admin/product/brand/list.do";
			HashMap<String, Object> params = Maps.newHashMap();
			params.put("isDisplay", brandSearchCondtion.getIsDisplay());
			if (StringUtils.hasText(brandSearchCondtion.getName())) {
				params.put("name", brandSearchCondtion.getName());
			}
			List<String> pageView = pageBean.renderPageView(url, params);

			map.addAttribute("pageBean", pageBean);
			map.addAttribute("pageView", pageView);
			map.addAttribute("params", params);
			map.addAttribute("baseUrl", CustomConstant.IMAGE_SERVER_URL);
			return "brand/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "brand/list";
		}
	}

	/**
	 * @Title: toAdd <br>
	 * @Description:转到添加页面 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/add.do", method = RequestMethod.GET)
	public String toAddBrand() {
		return "brand/add";
	}

	/**
	 * @Title: addBrand <br>
	 * @Description:添加商品 <br>
	 * @Author: madison <br>
	 * @param @param brand
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/addBrand.do", method = RequestMethod.POST)
	public String addBrand(Brand brand) {
		brandService.addBrand(brand);
		return "redirect:/admin/product/brand/list.do?isDisplay=1";
	}

	/**
	 * @Title: toEdit <br>
	 * @Description: 转到修改页面 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/edit.do", method = RequestMethod.GET)
	public String toEditBrand(BrandQuery searchCondtion,
			BindingResult errors, ModelMap map) {
		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "brand/list";
			}

			Brand brand = brandService.getBrandById(searchCondtion.getId());
			map.addAttribute("brand", brand);
			map.addAttribute("baseUrl", CustomConstant.IMAGE_SERVER_URL);
			return "brand/edit";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "brand/list";
		}
	}

	/**
	 * @Title: editBrand <br>
	 * @Description: 编辑品牌 <br>
	 * @Author: madison <br>
	 * @param @param brand
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/editBrand.do", method = RequestMethod.POST)
	public String editBrand(Brand brand) {
		brandService.editBrand(brand);
		return "redirect:/admin/product/brand/list.do?isDisplay=1";
	}

	/**
	 * @Title: deleteBrandById <br>
	 * @Description: 根据id删除商品 <br>
	 * @Author: madison <br>
	 * @param @param id
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/delete.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String deleteBrandById(BrandQuery brandSearchCondtion,
			BindingResult errors, ModelMap map) {
		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
				return "redirect:/admin/product/brand/list.do";
			}

			int id = brandSearchCondtion.getId();
			int isDisplay = brandSearchCondtion.getIsDisplay();
			String name = brandSearchCondtion.getName();

			if (id > 0) {
				brandService.deleteBrandById(id);
				map.addAttribute("id", id);
			} else {
				map.addAttribute("id", 1);
			}

			if (StringUtils.hasText(name)) {
				map.addAttribute("name", name);
			}
			map.addAttribute("isDisplay", isDisplay);

			return "redirect:/admin/product/brand/list.do";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
			return "redirect:/admin/product/brand/list.do";
		}

	}

	/**
	 * @Title: deleteBrandByIds <br>
	 * @Description: 根据id数组删除多个商品 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/remove.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void deleteBrandByIds(int[] ids,
			BrandQuery brandSearchCondtion, BindingResult errors,
			ModelMap map) {
		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
			}

			if (ids != null && ids.length > 0) {
				// 批量删除
				brandService.deleteBrandByIds(ids);
			}

			int isDisplay = brandSearchCondtion.getIsDisplay();
			String name = brandSearchCondtion.getName();

			if (StringUtils.hasText(name)) {
				map.addAttribute("name", name);
			}
			map.addAttribute("isDisplay", isDisplay);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
		}

	}

	/**
	 * @Title: upGrounding <br>
	 * @Description: 上架 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/upGrounding.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void upGrounding(MultiBrandQuery brandSearchCondtion,
			BindingResult errors, ModelMap map) {

		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
			}

			List<Integer> ids = brandSearchCondtion.getIds();
			if (ids != null && ids.size() > 0) {
				// 批量删除
				brandService.upGrounding(brandSearchCondtion);
			}

			resetCondtion(brandSearchCondtion, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
		}
	}

	/**
	 * @Title: upGrounding <br>
	 * @Description: 下架 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/product/brand/underCarriage.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void underCarriage(MultiBrandQuery brandSearchCondtion,
			BindingResult errors, ModelMap map) {

		try {
			String error = validateFieldToString(errors);
			if (StringUtils.hasText(error)) {
				map.addAttribute("error", error);
			}

			List<Integer> ids = brandSearchCondtion.getIds();
			if (ids != null && ids.size() > 0) {
				// 批量删除
				brandService.underCarriage(brandSearchCondtion);
			}

			resetCondtion(brandSearchCondtion, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exception-BrandController-getBrandListByCondition:"
					+ e.getMessage());
			map.addAttribute("error", e.getMessage());
		}
	}

	private void resetCondtion(MultiBrandQuery brandSearchCondtion,
			ModelMap map) {
		int isDisplay = brandSearchCondtion.getIsDisplay();
		String name = brandSearchCondtion.getName();

		if (StringUtils.hasText(name)) {
			map.addAttribute("name", name);
		}
		map.addAttribute("isDisplay", isDisplay);
	}
}
