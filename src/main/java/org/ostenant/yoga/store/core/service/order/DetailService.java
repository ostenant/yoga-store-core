package org.ostenant.yoga.store.core.service.order;

import java.util.List;

import org.ostenant.yoga.store.core.bean.order.Detail;

public interface DetailService {

	/**
	 * @Title: saveOrderDetail <br>
	 * @Description: 保存订单详情 <br>
	 * @Author: madison <br>
	 * @param @param detail    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void saveOrderDetail(Detail detail);

	List<Detail> getDetailsByOid(Integer id);

}
