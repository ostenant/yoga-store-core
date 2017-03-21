package org.ostenant.yoga.store.core.service.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Image;

public interface ImageService {

	/**
	 * @Title: addImage <br>
	 * @Description: 保存图片信息 <br>
	 * @Author: madison <br>
	 * @param @param image    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void addImage(Image image);

	/**
	 * @Title: deleteByProductId <br>
	 * @Description: 通过商品id删除图片信息 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return int    返回类型   <br>
	 * @throws
	 */
	int deleteByProductId(int productId);

	/**
	 * @Title: getByProductId <br>
	 * @Description: 获取图片列表集合 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return List<Image>    返回类型   <br>
	 * @throws
	 */
	List<Image> getByProductId(int productId);

}
