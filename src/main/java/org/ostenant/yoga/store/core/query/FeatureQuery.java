package org.ostenant.yoga.store.core.query;

import org.ostenant.yoga.store.core.query.base.Query;

public class FeatureQuery extends Query {

	private static final long serialVersionUID = 1389693173351204680L;

	private Integer id;

	private String name;

	private String value;

	private Integer sort;

	private Boolean isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Feature [id=" + id + ", name=" + name + ", value=" + value
				+ ", sort=" + sort + ", isDel=" + isDel + "]";
	}
}
