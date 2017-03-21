package org.ostenant.yoga.store.core.service.buyer;

import org.ostenant.yoga.store.core.bean.user.Buyer;

public interface BuyerService {

	/**
	 * @Title: getBuyerByUsername <br>
	 * @Description: 根据用户名获取用户 <br>
	 * @Author: madison <br>
	 * @return Buyer    返回类型   <br>
	 * @throws
	 */
	Buyer getBuyerByUsername(String username);

	/**
	 * @Title: saveBuyerInfo <br>
	 * @Description: 保存用户信息 <br>
	 * @Author: madison <br>
	 * @param @param buyer    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void saveBuyerInfo(Buyer buyer);

}
