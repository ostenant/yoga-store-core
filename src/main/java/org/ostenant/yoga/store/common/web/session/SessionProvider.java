package org.ostenant.yoga.store.common.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionProvider {

	/**
	 * @Title: setAttribute <br>
	 * @Description: 向session中保存值 <br>
	 * @Author: madison <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void setAttribute(HttpServletRequest request, String name,
			Serializable value);

	/**
	 * @Title: getAttribute <br>
	 * @Description: 获取session域范围中的值 <br>
	 * @Author: madison <br>
	 * @return Serializable    返回类型   <br>
	 * @throws
	 */
	public Serializable getAttribute(HttpServletRequest request, String name);

	/**
	 * @Title: logout <br>
	 * @Description: 退出登陆 <br>
	 * @Author: madison <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @Title: getSessionId <br>
	 * @Description: 获取Session Id<br>
	 * @Author: madison <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	public String getSessionId(HttpServletRequest request);

}
