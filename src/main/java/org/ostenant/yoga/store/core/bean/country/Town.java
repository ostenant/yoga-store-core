package org.ostenant.yoga.store.core.bean.country;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Town")
public class Town implements Serializable {

	private static final long serialVersionUID = -3576766114455790172L;

	private Integer id;

	private String code;

	private String name;

	private String city;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Town [id=" + id + ", code=" + code + ", name=" + name
				+ ", city=" + city + "]";
	}

}