package org.ostenant.yoga.store.core.bean.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Buyer")
public class Buyer implements Serializable {

	private static final long serialVersionUID = 7875921434857533856L;

	private String username;

	private String password;

	private String gender;

	private String email;

	private String realName;

	private Date registerTime;

	private String province;

	private String city;

	private String town;

	private String addr;

	private Boolean isDel;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Buyer [username=" + username + ", password=" + password
				+ ", gender=" + gender + ", email=" + email + ", realName="
				+ realName + ", registerTime=" + registerTime + ", province="
				+ province + ", city=" + city + ", town=" + town + ", addr="
				+ addr + ", isDel=" + isDel + "]";
	}

}
