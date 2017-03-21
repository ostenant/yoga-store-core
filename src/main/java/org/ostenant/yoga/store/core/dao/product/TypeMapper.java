package org.ostenant.yoga.store.core.dao.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.query.TypeQuery;

public interface TypeMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    /**
     * @Title: getTypeNameList <br>
     * @Description: 获取商品类型列表  <br>
     * @Author: madison <br>
     * @param @return    设定文件 <br>
     * @return List<Type>    返回类型   <br>
     * @throws
     */
	List<Type> getTypeNameList();

	List<Type> getTypeList(TypeQuery typeQuery);
	
}
