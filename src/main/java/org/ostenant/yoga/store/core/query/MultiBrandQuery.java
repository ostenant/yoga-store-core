package org.ostenant.yoga.store.core.query;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.ostenant.yoga.store.core.query.base.PageQuery;

@Alias("MultiBrandQuery")
public class MultiBrandQuery extends PageQuery implements
		Serializable {

	private static final long serialVersionUID = 525909736388802550L;

	private String name;

	private String description;

	private String imgUrl;

	private String webSite;

	private Integer sort;

	private int isDisplay;

	private List<Integer> ids;

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

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
