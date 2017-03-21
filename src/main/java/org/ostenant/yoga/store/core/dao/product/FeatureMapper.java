package org.ostenant.yoga.store.core.dao.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Feature;
import org.ostenant.yoga.store.core.query.FeatureQuery;

public interface FeatureMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Feature record);

    int insertSelective(Feature record);

    Feature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);

    /**
     * @Title: getFeatureList <br>
     * @Description: 查询属性集合 <br>
     * @Author: madison <br>
     * @param @return    设定文件 <br>
     * @return List<Feature>    返回类型   <br>
     * @throws
     */
	List<Feature> getFeatureList();

	List<Feature> getFeatureList2(FeatureQuery featureQuery);
}