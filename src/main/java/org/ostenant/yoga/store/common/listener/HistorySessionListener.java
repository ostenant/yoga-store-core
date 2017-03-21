package org.ostenant.yoga.store.common.listener;

import java.util.LinkedList;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.ostenant.yoga.store.core.response.ProductResponse;

public class HistorySessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent se) {
//		HttpSession session = se.getSession();
//		Cookie cookie = new Cookie("JSESSIONID", session.getId());
//		cookie.setMaxAge(3600*23*30);
//		cookie.setPath("/");
		// 循环队列 用于放历史记录
		LinkedList<ProductResponse> histProductList = new LinkedList<ProductResponse>();
		se.getSession().setAttribute("histProductList", histProductList);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
