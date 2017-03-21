package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Feature;
import org.ostenant.yoga.store.core.query.FeatureQuery;

public interface FeatureService {

	/**
	 * @Title: getFeatureList <br>
	 * @Description: 获取属性集合 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Feature>    返回类型   <br>
	 * @throws
	 */
	List<Feature> getFeatureList();

	/**
	 * 条件查询材质列表
	 * @return List<Feature>    返回类型   <br>
	 * @throws
	 */
	List<Feature> getFeatureList2(FeatureQuery featureQuery);

}
