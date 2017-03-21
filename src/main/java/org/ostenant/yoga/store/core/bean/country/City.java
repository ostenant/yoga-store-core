package org.ostenant.yoga.store.core.bean.country;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("City")
public class City implements Serializable {

	private static final long serialVersionUID = -3199805596934294710L;

	private Integer id;

	private String code;

	private String name;

	private String province;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", name=" + name
				+ ", province=" + province + "]";
	}

}