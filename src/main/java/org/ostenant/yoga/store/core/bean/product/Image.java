package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Image")
public class Image implements Serializable {

	private static final long serialVersionUID = 721593852986575930L;

	private Integer id;

	private Integer productId;

	private String url;

	private Boolean isDef;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsDef() {
		return isDef;
	}

	public void setIsDef(Boolean isDef) {
		this.isDef = isDef;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", productId=" + productId + ", url=" + url
				+ ", isDef=" + isDef + "]";
	}

}