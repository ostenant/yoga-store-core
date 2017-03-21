package org.ostenant.yoga.store.core.query;


import org.apache.ibatis.type.Alias;
import org.ostenant.yoga.store.core.query.base.PageQuery;

@Alias("BrandQuery")
public class BrandQuery extends PageQuery {

	private static final long serialVersionUID = 319013555932774251L;

	private Integer id;

	private String name;

	private String description;

	private String imgUrl;

	private String webSite;

	private Integer sort;

	private int isDisplay;

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
		return imgUrl;
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

	public int getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", description="
				+ description + ", imgUrl=" + imgUrl + ", webSite=" + webSite
				+ ", sort=" + sort + ", isDisplay=" + isDisplay + "]";
	}
}
