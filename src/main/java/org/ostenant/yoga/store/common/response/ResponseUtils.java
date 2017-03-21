package org.ostenant.yoga.store.common.response;

/**
 * 项目名称：yoga-store <br>   
 * 类名称：ResponseUtils <br>   
 * 类描述： 用于Response异步返回各种格式数据  <br>
 * 创建人：madison <br>  
 * 创建时间：2015-10-28 下午10:24:57 <br>  
 * 修改人：madison  <br>
 * 修改时间：2015-10-28 下午10:24:57   <br>
 * @version
 */

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {

	/* 发送内容  */
	public static void render(HttpServletResponse response,String contentType,String text){
		response.setContentType(contentType);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 发送JSON */
	public static void renderJson(HttpServletResponse response,String text){
		render(response, "application/json;charset=UTF-8", text);
	}
	
	/* 发送xml */
	public static void renderXml(HttpServletResponse response,String text){
		render(response, "text/xml;charset=UTF-8", text);
	}
	
	//发送text
	public static void renderText(HttpServletResponse response,String text){
		render(response, "text/plain;charset=UTF-8", text);
	}
	
	
}

