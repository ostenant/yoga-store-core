package org.ostenant.yoga.store.core.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.ostenant.yoga.store.core.service.FreeMarkerStaticService;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 采用Spring配置方式 项目名称：yoga-store <br>
 * 类名称：FreeMarkerStaticServiceImpl <br>
 * 类描述： <br>
 * 创建人：madison <br>
 * 创建时间：2015-11-13 下午8:50:40 <br>
 * 修改人：madison <br>
 * 修改时间：2015-11-13 下午8:50:40 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class FreeMarkerStaticServiceImpl implements FreeMarkerStaticService,
		ServletContextAware {

	private Configuration configuration;

	private ServletContext servletContext;

	/**
	 * 有setter方法便能自动注入
	 */
	public void setFreeMarkerConfigurer(
			FreeMarkerConfigurer freeMarkerConfigurer) {
		this.configuration = freeMarkerConfigurer.getConfiguration();
	}

	public void generateHtml(Map<String, Object> rootMap, Integer id) {
		Writer out = null;
		try {
			String templateName = "productDetail.html";
			// -- 获取模版 使用UTF-8读取
			Template template = configuration.getTemplate(templateName);

			// -- 获取真实路径
			String path = servletContext.getRealPath("/static/product/" + id
					+ ".html");
			File file = new File(path);
			// -- 如果父级目录不存在 递归创建目录
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs(); // 创建多级目录
			}
			// -- 写出到硬盘 使用UTF-8
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// -- 输出页面
			template.process(rootMap, out);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					out = null;
				}
			}
		}
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
