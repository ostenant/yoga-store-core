package org.ostenant.yoga.store.common.constant;

/**
 * http响应消息内容
 * @author madison
 *
 */
public abstract class LSResponseContent {
	
	/**
     * 系统异常
     */
    public static final String SYS_EXCEPTION = "系统异常";

    /**
     * 数据序列化
     */
    public static final String SERILIZE_DATA_NOT_NULL = "数据异常";

    /**
     * 帐号未注册
     */
    public static final String ACOUNT_NOT_FIND = "该账号未注册";

    /**
     * 帐号密码不匹配
     */
    public static final String ACOUNT_PASSWORD_NOT_MATCH = "账号或密码有误";

    /**
     * 用户验证失败
     */
    public static final String USER_VALIDATE_FAILURE = "用户验证失败";

    /**
     * 认证信息修改
     */
    public static final String USER_AUTH_HAS_BEEN_MODIFIED = "认证信息已被修改,请重新登录";

    /**
     * 权限不匹配
     */
    public static final String ACOUNT_AUTHORIZATION_FORBIDDEN = "权限不匹配，请重新登录";

}
