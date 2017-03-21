package org.ostenant.yoga.store.core.query.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 项目名称：yoga-store <br>
 * 类名称：BaseQuery <br>
 * 类描述： 带有泛型实例化查询对象的bean <br>
 * 创建人：madison <br>
 * 创建时间：2015-11-21 下午2:25:04 <br>
 * 修改人：madison <br>
 * 修改时间：2015-11-21 下午2:25:04 <br>
 * 修改备注： <br>
 * 
 * @version
 */
public class BaseQuery<T> implements Serializable {

	private static final long serialVersionUID = 4574325434457766977L;

	private static final Logger logger = LoggerFactory
			.getLogger(BaseQuery.class);

	// 查询对象的类型
	private Class<T> clazz = null;
	// 查询对象的实例
	private T queryBean = null;

	// -- 查询的字段
	private String fields;
	// -- orderBy 集合
	private List<FieldOrder> fieldOrders = new ArrayList<FieldOrder>();

	@SuppressWarnings("unchecked")
	public BaseQuery() {

		// -- 子类实例化时获取本类的class
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// -- 获取父类泛型类型
		Type[] type = pt.getActualTypeArguments();
		// -- 此处只有一个参数<T>
		this.clazz = (Class<T>) type[0];

		logger.info(">>>>> " + clazz.getName());

		// -- 给queryBean实例化
		if (null == queryBean) {
			try {
				// -- 实例化queryBean
				this.queryBean  = (T) Class.forName(this.clazz.getName())
						.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
				logger.error(">>>>> " + e.getMessage());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.error(">>>>> " + e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error(">>>>> " + e.getMessage());
			}
		}
	}

	public T getQueryBean() {
		return queryBean;
	}

	public String getFields() {
		return fields;
	}

	public BaseQuery<T> setFields(String fields) {
		this.fields = fields;
		return this;
	}

	public List<FieldOrder> getFieldOrders() {
		return fieldOrders;
	}

	public BaseQuery<T> setFieldOrders(List<FieldOrder> fieldOrders) {
		this.fieldOrders = fieldOrders;
		return this;
	}

	public class FieldOrder {
		private String field;
		private String order; // desc asc

		public FieldOrder() {
		}

		public FieldOrder(String field, String order) {
			super();
			this.field = field;
			this.order = order;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}

	}

	/**
	 * @Title: orderBy <br>
	 *         添加排序条件
	 * @throws
	 */
	public BaseQuery<T> orderBy(String field, boolean isAsc) {
		this.fieldOrders.add(new FieldOrder(field, isAsc ? "ASC" : "DESC"));
		return this;
	}

}
