package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.query.base.PageQuery;
import org.ostenant.yoga.store.core.response.SkuCellResponse;

public interface SkuCellService {

	/**
	 * @param pageQuery 
	 * @Title: getSkuListByProductId <br>
	 * @Description: 查询最小销售单元 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return List<SkuCellResponse>    返回类型   <br>
	 * @throws
	 */
	List<SkuCellResponse> getSkuListByProductId(Integer productId, PageQuery pageQuery);

	/**
	 * @Title: getTotalCountByProductId <br>
	 * @Description: 根据商品id获取最小销售单元总数 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountByProductId(Integer productId);

	/**
	 * @param pid 
	 * @Title: saveSku <br>
	 * @Description: 保存修改信息 <br>
	 * @Author: madison <br>
	 * @param @param skuCell
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int saveSku(SkuCell skuCell);

	/**
	 * @Title: getSkuListByPidWithoutPage <br>
	 * @Description: 不带分页和其他条件 <br>
	 * @Author: madison <br>
	 * @return List<SkuCell>    返回类型   <br>
	 * @throws
	 */
	List<SkuCellResponse> getSkuListByPidWithoutPage(Integer productId);

	/**
	 * @Title: getSkuById <br>
	 * @Description: 根据id查询sku <br>
	 * @Author: madison <br>
	 * @param @param skuId
	 * @param @return    设定文件 <br>
	 * @return SkuCell    返回类型   <br>
	 * @throws
	 */
	SkuCell getSkuById(Integer skuId);

}
