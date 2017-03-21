package org.ostenant.yoga.store.core.bean.cart;

import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

public class BuyerCartQuery {

	/* 最小最小单元 */
	@NotNull(message = "skuId不能为空!")
	private Integer skuId;
	/* 购物数量限制 */
	@NotNull(message = "buyLimit不能为空!")
	private Integer buyLimit;
	/* 购买数量 */
	@NotNull(message = "amount不能为空!")
	private Integer amount;
	/* 返回商品id */
	@NotNull(message = "productId不能为空!")
	private Integer productId;

	
	public void validation(){
		Assert.notNull(this.skuId, "skuId不能为空!");
		Assert.notNull(this.buyLimit, "buyLimit不能为空!");
		Assert.notNull(this.amount, "amount不能为空!");
		Assert.notNull(this.productId, "productId不能为空!");
	}
	
	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(Integer buyLimit) {
		this.buyLimit = buyLimit;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
