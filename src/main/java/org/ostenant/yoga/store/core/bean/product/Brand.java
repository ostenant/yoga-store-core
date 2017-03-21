package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


@Alias("Brand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 319013585932774251L;

	private Integer id;

	private String name;

	private String description;

	private String imgUrl;

	private String webSite;

	private Integer sort;

	private Boolean isDisplay;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return  imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", description="
				+ description + ", imgUrl=" + imgUrl + ", webSite=" + webSite
				+ ", sort=" + sort + ", isDisplay=" + isDisplay + "]";
	}

}