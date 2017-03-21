package org.ostenant.yoga.store.core.dao.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.query.BrandQuery;
import org.ostenant.yoga.store.core.query.MultiBrandQuery;

public interface BrandMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Brand record);

	int insertSelective(Brand record);

	Brand selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Brand record);

	int updateByPrimaryKey(Brand record);

	/**
	 * @Title: getBrandListBy <br>
	 * @Description: 条件查询 <br>
	 * @Author: madison <br>
	 *          name: 名称 isDisplay: 是否展示
	 * @return List<Brand> 返回类型 <br>
	 * @throws
	 */
	List<Brand> getBrandListBy(BrandQuery brandSearchCondtion);

	/**
	 * @Title: getTotalCount <br>
	 * @Description:查询符合条件的总数<br>
	 * @Author: madison <br>
	 *          name: 名称 isDisplay: 是否展示
	 * @return Integer 返回类型 <br>
	 * @throws
	 */
	Integer getTotalCountBy(BrandQuery brandSearchCondtion);

	/**
	 * @Title: deleteByIds <br>
	 * @Description: 批量删除品牌 <br>
	 * @Author: madison <br>
	 * @param @param ids 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	void deleteByIds(List<Integer> idsList);

	/**
	 * @Title: updateBatchByIds <br>
	 * @Description: 批量更新品牌 <br>
	 * @Author: madison <br>
	 * @param @param idsList 设定文件 <br>
	 * @return void 返回类型 <br>
	 * @throws
	 */
	void updateBatchByIds(MultiBrandQuery brandSearchCondtion);

	/**
	 * @Title: getBrandNameList <br>
	 * @Description: 获取简单信息 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Brand>    返回类型   <br>
	 * @throws
	 */
	List<Brand> getBrandNameList();

	List<Brand> getBrandList(BrandQuery brandQuery);
	
}
