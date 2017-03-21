package org.ostenant.yoga.store.core.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理 中央入口
 * 
 * @author madison
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class CenterController {

	/**
	 * @Title: index <br>
	 * @Description: 跳转入口页面 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/index.do")
	public String index() {
		return "index";
	}
	

	/**
	 * @Title: top <br>
	 * @Description: 跳转头页面 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/top.do")
	public String top() {
		return "top";
	}
	

	/**
	 * @Title: main <br>
	 * @Description: 跳转主页面 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/main.do")
	public String main() {
		return "main";
	}

	/**
	 * @Title: left <br>
	 * @Description: 跳转左页面 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/left.do")
	public String left() {
		return "left";
	}

	/**
	 * @Title: right <br>
	 * @Description: 跳转右页面 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return String    返回类型   <br>
	 * @throws
	 */
	@RequestMapping(value = "/right.do")
	public String right() {
		return "right";
	}

}
