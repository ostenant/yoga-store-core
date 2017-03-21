package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Brand;
import org.ostenant.yoga.store.core.query.BrandQuery;
import org.ostenant.yoga.store.core.query.MultiBrandQuery;

public interface BrandService {

	/**
	 * @Title: getBrandListBy <br>
	 * @Description: 查询品牌列表  <br>
	 * @Author: madison <br>
	 * @param condition 查询条件
	 * @return List<Brand>    返回类型   <br>
	 * @throws
	 */
	public List<Brand> getBrandListBy(BrandQuery brandSearchCondtion);

	/**
	 * @Title: getTotalCountBy <br>
	 * @Description: 查询品牌总数 <br>
	 * @Author: madison <br>
	 * @param @param brand
	 * @param @return    设定文件 <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	public Integer getTotalCountBy(BrandQuery brandSearchCondtion);

	/**
	 * @Title: addBrand <br>
	 * @Description: 添加品牌 <br>
	 * @Author: madison <br>
	 * @param @param brand    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void addBrand(Brand brand);

	
	/**
	 * @Title: deleteBrandById <br>
	 * @Description: 根据id删除品牌 <br>
	 * @Author: madison <br>
	 * @param @param id    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void deleteBrandById(int id);

	/**
	 * @Title: deleteBrandByIds <br>
	 * @Description: 批量删除品牌 <br>
	 * @Author: madison <br>
	 * @param @param ids    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void deleteBrandByIds(int[] ids);

	/**
	 * @Title: upGrounding <br>
	 * @Description: 上架 <br>
	 * @Author: madison <br>
	 * @param @param ids    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void upGrounding(MultiBrandQuery brandSearchCondtion);

	/**
	 * @Title: underCarriage <br>
	 * @Description: 下架 <br>
	 * @Author: madison <br>
	 * @param @param brandSearchCondtion    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void underCarriage(MultiBrandQuery brandSearchCondtion);

	/**
	 * @Title: getBrandById <br>
	 * @Description: 根据id查询品牌 <br>
	 * @Author: madison <br>
	 * @param @param id
	 * @param @return    设定文件 <br>
	 * @return Brand    返回类型   <br>
	 * @throws
	 */
	public Brand getBrandById(Integer id);

	/**
	 * @Title: editBrand <br>
	 * @Description: 编辑品牌 <br>
	 * @Author: madison <br>
	 * @param @param brand    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	public void editBrand(Brand brand);

	
	/**
	 * @Title: getBrandNameList <br>
	 * @Description: 获取品牌名称列表  <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Brand>    返回类型   <br>
	 * @throws
	 */
	public List<Brand> getBrandNameList();

	/**
	 * @param brandQuery 
	 * @Title: getBrandList <br>
	 * @Description: 选择性查询 <br>
	 * @Author: madison <br>
	 * @param @return    设定文件 <br>
	 * @return List<Brand>    返回类型   <br>
	 * @throws
	 */
	public List<Brand> getBrandList(BrandQuery brandQuery);
	
}

