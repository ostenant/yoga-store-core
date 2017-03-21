package org.ostenant.yoga.store.common.constant;

/**
 * http响应码
 * @author madison
 *
 */
public abstract class LSResponseCode {
	
	/**
	 * <tt>200 OK</tt> (HTTP/1.0 - RFC 1945)
	 */
	public static final int	SUCCESS			= 200;

	/**
	 * <tt>400 Bad Request</tt> (HTTP/1.1 - RFC 2616)
	 */
	public static final int	FAILURE			= 400;

	/**
	 * <tt>401 Unauthorized</tt> (HTTP/1.0 - RFC 1945)
	 */
	public static final int	UNAUTHENTICATED	= 401;

	/**
	 * <tt>403 Forbidden</tt> (HTTP/1.0 - RFC 1945)
	 */
	public static final int	FORBIDDEN		= 403;
	
	/**
	 * <tt>参数验证失败</tt>
	 */
	public static final int	INCORRECTPARAMS	= 450;

}
