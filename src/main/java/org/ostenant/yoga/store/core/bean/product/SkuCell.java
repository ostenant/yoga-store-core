package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("SkuCell")
public class SkuCell implements Serializable {

	private static final long serialVersionUID = -3797246584142032418L;

	private Integer id;

	private Integer productId;

	private Integer colorId;

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

	/* 对应商品 */
	private Product product;
	/* 商品颜色 */
	private Color color;
	/* 类型 */
	private Type type;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	@Override
	public String toString() {
		return "SkuCell [id=" + id + ", productId=" + productId + ", colorId="
				+ colorId + ", size=" + size + ", deliveFee=" + deliveFee
				+ ", skuPrice=" + skuPrice + ", stockInventory="
				+ stockInventory + ", skuUpperLimit=" + skuUpperLimit
				+ ", location=" + location + ", skuImg=" + skuImg
				+ ", skuSort=" + skuSort + ", skuName=" + skuName
				+ ", marketPrice=" + marketPrice + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createUserId="
				+ createUserId + ", updateUserId=" + updateUserId
				+ ", lastStatus=" + lastStatus + ", skuType=" + skuType
				+ ", sales=" + sales + ", product=" + product + ", color="
				+ color + ", type=" + type + "]";
	}

}