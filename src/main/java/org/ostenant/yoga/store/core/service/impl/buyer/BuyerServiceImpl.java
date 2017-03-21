package org.ostenant.yoga.store.core.service.impl.buyer;

import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.dao.user.BuyerMapper;
import org.ostenant.yoga.store.core.query.BuyerQuery;
import org.ostenant.yoga.store.core.service.buyer.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerMapper buyerMapper;

	@Transactional(readOnly = true)
	public Buyer getBuyerByUsername(String username) {
		BuyerQuery buyerQuery = new BuyerQuery();
		buyerQuery.setUsername(username.trim());
		buyerQuery
				.setFields("username, password, gender, email, real_name, register_time, province, city,town,addr, is_del");
		Buyer buyer = buyerMapper.getBuyerByCondition(buyerQuery);
		return buyer;
	}

	
	public void saveBuyerInfo(Buyer buyer) {
		buyerMapper.updateByPrimaryKeySelective(buyer);
	}

}
