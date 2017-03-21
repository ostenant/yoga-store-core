package org.ostenant.yoga.store.core.query;

import java.util.Arrays;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("ProductSaveData")
public class ProductSaveData {

	// -- product表字段
	private Integer id; // 商品id
	private String no; // 商品编号
	private String name; // 商品名
	private Double weight; // 毛重
	private Date createTime;
	private String createUserId;
	private Date checkTime; // 审核时间
	private String checkUserId; // 审核者id
	private Boolean isShow; // 是否显示
	private Boolean isDel; // 是否删除:0删除,1,没删除
	private Integer typeId; // 类型id
	private Integer brandId; // 品牌id
	private String keywords; // 关键词
	private Integer sales; // 销量

	// 商品属性集合 ,,
	private String[] feature;
	// 颜色集 ,,
	private String[] color;
	// 商品尺寸
	private String[] size;

	private Boolean isNew; // 是否是新品
	private Boolean isHot; // 是否热卖
	private Boolean isCommend; // 是否推荐

	// -- 特别字段
	private String url; // 图片相对地址
	// -- 商品描述
	private String description; // fck
	// -- 包装清单
	private String packageList;

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

	public String[] getFeature() {
		return feature;
	}

	public void setFeature(String[] feature) {
		this.feature = feature;
	}

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public String[] getSize() {
		return size;
	}

	public void setSize(String[] size) {
		this.size = size;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
		return "ProductSaveData [id=" + id + ", no=" + no + ", name=" + name
				+ ", weight=" + weight + ", createTime=" + createTime
				+ ", createUserId=" + createUserId + ", checkTime=" + checkTime
				+ ", checkUserId=" + checkUserId + ", isShow=" + isShow
				+ ", isDel=" + isDel + ", typeId=" + typeId + ", brandId="
				+ brandId + ", keywords=" + keywords + ", sales=" + sales
				+ ", feature=" + Arrays.toString(feature) + ", color="
				+ Arrays.toString(color) + ", size=" + Arrays.toString(size)
				+ ", isNew=" + isNew + ", isHot=" + isHot + ", isCommend="
				+ isCommend + ", url=" + url + ", description=" + description
				+ ", packageList=" + packageList + "]";
	}

}
