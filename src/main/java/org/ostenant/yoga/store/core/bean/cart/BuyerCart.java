package org.ostenant.yoga.store.core.bean.cart;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
public class BuyerCart implements Serializable {

	/* 购物车 */
	private LinkedList<BuyerItem> buyerItems = new LinkedList<BuyerItem>();
	/* 商品ID */
	private Integer productId;
	/* 最近修改购物车时间 */
	private Date lastModifieDate;
	/* 总费用 */
	@JsonIgnore
	private double totalExpenses; // 默认不序列化成json
	/* 总邮费 */
	@JsonIgnore
	private double totalDeliverFee;
	/* 商品总费用 */
	@JsonIgnore
	private double totalProdExpenses;
	/* 购物车商品总件数 */
	@JsonIgnore
	private int productSize;

	/* 添加购物项 */
	public void addBuyerItem(BuyerItem buyerItem) {
		buyerItems.addFirst(buyerItem);
	}

	/* 添加购物项  不重复 */
	public void addBuyerItemNoRepeated(BuyerItem buyerItem) {
		// 判断是否重复
		if (buyerItems.contains(buyerItem)) {
			for (BuyerItem it : buyerItems) {
				if (it.equals(buyerItem)) {
					int result = it.getAmount() + buyerItem.getAmount();
					if (it.getSkuCell().getSkuUpperLimit() >= result) {
						it.setAmount(result);
					} 
				}
			}
		} else {
			buyerItems.add(buyerItem);
		}
	}
	
	/* 删除某个item */
	public void deleteItem(BuyerItem buyerItem){
		buyerItems.remove(buyerItem);
	}
	
	/* 清空购物车 */
	public void clear(){
		buyerItems.clear();
	}

	public LinkedList<BuyerItem> getBuyerItems() {
		return buyerItems;
	}

	public void setBuyerItems(LinkedList<BuyerItem> buyerItems) {
		this.buyerItems = buyerItems;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getLastModifieDate() {
		return lastModifieDate;
	}

	public void setLastModifieDate(Date lastModifieDate) {
		this.lastModifieDate = lastModifieDate;
	}

	public double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public double getTotalDeliverFee() {
		return totalDeliverFee;
	}

	public void setTotalDeliverFee(double totalDeliverFee) {
		this.totalDeliverFee = totalDeliverFee;
	}

	public double getTotalProdExpenses() {
		return totalProdExpenses;
	}

	public void setTotalProdExpenses(double totalProdExpenses) {
		this.totalProdExpenses = totalProdExpenses;
	}
	

	public int getProductSize() {
		int count = 0;
		for (BuyerItem bi : getBuyerItems()) {
			count += bi.getAmount();
		}
		productSize = count;
		return productSize;
	}

	public void setProductSize(int productSize) {
		this.productSize = productSize;
	}

	@Override
	public String toString() {
		return "BuyerCart [buyerItems=" + buyerItems + ", productId="
				+ productId + ", lastModifieDate=" + lastModifieDate
				+ ", totalExpenses=" + totalExpenses + ", totalDeliverFee="
				+ totalDeliverFee + ", totalProdExpenses=" + totalProdExpenses
				+ "]";
	}

}
