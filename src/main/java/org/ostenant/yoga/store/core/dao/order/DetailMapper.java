package org.ostenant.yoga.store.core.dao.order;

import java.util.List;

import org.ostenant.yoga.store.core.bean.order.Detail;

public interface DetailMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Detail record);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);

	List<Detail> getDetailsByOid(Integer id);
}