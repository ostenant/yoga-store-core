package org.ostenant.yoga.store.core.query;

import org.ostenant.yoga.store.core.query.base.PageQuery;

public class MultiProductQuery extends PageQuery {

	private static final long serialVersionUID = -8373750729978889918L;

	private Boolean displayBrand; // -- 显示品牌查询条件
	private Boolean displayPrice; // -- 显示价格查询条件
	private Boolean displayType; // -- 显示类型查询条件
	private Boolean displayFeature; // -- 显示材质查询条件

	// -- 品牌
	private Integer brandId;
	private String brandName;

	// -- 类型
	private Integer typeId;
	private String typeName;

	// -- 价格
	private Integer minPrice;
	private Integer maxPrice;
	private String priceName;

	// -- 材质
	private Integer featureId;
	private String featureName;

	// -- 排序
	private String orderByDesc;

	public Boolean getDisplayBrand() {
		return displayBrand;
	}

	public void setDisplayBrand(Boolean displayBrand) {
		this.displayBrand = displayBrand;
	}

	public Boolean getDisplayPrice() {
		return displayPrice;
	}

	public void setDisplayPrice(Boolean displayPrice) {
		this.displayPrice = displayPrice;
	}

	public Boolean getDisplayType() {
		return displayType;
	}

	public void setDisplayType(Boolean displayType) {
		this.displayType = displayType;
	}

	public Boolean getDisplayFeature() {
		return displayFeature;
	}

	public void setDisplayFeature(Boolean displayFeature) {
		this.displayFeature = displayFeature;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getOrderByDesc() {
		return orderByDesc;
	}

	public void setOrderByDesc(String orderByDesc) {
		this.orderByDesc = orderByDesc;
	}

	@Override
	public String toString() {
		return "MultiProductQuery [displayBrand=" + displayBrand
				+ ", displayPrice=" + displayPrice + ", displayType="
				+ displayType + ", displayFeature=" + displayFeature
				+ ", brandId=" + brandId + ", brandName=" + brandName
				+ ", typeId=" + typeId + ", typeName=" + typeName
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", priceName=" + priceName + ", featureId=" + featureId
				+ ", featureName=" + featureName + ", orderByDesc="
				+ orderByDesc + "]";
	}

}
