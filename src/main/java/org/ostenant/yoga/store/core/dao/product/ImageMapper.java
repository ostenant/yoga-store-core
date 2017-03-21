package org.ostenant.yoga.store.core.dao.product;

import java.util.List;

import org.ostenant.yoga.store.core.bean.product.Image;

public interface ImageMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    /**
     * @Title: deleteByProductId <br>
     * @Description: 通过外键productId删除  <br>
     * @Author: madison <br>
     * @param @param productId
     * @param @return    设定文件 <br>
     * @return int    返回类型   <br>
     * @throws
     */
	int deleteByProductId(int productId);

	/**
	 * @Title: getByProductId <br>
	 * @Description: 获取图片 <br>
	 * @Author: madison <br>
	 * @param @param productId
	 * @param @return    设定文件 <br>
	 * @return List<Image>    返回类型   <br>
	 * @throws
	 */
	List<Image> getByProductId(int productId);
}