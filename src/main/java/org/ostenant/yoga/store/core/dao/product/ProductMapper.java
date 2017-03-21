package org.ostenant.yoga.store.core.dao.product;

import java.util.HashMap;
import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Product;
import org.ostenant.yoga.store.core.bean.product.ProductWithBLOBs;
import org.ostenant.yoga.store.core.query.MultiProductQuery;
import org.ostenant.yoga.store.core.query.ProductQuery;
import org.ostenant.yoga.store.core.response.ProductResponse;

public interface ProductMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(ProductWithBLOBs record);

    int insertSelective(ProductWithBLOBs record);

    ProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    int updateByPrimaryKey(Product record);

    /**
     * @Title: getProductListByQuery <br>
     * @Description:查看商品分页集合<br>
     * @Author: madison <br>
     * @param productQuery
     * @return List<Product>    返回类型   <br>
     * @throws
     */
	List<ProductResponse> getProductListByQuery(ProductQuery productQuery);

	/**
	 * @Title: getTotalCountByQuery <br>
	 * @Description: 查询满足条件的记录数 <br>
	 * @Author: madison <br>
	 * @param @param productQuery
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountByQuery(ProductQuery productQuery);

	/**
	 * @Title: getProductIdByNo <br>
	 * @Description: 通过no获取商品id <br>
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	Integer getProductIdByNo(String no);

	/**
	 * @Title: changeIsShowByIds <br>
	 * @Description: 改变上下架状态 <br>
	 * @Author: madison <br>
	 * @param @param params    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void changeIsShowByIds(HashMap<Object, Object> params);
	
	
	/**
	 * @Title: updatePriceFromSku <br>
	 * @Description: 更新商品平均价格（根据最小销售单元更新） <br>
	 * @Author: madison <br>
	 * @param     设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	int updatePriceFromSku();
	
	/**
	 * @Title: updatePriceFromSku <br>
	 * @Description: 更新商品平均价格（根据最小销售单元更新） <br>
	 * @Author: madison <br>
	 * @param     设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	int updatePriceFromSkuBy(Integer productId);
	
	/**
	 * @Title: updateSalesFromSku <br>
	 * @Description: 更新商品总销量 <br>
	 * @Author: madison <br>
	 * @param     设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	int updateSalesFromSku();
	
	/**
	 * @Title: updateSalesFromSku <br>
	 * @Description: 更新商品总销量 <br>
	 * @Author: madison <br>
	 * @param     设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	int updateSalesFromSkuBy(Integer productId);

	/**
	 *  前台 - 分页查询商品列表
	 * @Title: getProductListWithPage <br>
	 * @Author: madison <br>
	 * @return List<Product>    返回类型   <br>
	 * @throws
	 */
	List<ProductResponse> getProductListWithPage(MultiProductQuery multiProductQuery);

	/**
	 * 前台 - 分页查询商品列表总数
	 * @Title: getTotalCountListWithPage <br>
	 * @Author: madison <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountListWithPage(MultiProductQuery multiProductQuery);

	/**
	 * 前台-查询指定商品详情
	 * @Title: getProductById <br>
	 * @Author: madison <br>
	 * @return ProductResponse    返回类型   <br>
	 * @throws
	 */
	ProductResponse getProductById(Integer productId);

	/**
	 * @Title: getProductByIds <br>
	 * @Description: 通过id列表查询商品 <br>
	 * @Author: madison <br>
	 * @return List<ProductResponse>    返回类型   <br>
	 * @throws
	 */
	List<ProductResponse> getProductByIds(List<String> list);
	
	/**
	 * @Title: getAllUpCarriageIds <br>
	 * @Description: 获取所有上架商品的id <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Integer>    返回类型   <br>
	 * @throws
	 */
	List<Integer> getAllUpCarriageIds();

	/**
	 * @Title: getByProductId <br>
	 * @Description: 主键查询<br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return Product    返回类型   <br>
	 * @throws
	 */
	Product getByProductId(Integer productId);
    
}