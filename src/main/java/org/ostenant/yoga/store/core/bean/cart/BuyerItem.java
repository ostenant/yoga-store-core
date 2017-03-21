package org.ostenant.yoga.store.core.bean.cart;

import java.io.Serializable;

import org.ostenant.yoga.store.core.bean.product.SkuCell;

public class BuyerItem implements Serializable {

	private static final long serialVersionUID = 7296834248331289347L;

	/* 最小最小单元 */
	private SkuCell skuCell;
	/* 购物数量限制 */
	private Integer buyLimit;
	/* 购买数量 */
	private Integer amount;

	public boolean isOverStock(SkuCell skuCell){
		return this.amount >= skuCell.getStockInventory();
	}
	
	public SkuCell getSkuCell() {
		return skuCell;
	}

	public void setSkuCell(SkuCell skuCell) {
		this.skuCell = skuCell;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((buyLimit == null) ? 0 : buyLimit.hashCode());
		result = prime * result + ((skuCell == null) ? 0 : skuCell.hashCode());
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
		BuyerItem other = (BuyerItem) obj;
		
		if (skuCell == null) {
			if (other.skuCell != null)
				return false;
		} else if (!skuCell.equals(other.skuCell))
			return false;
		return true;
	}

	
}
