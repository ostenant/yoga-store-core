package org.ostenant.yoga.store.core.dao.product;

import java.util.List;
import java.util.Map;

import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.response.SkuCellResponse;

public interface SkuCellMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(SkuCell record);

    int insertSelective(SkuCell record);

    SkuCell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkuCell record);

    int updateByPrimaryKey(SkuCell record);

    /**
	 * @Title: getSkuListByProductId <br>
	 * @Description: 查询最小销售单元 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return List<SkuCellResponse>    返回类型   <br>
	 * @throws
	 */
	List<SkuCellResponse> getSkuListByProductId(Map<String, Object> paramMap);
	
	/**
	 * @Title: getSkuListByProductId <br>
	 * @Description: 查询最小销售单元 不带分页和其他条件 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return List<SkuCellResponse>    返回类型   <br>
	 * @throws
	 */
	List<SkuCellResponse> getSkuListByPidWithoutPage(Integer productId);

	/**
	 * @Title: getTotalCountByProductId <br>
	 * @Description:  <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountByProductId(Integer productId);

	/**
	 * @Title: deleteByProductId <br>
	 * @Description: 删除最小销售单元 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int deleteByProductId(int productId);

}
