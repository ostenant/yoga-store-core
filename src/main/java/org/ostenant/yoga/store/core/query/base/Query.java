package org.ostenant.yoga.store.core.query.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("Query")
public class Query implements Serializable {

	private static final long serialVersionUID = -867274070596677040L;
	/********************** 查询字段指定 **************************/

	// -- 查询的字段 用都好分割
	private String fields;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	/********************** order by **************************/

	// orderby 集合
	private List<FieldOrder> fieldOrders = new ArrayList<FieldOrder>();

	public List<FieldOrder> getFieldOrders() {
		return fieldOrders;
	}

	public void setFieldOrders(List<FieldOrder> fieldOrders) {
		this.fieldOrders = fieldOrders;
	}

	public class FieldOrder {
		private String field;
		private String order; // desc asc
		
		public FieldOrder() {}

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
	public void orderBy(String field, boolean isAsc) {
		fieldOrders.add(new FieldOrder(field, isAsc ? "ASC" : "DESC"));
	}
}
