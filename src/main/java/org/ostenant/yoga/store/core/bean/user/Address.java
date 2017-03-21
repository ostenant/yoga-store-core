package org.ostenant.yoga.store.core.bean.user;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1372382724622365983L;

	private Integer id;

	private String buyerId;

	private String name;

	private String city;

	private String addr;

	private String phone;

	private Integer isDef;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsDef() {
		return isDef;
	}

	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", buyerId=" + buyerId + ", name=" + name
				+ ", city=" + city + ", addr=" + addr + ", phone=" + phone
				+ ", isDef=" + isDef + "]";
	}

}
