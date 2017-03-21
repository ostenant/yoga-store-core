package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.query.TypeQuery;

public interface TypeService {

	/**
	 * @Title: getTypeNameList <br>
	 * @Description: 获取商品类型列表  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Type>    返回类型   <br>
	 * @throws
	 */
	List<Type> getTypeNameList();

	/**
	 * 查询类型集合
	 * @return List<Type>    返回类型   <br>
	 * @throws
	 */
	List<Type> getTypeList(TypeQuery typeQuery);

	/**
	 * @Title: getTypeById <br>
	 * @Description: 获取类型 <br>
	 * @Author: madison <br>
	 * @param @param typeId
	 * @param @return    设定文件 <br>
	 * @return Type    返回类型   <br>
	 * @throws
	 */
	Type getTypeById(Integer typeId);

}
