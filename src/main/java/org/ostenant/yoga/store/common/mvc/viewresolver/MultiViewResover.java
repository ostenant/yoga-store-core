package org.ostenant.yoga.store.common.mvc.viewresolver;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 项目名称：yoga-store <br>
 * 类名称：MultiViewResover <br>
 * 类描述： 通过多视图解析器注册不同类型的视图解析器 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-23 下午9:41:37 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-23 下午9:41:37 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class MultiViewResover implements ViewResolver {

	private Map<String, ViewResolver> viewResolvers;

	/**
	 * 默认jsp视图解析器
	 */
	private final static String DEFAULT_RESOLVER = "jsp";
	
	/**
	 * json视图解析器
	 */
	private final static String JSON_RESOLVER = "json";
	
	/**
	 * json视图
	 */
	private final static String JSON_VIEW_NAME = "jsonView";

	/**
	 * 自定义resolve类默认为jsp视图，其余视图用下划线分隔
	 */
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		// 判断是否为json
		String tmpViewName = viewName.trim().toLowerCase();
		if (JSON_RESOLVER.equals(tmpViewName)) {
			ViewResolver resolver = viewResolvers.get(JSON_RESOLVER);
			return resolver.resolveViewName(JSON_VIEW_NAME, locale);
		}
		// 若无下划线，默认转向jsp视图
		int n = viewName.lastIndexOf("_");
		if (n == (-1)) {
			ViewResolver resolver = viewResolvers.get(DEFAULT_RESOLVER);
			return resolver.resolveViewName(viewName, locale);
		}
		// 有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry key="ftl">的key匹配
		String suffix = viewName.substring(n + 1);
		// 根据下划线后面的字符串去获取托管的视图解析类对象
		ViewResolver resolver = viewResolvers.get(suffix);
		// 取下划线前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello_jsp
		viewName = viewName.substring(0, n);
		if (resolver != null) {
			return resolver.resolveViewName(viewName, locale);
		}
		return null;
	}

	public Map<String, ViewResolver> getViewResolvers() {
		return viewResolvers;
	}

	public void setViewResolvers(Map<String, ViewResolver> viewResolvers) {
		this.viewResolvers = viewResolvers;
	}

}
