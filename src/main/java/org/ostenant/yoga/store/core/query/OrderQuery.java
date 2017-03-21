package org.ostenant.yoga.store.core.query;

import org.ostenant.yoga.store.core.bean.order.Order;
import org.ostenant.yoga.store.core.query.base.BaseQuery;

public class OrderQuery extends BaseQuery<Order> {

	private static final long serialVersionUID = 7656006314634958824L;

	
	public static void main(String[] args) {
		
		OrderQuery orderQuery = new OrderQuery();
		
		Order queryBean = orderQuery.getQueryBean();
		
		orderQuery.setFields("")//
			.orderBy("",true)//
			.getQueryBean()//
			.setBuyerId("").setCreateDate(null).setDeliverFee(null);
		
		System.out.println(queryBean);
	}
	
}
