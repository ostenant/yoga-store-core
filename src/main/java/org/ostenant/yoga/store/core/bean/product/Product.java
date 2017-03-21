package org.ostenant.yoga.store.core.bean.product;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Product")
public class Product implements Serializable {

	private static final long serialVersionUID = -3689408531509165888L;

	private Integer id;

	private String no;

	private String name;

	private Double weight;

	private Boolean isNew;

	private Boolean isHot;

	private Boolean isCommend;

	private Date createTime;

	private String createUserId;

	private Date checkTime;

	private String checkUserId;

	private Boolean isShow;

	private Boolean isDel;

	private Integer typeId;

	private Integer brandId;

	private String keywords;

	private Integer sales;

	private Integer price;

	private String feature;

	private String color;

	private String size;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsCommend() {
		return isCommend;
	}

	public void setIsCommend(Boolean isCommend) {
		this.isCommend = isCommend;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", no=" + no + ", name=" + name
				+ ", weight=" + weight + ", isNew=" + isNew + ", isHot="
				+ isHot + ", isCommend=" + isCommend + ", createTime="
				+ createTime + ", createUserId=" + createUserId
				+ ", checkTime=" + checkTime + ", checkUserId=" + checkUserId
				+ ", isShow=" + isShow + ", isDel=" + isDel + ", typeId="
				+ typeId + ", brandId=" + brandId + ", keywords=" + keywords
				+ ", sales=" + sales + ", price=" + price + ", feature="
				+ feature + ", color=" + color + ", size=" + size + "]";
	}

}
