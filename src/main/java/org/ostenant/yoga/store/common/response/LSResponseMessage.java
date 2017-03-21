package org.ostenant.yoga.store.common.response;

import java.io.Serializable;
import java.util.Map;

/**
 * 项目名称：yoga-store <br>
 * 类名称：JSONResponse <br>
 * 类描述： 响应客户端的json对象 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-22 下午2:38:12 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-22 下午2:38:12 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class LSResponseMessage implements Serializable {

	private static final long serialVersionUID = 208833721654216523L;

	/**
	 * 错误码 or（成功码）
	 */
	private int code;

	/**
	 * 错误消息 or（成功消息）
	 */
	private String message;

	/**
	 * 数据信息
	 */
	private Map<String, Object> data;

	public LSResponseMessage() {
	}

	/**
	 * 请求失败时调用的构造函数
	 * 
	 * @param code
	 *            错误码
	 * @param message
	 *            错误消息
	 */
	public LSResponseMessage(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * 请求成功时调用的构造函数
	 * 
	 * @param code
	 *            错误码
	 * @param message
	 *            错误消息
	 * @param data
	 *            错误数据
	 */
	public LSResponseMessage(int code, String message, Map<String, Object> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JSONResponse [code=" + code + ", message=" + message
				+ ", data=" + data + "]";
	}

}
