package org.ostenant.yoga.store.core.bean.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Order")
public class Order implements Serializable {

	private static final long serialVersionUID = -582553903824503116L;

	private Integer id;
	// 订单号
	private String oid;
	// 运费
	private BigDecimal deliverFee;
	// 应付金额
	private Double payableFee;
	// 订单金额
	private Double totalPrice;
	// 支付方式 0:到付 1:在线 2:邮局 3:公司转帐
	private Integer paymentWay;
	// 货到付款方式.1现金,2POS刷卡
	private Integer paymentCash;
	// 送货时间
	private Integer delivery;
	// 是否电话确认 1:是 0: 否
	private Integer isconfirm;
	// 支付状态 :0到付1待付款,2已付款,3待退款,4退款成功,5退款失败
	private Integer isPaiy;
	// 订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5待退货 6已退货
	private Integer state;
	// 订单生成时间
	private Date createDate;
	// 附言
	private String note;
	// 用户名
	private String buyerId;
	// 收货地址ID
	private Integer addrId;

	// ****************************************************************************************************************/

	// 支付方式 0:到付 1:在线 2:邮局 3:公司转帐
	public String getPaymentWayName() {
		switch (paymentWay) {
		case 0:
			return "货到付款";
		case 1:
			return "在线支付";
		case 2:
			return "公司转账";
		case 3:
			return "邮局汇款";
		default:
			return "";
		}
	}

	// 货到付款方式.1现金,2POS刷卡
	public String getPaymentCashName() {
		if (null == paymentCash) {
			return null;
		}

		switch (paymentCash) {
		case 0:
			return "现金";
		case 1:
			return "POS刷卡";
		default:
			return "";
		}

	}

	// 支付状态 0到付,1待付款,2已付款,3待退款,4退款成功,5退款失败 */
	public String getIsPaiyName() {
		switch (isPaiy) {
		case 0:
			return "货到到付";
		case 1:
			return "待付款";
		case 2:
			return "已付款";
		case 3:
			return "待退款";
		case 4:
			return "退款成功";
		case 5:
			return "退款失败";
		default:
			return "";
		}
	}

	// 订单状态 //订单状态 0:提交订单 1:仓库配货 2:商品出库 3:等待收货 4:完成 5:待退货 6已退货 7已取消 */
	public String getStateName() {
		switch (state) {
		case 0:
			return "提交订单";
		case 1:
			return "仓库配货";
		case 2:
			return "商品出库";
		case 3:
			return "等待收货";
		case 4:
			return "已完成";
		case 5:
			return "待退货";
		case 6:
			return "已退货";
		case 7:
			return "已取消";
		default:
			return "";
		}
	}

	// 送货时间 // 1:工作日，双休日，假日均可送货 2:只双休日，假日送货 3:只工作日送货（双休日，节假日不送） */
	public String getDeliveryName() {
		switch (delivery) {
		case 1:
			return "工作日，双休日，假日均可送货";
		case 2:
			return "只双休日，假日送货";
		case 3:
			return "只工作日送货（双休日，节假日不送）";
		default:
			return "";
		}
	}

	// 电话确认 1:是 0:否 */
	public String getIsConfirmName() {
		switch (isconfirm) {
		case 0:
			return "否";
		case 1:
			return "是";
		default:
			return "";
		}
	}

	// ****************************************************************************************************************/
	public Integer getPaymentWay() {
		return paymentWay;
	}

	public Order setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
		return this;
	}

	public Integer getPaymentCash() {
		return paymentCash;
	}

	public Order setPaymentCash(Integer paymentCash) {
		this.paymentCash = paymentCash;
		return this;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public Order setDelivery(Integer delivery) {
		this.delivery = delivery;
		return this;
	}

	public Integer getIsconfirm() {
		return isconfirm;
	}

	public Order setIsconfirm(Integer isconfirm) {
		this.isconfirm = isconfirm;
		return this;
	}

	public Integer getIsPaiy() {
		return isPaiy;
	}

	public Order setIsPaiy(Integer isPaiy) {
		this.isPaiy = isPaiy;
		return this;
	}

	public Integer getState() {
		return state;
	}

	public Order setState(Integer state) {
		this.state = state;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public Order setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getOid() {
		return oid;
	}

	public Order setOid(String oid) {
		this.oid = oid;
		return this;
	}

	public BigDecimal getDeliverFee() {
		return deliverFee;
	}

	public Order setDeliverFee(BigDecimal deliverFee) {
		this.deliverFee = deliverFee;
		return this;
	}

	public Double getPayableFee() {
		return payableFee;
	}

	public Order setPayableFee(Double payableFee) {
		this.payableFee = payableFee;
		return this;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public Order setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Order setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getNote() {
		return note;
	}

	public Order setNote(String note) {
		this.note = note;
		return this;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public Order setBuyerId(String buyerId) {
		this.buyerId = buyerId;
		return this;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", oid=" + oid + ", deliverFee="
				+ deliverFee + ", payableFee=" + payableFee + ", totalPrice="
				+ totalPrice + ", paymentWay=" + paymentWay + ", paymentCash="
				+ paymentCash + ", delivery=" + delivery + ", isconfirm="
				+ isconfirm + ", isPaiy=" + isPaiy + ", state=" + state
				+ ", createDate=" + createDate + ", note=" + note
				+ ", buyerId=" + buyerId + "]";
	}

}
