package org.ostenant.yoga.store.core.dao.user;

import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.query.BuyerQuery;

public interface BuyerMapper {
	
    int deleteByPrimaryKey(String username);

    int insert(Buyer record);

    int insertSelective(Buyer record);

    Buyer selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Buyer record);

    int updateByPrimaryKey(Buyer record);

    /**
     * @Title: getBuyerByCondition <br>
     * @Description: 条件查询购物者信息 <br>
     * @Author: madison <br>
     * @return Buyer    返回类型   <br>
     * @throws
     */
	Buyer getBuyerByCondition(BuyerQuery buyerQuery);
    
}
