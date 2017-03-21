package org.ostenant.yoga.store.common.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.ostenant.yoga.store.common.constant.LSResponseCode;
import org.ostenant.yoga.store.common.response.LSResponseMessage;
import org.ostenant.yoga.store.common.web.session.SessionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.google.common.collect.Maps;

/**
 * 
 * 项目名称：yoga-store <br>
 * 类名称：BaseController <br>
 * 类描述： 控制器基础类 用于封装常见的异常处理 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-22 下午3:45:37 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-22 下午3:45:37 <br>
 * 修改备注： <br>
 * 
 * @version
 */
@Controller
public class BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	
	@Autowired
	protected SessionProvider sessionProvider;
	
	/**
	 * 
	 * @Title: validateErrorsToString <br>
	 * @Description: 验证表单字段 错误转化为字符串 <br>
	 * @Author: madison <br>
	 * @param @param errors
	 * @param @return 设定文件 <br>
	 * @return String 返回类型 <br>
	 * @throws
	 */
	public String validateFieldToString(BindingResult errors) {

		List<FieldError> errorList = errors.getFieldErrors();
		if (CollectionUtils.isEmpty(errorList)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < errorList.size(); i++) {
			String defaultMessage = errorList.get(i).getDefaultMessage();
			sb.append(defaultMessage);

			if (i != errorList.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * @Title: validateParamsToMap <br>
	 * @Description: 验证表单字段 错误转化为map <br>
	 * @Author: madison <br>
	 * @param @param errors
	 * @param @return 设定文件 <br>
	 * @return Map<String,Object> 返回类型 <br>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> validateFieldToMap(BindingResult errors) {
		List<FieldError> errorList = errors.getFieldErrors();

		if (CollectionUtils.isEmpty(errorList)) {
			return null;
		}

		Map<String, Object> errorMap = Maps.newHashMap();

		for (FieldError fe : errorList) {
			String fieldName = fe.getField();
			String errorMsg = fe.getDefaultMessage();

			if (!errorMap.containsKey(fieldName)) {

				List<String> fielderrorMsgs = new ArrayList<String>();
				fielderrorMsgs.add(errorMsg);
				errorMap.put(fieldName, fielderrorMsgs);
			} else {

				List<String> fielderrorMsgs = (List<String>) errorMap
						.get(fieldName);
				fielderrorMsgs.add(errorMsg);
				errorMap.put(fieldName, fielderrorMsgs);
			}
		}

		return errorMap;
	}

	/**
	 * @Title: validateParams <br>
	 * @Description: 封装参数验证错误信息 <br>
	 * @Author: madison <br>
	 * @param @param errors
	 * @param @return 设定文件 <br>
	 * @return DefaultJsonResponse 返回类型 <br>
	 * @throws
	 */
	public LSResponseMessage validateParams(BindingResult errors) {

		Map<String, Object> errorMap = new HashMap<String, Object>();
		if (addErrors(errors, errorMap)) {

			String errorMessage = validateFieldToString(errors);

			return new LSResponseMessage(LSResponseCode.INCORRECTPARAMS,
					errorMessage, errorMap);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean addErrors(Errors errors, Map<String, Object> map) {
		if (errors.hasErrors()) {
			List<FieldError> errorList = errors.getFieldErrors();
			for (FieldError fe : errorList) {
				String fieldName = fe.getField();
				String errorMsg = fe.getDefaultMessage();

				if (!map.containsKey(fieldName)) {

					List<String> fielderrorMsgs = new ArrayList<String>();
					fielderrorMsgs.add(errorMsg);
					map.put(fieldName, fielderrorMsgs);
				} else {

					List<String> fielderrorMsgs = (List<String>) map
							.get(fieldName);
					fielderrorMsgs.add(errorMsg);
					map.put(fieldName, fielderrorMsgs);
				}

			}
			return true;
		}
		return false;
	}

	/**
	 * @Title: push <br>
	 * @Description: 存取键值对 <br>
	 * @Author: madison <br>
	 * @param @param params
	 * @param @param key
	 * @param @param value    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void push(HashMap<String, Object> params, String key, Object value) {
		if (StringUtils.hasText(key) && value != null) {
			params.put(key, value);
		}
	}

}
