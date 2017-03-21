package org.ostenant.yoga.store.core.service.impl.order;

import java.util.List;

import org.ostenant.yoga.store.core.bean.order.Detail;
import org.ostenant.yoga.store.core.dao.order.DetailMapper;
import org.ostenant.yoga.store.core.service.order.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailMapper detailMapper;

	
	public void saveOrderDetail(Detail detail) {
		detailMapper.insertSelective(detail);
	}

	@Transactional(readOnly = true)
	public List<Detail> getDetailsByOid(Integer id) {
		 List<Detail>  details = detailMapper.getDetailsByOid(id);
		return details;
	}
	
}
