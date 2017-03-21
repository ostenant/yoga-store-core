package org.ostenant.yoga.store.common.mvc.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 项目名称：yoga-store <br>
 * 类名称：DefaultWebBindingInitializer <br>
 * 类描述： 注册新的web绑定 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-22 下午4:08:49 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-22 下午4:08:49 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class DefaultWebBindingInitializer implements WebBindingInitializer {

	private static final DateFormat TIMEFORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public void initBinder(WebDataBinder binder, WebRequest request) {

		// 注册自定义日期转换器
		binder.registerCustomEditor(Date.class, new DefaultDateEditor(
				TIMEFORMAT, true));
	}

}
