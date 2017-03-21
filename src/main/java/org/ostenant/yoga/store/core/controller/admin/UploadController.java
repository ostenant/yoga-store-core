package org.ostenant.yoga.store.core.controller.admin;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import net.fckeditor.response.UploadResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.response.ResponseUtils;
import org.ostenant.yoga.store.webservice.DirMakerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/admin")
public class UploadController {

	private static DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	private static Random random = new Random();

	private static Logger logger = LoggerFactory
			.getLogger(UploadController.class);

	/**
	 * @Title: uploadPicture <br>
	 * @Description: 利用jersey实现普通文件上传 <br>
	 * @Author: madison <br>
	 * @param @param file 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void uploadPicture(
			@RequestParam(value = "picture", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// -- 文件扩展名
			String extension = FilenameUtils.getExtension(file
					.getOriginalFilename());

			// -- 设置分目录存储
			String filename = format.format(new Date());

			synchronized (filename) {
				for (int i = 0; i < 3; i++) {
					filename += random.nextInt(10);
				}
			}

			// 调用webservice设置分目录存储
			// 方式一 -- 使用客户端生成代码调用webservice
			// DirMakerImplService dirMakerImplService = new
			// DirMakerImplService();
			// DirMakerImpl dirMakerImpl = dirMakerImplService
			// .getDirMakerImplPort();
			//
			// String root = "/images";
			// String children = "";
			// // 文件存放根目录 + 子目录
			// String dirs = dirMakerImpl.makeDir(root, children);
			//
			// // 获得相对路径
			// String path = dirs + filename + "." + extension;

			// 方式二 -- 使用jdk的service调用webservice

			// -- 1.获取wsdl文档路径
			URL wsdlDocumentLocation = new URL(CustomConstant.DIR_WSDL_URL);
			// wsdl命名空间
			String namespaceURI = CustomConstant.NAMESPACE_URI;
			// 服务视图名称
			String localPart = CustomConstant.LOCAL_PART;
			QName serviceName = new QName(namespaceURI, localPart);

			// 创建服务视图
			Service service = Service.create(wsdlDocumentLocation, serviceName);
			// 获取portType
			DirMakerImpl dirMaker = service.getPort(DirMakerImpl.class);
			// 调用方法
			String root = "/images";
			String children = "";
			// 文件存放根目录 + 子目录
			String dirs = dirMaker.makeDir(root, children);

			// 获得相对路径
			String path = dirs + filename + "." + extension;

			// jersey上传文件
			// ------------------------------------------------------------------------------------------------------------

			// -- 1.创建一个jersey实例化对象
			Client client = new Client();

			// -- 2.设置请求路径(图片服务器)
			// "http://localhost:8088/yoga-store_jersey/"
			String url = CustomConstant.IMAGE_SERVER_URL + path;
			WebResource resource = client.resource(url);

			// -- 3.开始上传 使用put提交
			String result = resource.put(String.class, file.getBytes());
			logger.info("图片上传成功： " + result);

			// -- 4.上传成功返回url（用于请求图片） 和 path（存放数据库）
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("url", url);
			jsonObject.put("path", path);
			ResponseUtils.renderJson(response, jsonObject.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: uploadFCKPicture <br>
	 * @Description: 上传fck图片 <br>
	 * @Author: madison <br>
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	@RequestMapping(value = "/upload/fck.do", method = RequestMethod.POST)
	public void uploadFCKPicture(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			// -- 获取上传文件
			MultipartRequest mr = (MultipartRequest) request;
			// 获取文件map
			Map<String, MultipartFile> fileMap = mr.getFileMap();
			Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();

			for (Entry<String, MultipartFile> entry : entrySet) {
				// 文件
				MultipartFile file = entry.getValue();

				// -- 文件扩展名
				String extension = FilenameUtils.getExtension(file
						.getOriginalFilename());
				// -- 设置分目录存储
				String filename = format.format(new Date());
				synchronized (filename) {
					for (int i = 0; i < 3; i++) {
						filename += random.nextInt(10);
					}
				}

				// -- 1.获取wsdl文档路径
				URL wsdlDocumentLocation;
				wsdlDocumentLocation = new URL(CustomConstant.DIR_WSDL_URL);
				// wsdl命名空间
				String namespaceURI = CustomConstant.NAMESPACE_URI;
				// 服务视图名称
				String localPart = CustomConstant.LOCAL_PART;
				QName serviceName = new QName(namespaceURI, localPart);
				// 创建服务视图
				Service service = Service.create(wsdlDocumentLocation,
						serviceName);
				// 获取portType
				DirMakerImpl dirMaker = service.getPort(DirMakerImpl.class);
				// 调用方法
				String root = "/images";
				String children = "";
				// 文件存放根目录 + 子目录
				String dirs = dirMaker.makeDir(root, children);
				// 获得相对路径
				String path = dirs + filename + "." + extension;
				// -- 2.创建一个jersey实例化对象
				Client client = new Client();
				// -- 3.设置请求路径(图片服务器)
				// "http://localhost:8088/yoga-store_jersey/"
				String url = CustomConstant.IMAGE_SERVER_URL + path;
				WebResource resource = client.resource(url);
				// -- 3.开始上传 使用put提交
				String result = resource.put(String.class, file.getBytes());
				logger.info("图片上传成功： " + result);

				// 返回fck前端图片格式
				// -- 1.创建一个成功返回对象
				UploadResponse uploadResponse = UploadResponse.getOK(url); // 将图片路径转换为fck格式
				// -- 2.输出到页面 fck默认识别
				response.getWriter().print(uploadResponse);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
