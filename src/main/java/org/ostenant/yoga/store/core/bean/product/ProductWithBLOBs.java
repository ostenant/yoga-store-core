package org.ostenant.yoga.store.core.bean.product;

import org.apache.ibatis.type.Alias;

@Alias("ProductWithBLOBs")
public class ProductWithBLOBs extends Product {

	private static final long serialVersionUID = -3681586477901806715L;

	private String description;

	private String packageList;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPackageList() {
		return packageList;
	}

	public void setPackageList(String packageList) {
		this.packageList = packageList;
	}

	@Override
	public String toString() {
		return "ProductWithBLOBs [description=" + description
				+ ", packageList=" + packageList + ", getDescription()="
				+ getDescription() + ", getPackageList()=" + getPackageList()
				+ ", getId()=" + getId() + ", getNo()=" + getNo()
				+ ", getName()=" + getName() + ", getWeight()=" + getWeight()
				+ ", getIsNew()=" + getIsNew() + ", getIsHot()=" + getIsHot()
				+ ", getIsCommend()=" + getIsCommend() + ", getCreateTime()="
				+ getCreateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getCheckTime()=" + getCheckTime() + ", getCheckUserId()="
				+ getCheckUserId() + ", getIsShow()=" + getIsShow()
				+ ", getIsDel()=" + getIsDel() + ", getTypeId()=" + getTypeId()
				+ ", getBrandId()=" + getBrandId() + ", toString()="
				+ super.toString() + ", getKeywords()=" + getKeywords()
				+ ", getSales()=" + getSales() + ", getFeature()="
				+ getFeature() + ", getColor()=" + getColor() + ", getSize()="
				+ getSize() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}