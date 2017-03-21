package org.ostenant.yoga.store.common.aop;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.common.web.aop.MemCachedUtil;

import com.danga.MemCached.MemCachedClient;

public class MemcachedAspect {

	@Autowired
	private MemCachedClient memCachedClient;

	private static Logger logger = LoggerFactory
			.getLogger(MemcachedAspect.class);

	// 缓存默认超时时间
	private static final int TIMEOUT = 3600000;
	// 设置自定义超时时间
	private int expiry = TIMEOUT;

	/**
	 * 环绕通知 用于拦截处理get*查询数据 -> 先从缓存中获取数据 -> 如果缓存没有数据 -> 查询数据库 -> 放入缓存
	 * memcached以package.class.methodName.args作为key
	 * 
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		// 第一件事:连接memcached服务器
		if (null == memCachedClient || memCachedClient.stats().isEmpty()) {
			logger.warn("memcached服务器未开启或者连接失败...");
			// 执行目标对象方法 进入Service
			return pjp.proceed();
		}

		// 获取缓存key
		String cachedKey = getCachedKey(pjp);
		// 没有缓存历史
		if (null == memCachedClient.get(cachedKey)) {
			// service返回结果
			Object o = pjp.proceed();
			memCachedClient.set(cachedKey, o, TIMEOUT);
		}

		return memCachedClient.get(cachedKey);
	}

	/**
	 * 获取缓存key
	 * 
	 * @param pjp
	 * @return
	 */
	private String getCachedKey(ProceedingJoinPoint pjp) throws IOException,
			JsonGenerationException, JsonMappingException {
		// 目标对象的全限定名
		String packageClassName = pjp.getTarget().getClass().getName();
		// 目标方法名称
		String methodName = pjp.getSignature().getName();
		// 目标方法参数列表
		Object[] args = pjp.getArgs();
		// 将参数列表转化为json格式
		ObjectMapper om = new ObjectMapper();
		// 序列化
		om.setSerializationInclusion(Inclusion.NON_NULL);
		StringWriter writer = new StringWriter();
		for (Object arg : args) {
			om.writeValue(writer, arg);
		}

		StringBuilder sb = new StringBuilder();

		return sb.append(packageClassName)//
				.append(".")//
				.append(methodName)//
				.append(".")//
				.append(writer.toString())//
				.toString();
	}

	/**
	 * 后置通知 不管目标方法正常执行还是抛出异常都掉用 增删改等操作
	 * 
	 * @param jp
	 */
	public void doAfter(JoinPoint jp) {
		// 如果方法是update,add,delete,remove即不是以get*为名
		String packageClassName = jp.getTarget().getClass().getName();

		// 遍历memcached缓存中的key
		Map<String, Object> keys = MemCachedUtil.getKeySet(memCachedClient);
		Set<String> keySet = keys.keySet();
		for (String key : keySet) {
			if (key.startsWith(packageClassName)) {
				memCachedClient.delete(key);
			}
		}
	}

	public int getExpiry() {
		return expiry;
	}

	public void setExpiry(int expiry) {
		this.expiry = expiry;
	}

}
