package org.ostenant.yoga.store.common.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.core.dao.product.ProductMapper;
import org.ostenant.yoga.store.core.response.ProductResponse;
import org.ostenant.yoga.store.core.response.SkuCellResponse;
import org.ostenant.yoga.store.core.service.FreeMarkerStaticService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 项目名称：yoga-store <br>   
 * 类名称：FreemarkerListener <br>   
 * 类描述：  启动服务器时为所有已经上架的商品进行页面静态化 <br>
 * 创建人：madison <br>  
 * 创建时间：2015-11-14 下午4:20:40 <br>  
 * 修改人：madison  <br>
 * 修改时间：2015-11-14 下午4:20:40   <br>
 * 修改备注：   <br>
 * @version
 */
public class FreemarkerListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		// -- 获取容器对象
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		
		// -- 初始化容器需要的对象
		ProductMapper productMapper = (ProductMapper) context.getBean("productMapper");
		FreeMarkerStaticService freeMarkerStaticService = (FreeMarkerStaticService) context
				.getBean("freeMarkerStaticService");
		ProductService productService = (ProductService) context.getBean("productServiceImpl");
		SkuCellService skuCellService = (SkuCellService) context.getBean("skuCellServiceImpl");
		
		List<Integer> ids = productMapper.getAllUpCarriageIds();
		
		Map<String, Object> rootMap = null;
		if (ids != null && ids.size() > 0) {
			for (Integer productId : ids) {
				rootMap = new HashMap<String, Object>();
				// 指定商品详情
				ProductResponse productResponse = productService.getProductById(productId);

				// 最小销售单元
				List<SkuCellResponse> skuCells = skuCellService
						.getSkuListByPidWithoutPage(productId);

				// freemarker遍历时key不能是Integer
				HashMap<String, String> colors = new HashMap<String, String>();
				for (SkuCellResponse skuCell : skuCells) {
					if (!colors.containsKey(skuCell.getColorId() + "")) {
						// 如果没有key
						colors.put(skuCell.getColorId() + "",
								skuCell.getColor());
					}
				}

				rootMap.put("product", productResponse);
				rootMap.put("skuCells", skuCells);
				rootMap.put("colors", colors);
				rootMap.put("basePath", CustomConstant.IMAGE_SERVER_URL);
				rootMap.put("path", CustomConstant.IMAGE_SERVER_URL
						+ productResponse.getUrl());

				// 分别生成模版输出
				freeMarkerStaticService.generateHtml(rootMap, productId);
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
