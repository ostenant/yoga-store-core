package org.ostenant.yoga.store.core.bean.user;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = -1128809529348307615L;

	private String username;

	private String password;

	private String degree;

	private String email;

	private Boolean gender;

	private String imgUrl;

	private String phone;

	private String realName;

	private String school;

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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password
				+ ", degree=" + degree + ", email=" + email + ", gender="
				+ gender + ", imgUrl=" + imgUrl + ", phone=" + phone
				+ ", realName=" + realName + ", school=" + school + ", isDel="
				+ isDel + "]";
	}

}

