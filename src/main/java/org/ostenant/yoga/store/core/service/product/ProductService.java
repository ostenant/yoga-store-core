package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Product;
import org.ostenant.yoga.store.core.query.MultiProductQuery;
import org.ostenant.yoga.store.core.query.ProductQuery;
import org.ostenant.yoga.store.core.query.ProductSaveData;
import org.ostenant.yoga.store.core.response.ProductResponse;


public interface ProductService {

	/**
	 * @Title: getProductListByQuery <br>
	 * @Description: 查询分页所需的数据单元 <br>
	 * @Author: madison <br>
	 * @param @param productQuery
	 * @param @return    设定文件 <br>
	 * @return List<Product>    返回类型   <br>
	 * @throws
	 */
	List<ProductResponse> getProductListByQuery(ProductQuery productQuery);

	/**
	 * @Title: getTotalCountByQuery <br>
	 * @Description: 获取满足条件的总数 <br>
	 * @Author: madison <br>
	 * @param @param productQuery
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountByQuery(ProductQuery productQuery);

	/**
	 * @Title: addProduct <br>
	 * @Description: 添加商品信息 <br>
	 * @Author: madison <br>
	 * @param @param product    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	int addProduct(ProductSaveData productSaveData);

	/**
	 * @Title: deleteProductById <br>
	 * @Description: 删除商品信息 <br>
	 * @Author: madison <br>
	 * @param @param productQuery    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void deleteProductById(ProductQuery productQuery);

	/**
	 * @Title: underCarriageByIds <br>
	 * @Description: 批量下架 <br>
	 * @Author: madison <br>
	 * @param @param ids    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void underCarriageByIds(Integer[] ids);

	
	/**
	 * @Title: topCarriageByIds <br>
	 * @Description: 批量上架 <br>
	 * @Author: madison <br>
	 * @param @param ids    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void topCarriageByIds(Integer[] ids);

	/**
	 * 前台 - 分页查询商品列表
	 * @Title: getProductListWithPage <br>
	 * @Description: TODO(这里用一句话描述这个方法的作用) <br>
	 * @Author: madison <br>
	 * @return List<Product>    返回类型   <br>
	 * @throws
	 */
	List<ProductResponse> getProductListWithPage(MultiProductQuery multiProductQuery);

	/**
	 * 前台 - 分页查询商品列表行数
	 * @Title: getTotalCountListWithPage <br>
	 * @Description: TODO(这里用一句话描述这个方法的作用) <br>
	 * @Author: madison <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int getTotalCountListWithPage(MultiProductQuery multiProductQuery);

	/**
	 * @Title: getProductById <br>
	 * @Description: 通过id获取商品 <br>
	 * @Author: madison <br>
	 * @return ProductResponse    返回类型   <br>
	 * @throws
	 */
	ProductResponse getProductById(Integer productId);

	/**
	 * 批量查询商品
	 * @Title: getProductByIds <br>
	 * @Description: TODO(这里用一句话描述这个方法的作用) <br>
	 * @Author: madison <br>
	 * @return List<ProductResponse>    返回类型   <br>
	 * @throws
	 */
	List<ProductResponse> getProductByIds(List<String> list);

	/**
	 * @Title: getProductByIdOnly <br>
	 * @Description: 查询 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return Product    返回类型   <br>
	 * @throws
	 */
	Product getProductByIdOnly(Integer productId);

}
