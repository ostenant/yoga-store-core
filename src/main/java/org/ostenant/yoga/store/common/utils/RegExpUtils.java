package org.ostenant.yoga.store.common.utils;

/**
 * 类名称：RegExpUtils <br>
 * 类描述： 正则表达式工具类 <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-26 下午3:06:10 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-26 下午3:06:10 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class RegExpUtils {

	/* 去掉'-'后的32位GUID */
	public static final String GUID_MERGED_32 = "^[0-9a-z]{32}$";

	/* 大陆地区11位手机号码 */
	public static final String MOBILE_NUMBER_11 = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

	/* 非负浮点数 */
	public static final String NONNEGATIVE_FLOAT = "^\\d+(\\.\\d+)?$";

	/* 非负整数 */
	public static final String NONNEGATIVE_INTEGER = "^(0|[1-9]\\d*)$";

	/* 非负整数 */
	public static final String POSITIVE_INTEGER = "^([1-9]\\d*)$";

	/* IP地址 */
	public static final String IP_ADDRESS = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";

	/* 6位数字验证码 */
	public static final String CAPTCHA_6 = "^\\d{6}$";

	/* URL */
	public static final String URL = "^((https|http|ftp|rtsp|mms)?://)?"
			+ "?(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" + "(localhost)" + "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-zA-Z_!~*'()-]+\\.)*" // 域名- www.
			+ "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\\." // 二级域名
			+ "[a-zA-Z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";

	/* email */
	public static final String EMAIL = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";

	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		System.out.println(url.matches(URL));
	}
}
