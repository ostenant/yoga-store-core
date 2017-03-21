package org.ostenant.yoga.store.core.bean;

import java.util.Date;

/**
 * 测试JavaBean
 * @author madison
 *
 */
public class TestTb {
	
	private Integer id;
	private String name;
	private Date birthday;
	
	public TestTb() {}
	
	public TestTb(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}



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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	

}
