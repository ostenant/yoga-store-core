package org.ostenant.yoga.store.common.constant;

/**
 * 项目名称：yoga-store <br>
 * 类名称：CustomConstant <br>
 * 类描述： 自定义常量 <br>
 * 创建人：madison   <br>
 * 创建时间：2015-10-26 上午10:16:44 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-26 上午10:16:44 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class CustomConstant {

	/**
	 * 图片服务器URL地址
	 */
	public final static String IMAGE_SERVER_URL = "http://119.29.228.69:8088/";
	
	/**
	 * app服务器URL地址
	 */
	public final static String APP_SERVER_HOST = "http://119.29.228.69:8888/";

	/**
	 * 目录wsdl地址
	 */
	public final static String DIR_WSDL_URL = "http://119.29.228.69:8089/picture/mkdir?wsdl";

	/**
	 * wsdl命名空间
	 */
	public final static String NAMESPACE_URI = "http://webservice.yoga-store.com/";
	
	/**
	 * wsdl服务视图名称
	 */
	public final static String LOCAL_PART = "DirMakerImplService";

	/**
	 * 分页显示的最大页码数
	 */
	public final static int MAX_PAGINATION = 9;

	/**
	 * 分页显示的最大页码数
	 */
	public final static int MIN_PAGINATION = 3;

	/**
	 * 分页显示的奇数页码数
	 */
	public final static int ODD_PAGINATION = 9;

	/**
	 * 分页显示的偶数页码数
	 */
	public final static int EVEN_PAGINATION = 8;

	/**
	 * 分页显示的初始化页码数
	 */
	public final static int INIT_PAGINATION = 7;
	
	/**
	 * session的标识符
	 */
	public final static String SESSION_ID =  "JSESSIONID";
	
	/**
	 * 验证通过存放用户信息
	 */
	public final static String BUYER_ID =  "BUYER_ID";

	/**
	 * 购物车cookie名称
	 */
	public final static String BUYER_CART_COOKIE =  "BUYER_CART";
	
	
	/**
	 * 用户保存送货地址的最大条数
	 */
	public final static Integer BUYER_SAVE_ADDR_COUNT =  5;
	
	

}
