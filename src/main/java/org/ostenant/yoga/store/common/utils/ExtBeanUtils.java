package org.ostenant.yoga.store.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 项目名称：yoga-store <br>
 * 类名称：ExtBeanUtils <br>
 * 类描述： 扩展BeanUtils <br>
 * 创建人：madison <br>
 * 创建时间：2015-10-23 上午10:51:46 <br>
 * 修改人：madison <br>
 * 修改时间：2015-10-23 上午10:51:46 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class ExtBeanUtils {

	public static final Logger logger = LoggerFactory
			.getLogger(ExtBeanUtils.class);

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			logger.error("error when copy properties from bean {} to {}. \\n"
					+ e.getMessage(), orig.getClass().getName(), dest
					.getClass().getName());
		} catch (InvocationTargetException e) {
			logger.error("error when copy properties from bean {} to {}. \\n"
					+ e.getMessage(), orig.getClass().getName(), dest
					.getClass().getName());
		}
	}

	public static Object cloneBean(Object bean) {
		try {
			return BeanUtils.cloneBean(bean);
		} catch (IllegalAccessException e) {
			logger.error("error when clone from bean from bean {} to {}. \\n"
					+ e.getMessage(), bean.getClass().getName());
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			logger.error(
					"error when clone from bean {} to {}. \\n" + e.getMessage(),
					bean.getClass().getName());
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			logger.error(
					"error when clone from bean {} to {}. \\n" + e.getMessage(),
					bean.getClass().getName());
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			logger.error(
					"error when clone from bean {} to {}. \\n" + e.getMessage(),
					bean.getClass().getName());
			throw new RuntimeException(e);
		}

	}

	public static void populate(Object bean, Map<?, ?> properties) {
		try {
			BeanUtils.populate(bean, properties);
		} catch (IllegalAccessException e) {
			logger.error(
					"error when clone from map {} to bean {}. \\n"
							+ e.getMessage(), bean.getClass().getName(),
					properties.getClass().getName());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.error(
					"error when clone from map {} to bean {}. \\n"
							+ e.getMessage(), bean.getClass().getName(),
					properties.getClass().getName());
			e.printStackTrace();
		}
	}

}
