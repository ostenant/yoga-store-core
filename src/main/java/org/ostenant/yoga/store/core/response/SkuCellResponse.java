package org.ostenant.yoga.store.core.response;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("SkuCellResponse")
public class SkuCellResponse implements Serializable {

	private static final long serialVersionUID = 4466377437815971886L;

	private Integer id;

	private Integer productId;

	private Integer colorId; // 颜色

	private String size;

	private Double deliveFee;

	private Double skuPrice;

	private Integer stockInventory;

	private Integer skuUpperLimit;

	private String location;

	private String skuImg;

	private Integer skuSort;

	private String skuName;

	private Double marketPrice;

	private Date createTime;

	private Date updateTime;

	private String createUserId;

	private String updateUserId;

	private Integer lastStatus;

	private Integer skuType;

	private Integer sales;

	// -- 颜色
	private String color;
	// -- 商品编号
	private String no;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getDeliveFee() {
		return deliveFee;
	}

	public void setDeliveFee(Double deliveFee) {
		this.deliveFee = deliveFee;
	}

	public Double getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public Integer getStockInventory() {
		return stockInventory;
	}

	public void setStockInventory(Integer stockInventory) {
		this.stockInventory = stockInventory;
	}

	public Integer getSkuUpperLimit() {
		return skuUpperLimit;
	}

	public void setSkuUpperLimit(Integer skuUpperLimit) {
		this.skuUpperLimit = skuUpperLimit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSkuImg() {
		return skuImg;
	}

	public void setSkuImg(String skuImg) {
		this.skuImg = skuImg;
	}

	public Integer getSkuSort() {
		return skuSort;
	}

	public void setSkuSort(Integer skuSort) {
		this.skuSort = skuSort;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Integer lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Integer getSkuType() {
		return skuType;
	}

	public void setSkuType(Integer skuType) {
		this.skuType = skuType;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "SkuCellResponse [id=" + id + ", productId=" + productId
				+ ", colorId=" + colorId + ", size=" + size + ", deliveFee="
				+ deliveFee + ", skuPrice=" + skuPrice + ", stockInventory="
				+ stockInventory + ", skuUpperLimit=" + skuUpperLimit
				+ ", location=" + location + ", skuImg=" + skuImg
				+ ", skuSort=" + skuSort + ", skuName=" + skuName
				+ ", marketPrice=" + marketPrice + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createUserId="
				+ createUserId + ", updateUserId=" + updateUserId
				+ ", lastStatus=" + lastStatus + ", skuType=" + skuType
				+ ", sales=" + sales + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((colorId == null) ? 0 : colorId.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((createUserId == null) ? 0 : createUserId.hashCode());
		result = prime * result
				+ ((deliveFee == null) ? 0 : deliveFee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastStatus == null) ? 0 : lastStatus.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((marketPrice == null) ? 0 : marketPrice.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((sales == null) ? 0 : sales.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((skuImg == null) ? 0 : skuImg.hashCode());
		result = prime * result + ((skuName == null) ? 0 : skuName.hashCode());
		result = prime * result
				+ ((skuPrice == null) ? 0 : skuPrice.hashCode());
		result = prime * result + ((skuSort == null) ? 0 : skuSort.hashCode());
		result = prime * result + ((skuType == null) ? 0 : skuType.hashCode());
		result = prime * result
				+ ((skuUpperLimit == null) ? 0 : skuUpperLimit.hashCode());
		result = prime * result
				+ ((stockInventory == null) ? 0 : stockInventory.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updateUserId == null) ? 0 : updateUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkuCellResponse other = (SkuCellResponse) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (colorId == null) {
			if (other.colorId != null)
				return false;
		} else if (!colorId.equals(other.colorId))
			return false;
		
		return true;
	}

}
