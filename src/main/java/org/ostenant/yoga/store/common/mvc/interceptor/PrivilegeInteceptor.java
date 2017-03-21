package org.ostenant.yoga.store.common.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.web.session.SessionProvider;
import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.dao.user.BuyerMapper;
import org.ostenant.yoga.store.core.query.BuyerQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 项目名称：yoga-store <br>
 * 类名称：PrivilegeInteceptor <br>
 * 类描述： 默认注册在HandlerExcutionChain <br>
 * HandlerExcutionChain默认注册在HandlerMapping <br>
 * 一个handler可以对应多个inteceptor<br>
 * 创建人：madison <br>
 * 创建时间：2015-11-16 上午11:01:46 <br>
 * 修改人：madison <br>
 * 修改时间：2015-11-16 上午11:01:46 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class PrivilegeInteceptor implements HandlerInterceptor {

	// -- 用于开发阶段
	private boolean useAdmin;
	// --拦截的url
	private String interceptUrl;

	private Boolean isLogin;

	/* 默认拦截的URI */
	private static final String DEFAULT_INTERCEPT_URL = "/buyer/";

	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private BuyerMapper buyerMapper;

	public boolean isUseAdmin() {
		return useAdmin;
	}

	public void setUseAdmin(boolean useAdmin) {
		this.useAdmin = useAdmin;
	}

	public String getInterceptUrl() {
		return interceptUrl;
	}

	public void setInterceptUrl(String interceptUrl) {
		this.interceptUrl = interceptUrl;
	}

	public Boolean isLogin() {
		return isLogin;
	}

	public void setLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	private static Logger logger = LoggerFactory
			.getLogger(PrivilegeInteceptor.class);

	/**
	 * handlerAdapter执行目标方法之前拦截执行
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		logger.info("preHandle starts >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");

		// -- 设置默认的拦截URI
		if (null == interceptUrl || StringUtils.isBlank(interceptUrl)) {
			setInterceptUrl(DEFAULT_INTERCEPT_URL);
		}

		// 初始化
		this.isLogin = (Boolean) sessionProvider.getAttribute(request,
				"isLogin");
		if (null == this.isLogin) {
			sessionProvider.setAttribute(request, "isLogin", false);
		} else {
			sessionProvider.setAttribute(request, "isLogin", true);

		}

		// -- 判断请求URI是否匹配拦截地址
		String requestURI = request.getRequestURI();
		boolean isIntecepted = requestURI.startsWith(this.interceptUrl)
				|| requestURI.contains(this.interceptUrl);

		if (StringUtils.isNotBlank(requestURI) && isIntecepted) {
			// -- 启动拦截

			if (useAdmin) {
				// -- 默认查询
				BuyerQuery buyerQuery = new BuyerQuery();
				buyerQuery.setUsername("ailian");
				Buyer administor = buyerMapper.getBuyerByCondition(buyerQuery);
				sessionProvider.setAttribute(request, CustomConstant.BUYER_ID,
						administor);
			} // -- 不使用管理员身份
			else {
				Buyer buyer = (Buyer) sessionProvider.getAttribute(request,
						CustomConstant.BUYER_ID);
				if (null == buyer) {
					sessionProvider.setAttribute(request, "isLogin", false);
					// -- 为空就重定向到登录页面
					response.sendRedirect("/shopping/login.shtml?returnUrl="
							+ requestURI);
					return false;
				}
			}
		}

		logger.info("preHandle ends >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		return true;
	}

	/**
	 * handlerAdapter成功执行目标方法之后拦截执行
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
	}

	/**
	 * 渲染试图以后执行
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
	}

}
