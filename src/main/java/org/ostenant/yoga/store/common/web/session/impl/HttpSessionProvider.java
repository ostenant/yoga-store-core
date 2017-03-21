package org.ostenant.yoga.store.common.web.session.impl;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.web.session.SessionProvider;

public class HttpSessionProvider implements SessionProvider {

	public void setAttribute(HttpServletRequest request, String name,
			Serializable value) {
		// 获取session 如果没有session 创建
		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute(name, value);
		}

	}

	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false); // 获取之前的session
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 销毁服务器的session域
			session.invalidate();
		}
		// 返回客户端jsessionId为空
		Cookie c = new Cookie(CustomConstant.SESSION_ID, null);
		c.setMaxAge(0);
		response.addCookie(c);
	}

	public String getSessionId(HttpServletRequest request) {
		// 获取request url的sessionId
		return request.getRequestedSessionId();
	}

}
