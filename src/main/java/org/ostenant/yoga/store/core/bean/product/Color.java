package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Color")
public class Color implements Serializable {

	private static final long serialVersionUID = 7284341738710387121L;

	private Integer id;

	private String name;

	private Integer parentId;

	private String imgUrl;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", name=" + name + ", parentId=" + parentId
				+ ", imgUrl=" + imgUrl + "]";
	}

}
